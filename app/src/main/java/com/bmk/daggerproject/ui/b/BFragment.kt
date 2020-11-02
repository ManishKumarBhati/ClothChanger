package com.bmk.daggerproject.ui.b

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import com.bmk.daggerproject.R
import com.bmk.daggerproject.databinding.FragmentBBinding
import com.bmk.daggerproject.domain.DetailsData
import com.bmk.daggerproject.ui.about.AFragment
import com.bmk.daggerproject.util.CommonFragment
import com.bmk.daggerproject.util.setGlideImage
import com.jakewharton.rxbinding3.view.clicks
import io.reactivex.Observable
import javax.inject.Inject

class BFragment : CommonFragment(), BView {
    @Inject
    lateinit var presenter: BPresenter
    lateinit var binding: FragmentBBinding

    override fun getLayout() = R.layout.fragment_b

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentBBinding.bind(view)
        presenter.start()

    }

    override fun onSubmitClick(): Observable<Unit> {
        return binding.btnSubmit.clicks()
    }

    override fun getComment(): String {
        return binding.etCmnt.text.toString().trim()
    }

    override fun render(data: DetailsData) {
        activity?.title = data.title.substring(0, 10)
        binding.ivImg.setGlideImage(data.imgUrl)
        binding.etCmnt.setText(data.comment)
    }

    override fun showProgress(show: Boolean) {
        binding.progressBar.isVisible = show
    }

    override fun showErrorMessage(error: String?) {
        Log.e("Error", error)
    }


    companion object {
        val TAG: String = "BFragment"
        val ARGS_ID: String = "args id"
        fun newInstance(id: String): AFragment {
            return AFragment().apply {
                arguments = bundleOf(ARGS_ID to id)
            }
        }
    }
}