package com.bmk.daggerproject.ui.a

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.core.view.isVisible
import com.bmk.daggerproject.R
import com.bmk.daggerproject.databinding.FragmentABinding
import com.bmk.daggerproject.ui.main.MainActivity
import com.bmk.daggerproject.util.base.CommonFragment
import com.bmk.domain.UserData
import com.jakewharton.rxbinding3.view.clicks
import io.reactivex.Observable
import io.reactivex.rxkotlin.ofType
import io.reactivex.subjects.PublishSubject
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject


class AFragment : CommonFragment(), AContract {

    @Inject
    lateinit var presenter: APresenter
    lateinit var binding: FragmentABinding
    val subject = PublishSubject.create<UIEvent>()
    override fun getLayout() = R.layout.fragment_a

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentABinding.bind(view)
        presenter.start()
    }


    override fun showProgress(show: Boolean) {
        binding.progressBar.isVisible = show
    }

    override fun showErrorMessage(error: String?) {
        Log.e("Error", error)
    }

    override fun onAddTopClick(): Observable<Unit> {
        return binding.fabTop.clicks()
    }

    override fun onAddBottomClick(): Observable<Unit> {
        return binding.fabBottom.clicks()
    }

    override fun onShuffleClickCLick(): Observable<Unit> {
        return binding.fabShuffle.clicks()
    }

    override fun onAddImg(): Observable<UIEvent.AddImage> {
        return subject.ofType()
    }

    override fun render(data: List<UserData>) {
        val top = data.filter { it.topbottom == AContract.TOP }
        val bottom = data.filter { it.topbottom == AContract.BOTTOM }

        binding.vp1.adapter = ViewPagerAdapter(requireContext(), top.shuffled())
        binding.vp2.adapter = ViewPagerAdapter(requireContext(), bottom.shuffled())
    }

    override fun renderImageSave(data: Long) {
        Log.d("BMK", data.toString())
    }

    override fun openCamera(id: Int) {
        if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.CAMERA)
            != PackageManager.PERMISSION_GRANTED
        ) requestPermissions(
            arrayOf(Manifest.permission.CAMERA),
            AContract.REQUEST_CAPTURE_IMAGE
        ) else startCamera(id)
    }

    fun startCamera(id: Int) {
        val pictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        activity?.let {

            if (pictureIntent.resolveActivity(it.packageManager) != null) {
                //Create a file to store the image
                var photoFile: File? = null
                try {
                    photoFile = createImageFile(id)
                } catch (ex: IOException) {
                }
                if (photoFile != null) {
                    val photoURI =
                        FileProvider.getUriForFile(
                            requireContext(),
                            "com.bmk.daggerproject.provider",
                            photoFile
                        )
                    pictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
                    startActivityForResult(pictureIntent, AContract.REQUEST_CAPTURE_IMAGE)
                }
            }
        }
    }

    private fun createImageFile(id: Int): File? {
        val timeStamp: String =
            SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
        val imageFileName = "IMG_" + timeStamp + "_"
        val storageDir = requireContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        val image: File = File.createTempFile(
            imageFileName,  /* prefix */
            ".jpg",  /* suffix */
            storageDir /* directory */
        )
        subject.onNext(UIEvent.AddImage(image.absolutePath, id))
        return image
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.stop()
    }

    companion object {
        val TAG: String = "AFragment"
        fun newInstance(): AFragment {
            return AFragment()
        }
    }
}