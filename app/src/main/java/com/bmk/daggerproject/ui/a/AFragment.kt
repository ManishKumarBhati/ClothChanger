package com.bmk.daggerproject.ui.a

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.view.animation.Animation
import android.view.animation.BounceInterpolator
import android.view.animation.ScaleAnimation
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.core.view.isVisible
import androidx.viewpager2.widget.ViewPager2
import com.bmk.daggerproject.R
import com.bmk.daggerproject.databinding.FragmentABinding
import com.bmk.daggerproject.ui.main.MainActivity
import com.bmk.daggerproject.util.base.CommonFragment
import com.bmk.domain.UserData
import com.jakewharton.rxbinding3.view.changeEvents
import com.jakewharton.rxbinding3.view.clicks
import com.jakewharton.rxbinding3.widget.checkedChanges
import io.reactivex.Observable
import io.reactivex.rxkotlin.ofType
import io.reactivex.subjects.PublishSubject
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject


class AFragment : CommonFragment(), AContract {
    var topPos: Long = -1
    var bottomPos: Long = -1
    var selectedButton = -1
    var fileName: Pair<String?, Int> = null to -1
    val topList: MutableList<UserData> = mutableListOf()
    val bottomList: MutableList<UserData> = mutableListOf()

    @Inject
    lateinit var presenter: APresenter
    lateinit var binding: FragmentABinding
    val subject = PublishSubject.create<UIEvent>()
    override fun getLayout() = R.layout.fragment_a

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentABinding.bind(view)
        presenter.start()

        binding.vp1.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                if (topList.isNotEmpty()) {
                    topPos = topList[position].id
                    subject.onNext(UIEvent.OnScroll(topPos, bottomPos))
                }
            }
        })
        binding.vp2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                if (topList.isNotEmpty()) {
                    bottomPos = bottomList[position].id
                    subject.onNext(UIEvent.OnScroll(topPos, bottomPos))
                }
            }
        })

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

    override fun onScroll(): Observable<UIEvent.OnScroll> {
        return subject.ofType()
    }

    override fun onChkChange(): Observable<Boolean> {
        return binding.tbFav.checkedChanges()
    }

    override fun getData(): Pair<Long, Long> {
        return topPos to bottomPos
    }

    override fun handleCheckChange() {
        val scaleAnimation = ScaleAnimation(
            0.7f,
            1.0f,
            0.7f,
            1.0f,
            Animation.RELATIVE_TO_SELF,
            0.7f,
            Animation.RELATIVE_TO_SELF,
            0.7f
        )
        scaleAnimation.duration = 500
        val bounceInterpolator = BounceInterpolator()
        scaleAnimation.interpolator = bounceInterpolator
        binding.tbFav.startAnimation(scaleAnimation)
    }

    override fun render(data: List<UserData>) {
        topList.clear()
        bottomList.clear()

        val top = data.filter { it.topbottom == AContract.TOP }
        val bottom = data.filter { it.topbottom == AContract.BOTTOM }

        if (top.isNotEmpty()) {
            topList.addAll(top)
            binding.vp1.adapter = ViewPagerAdapter(requireContext(), top.shuffled())
        }
        if (bottom.isNotEmpty()) {
            bottomList.addAll(bottom)
            binding.vp2.adapter = ViewPagerAdapter(requireContext(), bottom.shuffled())
        }
    }

    override fun renderImageSave(data: Long) {
        Log.d("leo", data.toString())
    }

    override fun renderFav(isFav: Boolean) {
        binding.tbFav.isChecked = isFav
    }

    override fun openCamera(id: Int) {
        selectedButton = id
        if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.CAMERA)
            != PackageManager.PERMISSION_GRANTED
        ) requestPermissions(
            arrayOf(Manifest.permission.CAMERA),
            AContract.REQUEST_CAPTURE_IMAGE
        ) else startCamera(id)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == AContract.REQUEST_CAPTURE_IMAGE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                startCamera(selectedButton)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (AContract.REQUEST_CAPTURE_IMAGE == requestCode && resultCode == -1) fileName.first?.let {
            subject.onNext(UIEvent.AddImage(it, fileName.second))
        } else fileName = null to -1
        super.onActivityResult(requestCode, resultCode, data)
    }

    override fun onResume() {
        super.onResume()
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
        fileName = image.absolutePath to id
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