package com.bmk.daggerproject.ui.d

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.RadioButton
import androidx.core.view.isVisible
import com.bmk.daggerproject.R
import com.bmk.daggerproject.databinding.FragmentBBinding
import com.bmk.daggerproject.databinding.FragmentCBinding
import com.bmk.daggerproject.databinding.FragmentDBinding
import com.bmk.daggerproject.util.base.CommonFragment
import com.bmk.daggerproject.util.dateFormat
import com.bmk.daggerproject.util.getDefaultAdapter
import com.bmk.daggerproject.util.showDatePicker
import com.bmk.domain.DetailsData
import com.jakewharton.rxbinding3.view.clicks
import io.reactivex.Observable
import java.util.*
import javax.inject.Inject

class DFragment : CommonFragment(), DView {
    @Inject
    lateinit var presenter: DPresenter
    lateinit var binding: FragmentDBinding
    lateinit var empExpAdapter: ArrayAdapter<String>

    override fun getLayout() = R.layout.fragment_d

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDBinding.bind(view)
        presenter.start()
        empExpAdapter = getDefaultAdapter(
            requireContext(),
            listOf("Borivali East", "Borivali West", "Malad East", "Malad West")
        )
        binding.spnrBranchName.adapter = empExpAdapter
    }

    override fun getBankName(): String {
        return binding.etBankName.text.toString().trim()
    }

    override fun getBranchName(): String {
        return binding.spnrBranchName.selectedItem as String
    }

    override fun getAcNo(): String {
        return binding.etAcNo.text.toString().trim()
    }

    override fun getIfsc(): String {
        return binding.etIfsc.text.toString().trim()
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
        val TAG: String = "DFragment"
        fun newInstance(): DFragment {
            return DFragment()
        }
    }
}