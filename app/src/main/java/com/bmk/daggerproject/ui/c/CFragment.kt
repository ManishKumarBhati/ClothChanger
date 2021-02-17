package com.bmk.daggerproject.ui.c

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.RadioButton
import androidx.core.view.isVisible
import com.bmk.daggerproject.R
import com.bmk.daggerproject.databinding.FragmentBBinding
import com.bmk.daggerproject.databinding.FragmentCBinding
import com.bmk.daggerproject.util.base.CommonFragment
import com.bmk.daggerproject.util.dateFormat
import com.bmk.daggerproject.util.getDefaultAdapter
import com.bmk.daggerproject.util.showDatePicker
import com.bmk.domain.DetailsData
import com.jakewharton.rxbinding3.view.clicks
import io.reactivex.Observable
import java.util.*
import javax.inject.Inject

class CFragment : CommonFragment(), CView {
    @Inject
    lateinit var presenter: CPresenter
    lateinit var binding: FragmentCBinding
    lateinit var empExpAdapter: ArrayAdapter<String>
    lateinit var acTypeAdapter: ArrayAdapter<String>

    override fun getLayout() = R.layout.fragment_c

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentCBinding.bind(view)
        presenter.start()
        empExpAdapter = getDefaultAdapter(requireContext(), listOf("1", "2", "3", "4", "5"))
        binding.spnrEmpExp.adapter = empExpAdapter
        acTypeAdapter = getDefaultAdapter(requireContext(), listOf("Current", "Saving"))
        binding.spnrAcType.adapter = acTypeAdapter
    }

    override fun getEmpNo(): String {
        return binding.etEmpNo.toString().trim()
    }

    override fun getEmpName(): String {
        return binding.etEmpName.toString().trim()
    }

    override fun getEmpDesg(): String {
        return binding.etEmpDesg.toString().trim()
    }

    override fun getEmpAcType(): String {
        return binding.spnrEmpExp.selectedItem as String
    }

    override fun getEmpExp(): String {
        return binding.spnrEmpExp.selectedItem as String
    }

    override fun onSubmitClick(): Observable<Unit> {
        return binding.btnSubmit.clicks()
    }

    override fun render() {

    }

    override fun showProgress(show: Boolean) {
        binding.progressBar.isVisible = show
    }

    override fun showErrorMessage(error: String?) {
        Log.e("Error", error)
    }


    companion object {
        val TAG: String = "CFragment"
        fun newInstance(): CFragment {
            return CFragment()
        }
    }
}