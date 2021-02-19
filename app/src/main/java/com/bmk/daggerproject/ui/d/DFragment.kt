package com.bmk.daggerproject.ui.d

import android.Manifest
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.content.ContextCompat.checkSelfPermission
import androidx.core.content.FileProvider
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import com.bmk.daggerproject.R
import com.bmk.daggerproject.databinding.FragmentDBinding
import com.bmk.daggerproject.ui.a.AFragment
import com.bmk.daggerproject.ui.c.CFragment
import com.bmk.daggerproject.ui.main.MainActivity
import com.bmk.daggerproject.util.base.CommonFragment
import com.bmk.daggerproject.util.getDefaultAdapter
import com.bmk.domain.BankData
import com.bmk.domain.KeyValue
import com.bumptech.glide.Glide
import com.jakewharton.rxbinding3.view.clicks
import io.reactivex.Observable
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject


class DFragment : CommonFragment(), DView {
    @Inject
    lateinit var presenter: DPresenter
    lateinit var binding: FragmentDBinding
    lateinit var empExpAdapter: ArrayAdapter<KeyValue>
    var imageFilePath: String? = null
    override fun getLayout() = R.layout.fragment_d

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDBinding.bind(view)
        presenter.start()
        empExpAdapter = getDefaultAdapter(
            requireContext(),
            BranchList
        )
        binding.spnrBranchName.adapter = empExpAdapter
    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_CAPTURE_IMAGE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                startCamera()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode === REQUEST_CAPTURE_IMAGE && resultCode === RESULT_OK) {
            if (data != null && data.extras != null) {
                val d = data.extras!!.get("data") as Bitmap
                binding.ivImage.setImageBitmap(d)
            }
        }
        if (requestCode == REQUEST_CAPTURE_IMAGE) {
            binding.ivImage.visibility = View.VISIBLE
            Glide.with(this).load(imageFilePath).into(binding.ivImage)
        }
    }


    private fun createImageFile(): File? {
        val timeStamp: String =
            SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
        val imageFileName = "IMG_" + timeStamp + "_"
        val storageDir = requireContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        val image: File = File.createTempFile(
            imageFileName,  /* prefix */
            ".jpg",  /* suffix */
            storageDir /* directory */
        )
        imageFilePath = image.absolutePath
        return image
    }

    override fun getBankName(): String {
        return binding.etBankName.text.toString().trim()
    }

    override fun getBranchName(): KeyValue {
        return binding.spnrBranchName.selectedItem as KeyValue
    }

    override fun getAcNo(): String {
        return binding.etAcNo.text.toString().trim()
    }

    override fun getIfsc(): String {
        return binding.etIfsc.text.toString().trim()
    }

    override fun getImageURL(): String? {
        return imageFilePath
    }


    override fun onSubmitClick(): Observable<Unit> {
        return binding.btnSubmit.clicks()
    }

    override fun showToast(error: String) {
        Toast.makeText(requireContext(), error, Toast.LENGTH_SHORT).show()
    }

    override fun render(data: BankData) {
        binding.etBankName.setText(data.bankName)
        val pos =
            BranchList.firstOrNull { it.value == data.branch }
                ?: CFragment.ExperienceList.first()
        binding.spnrBranchName.setSelection(empExpAdapter.getPosition(pos))
        binding.etAcNo.setText(data.acNo)
        binding.etIfsc.setText(data.ifscCode)
        imageFilePath = data.image
        Glide.with(this).load(data.image).into(binding.ivImage)
        binding.ivImage.visibility = View.VISIBLE
    }

    override fun backToHome() {
        activity?.supportFragmentManager?.beginTransaction()
            ?.setCustomAnimations(
                MainActivity.AnimType.FADE.getAnimPair().first,
                MainActivity.AnimType.FADE.getAnimPair().second
            )?.add(
                R.id.frame,
                AFragment.newInstance(),
                AFragment.TAG
            )?.commit()
    }

    override fun showProgress(show: Boolean) {
        binding.progressBar.isVisible = show
    }

    override fun showErrorMessage(error: String?) {
        Log.e("Error", error)
    }

    fun openCamera() {
        if (checkSelfPermission(requireContext(), Manifest.permission.CAMERA)
            != PackageManager.PERMISSION_GRANTED
        ) requestPermissions(
            arrayOf(Manifest.permission.CAMERA),
            REQUEST_CAPTURE_IMAGE
        ) else startCamera()
    }

    fun startCamera() {
        val pictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        activity?.let {

            if (pictureIntent.resolveActivity(it.packageManager) != null) {
                //Create a file to store the image
                var photoFile: File? = null
                try {
                    photoFile = createImageFile();
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
                    startActivityForResult(pictureIntent, REQUEST_CAPTURE_IMAGE)
                }
            }
        }
    }

    companion object {
        const val REQUEST_CAPTURE_IMAGE = 100

        val BranchList =
            listOf(
                KeyValue(-1, "Select Branch"),
                KeyValue(1, "Borivali East"),
                KeyValue(2, "Borivali West"),
                KeyValue(3, "Malad East"),
                KeyValue(4, "Malad West")
            )
        val TAG: String = "DFragment"
        val ARGS_EMP_DATA: String = "DFragment_data"
        fun newInstance(data: EmployeeInputRequest): DFragment {
            return DFragment().apply {
                arguments = bundleOf(DFragment.ARGS_EMP_DATA to data)
            }
        }
    }
}