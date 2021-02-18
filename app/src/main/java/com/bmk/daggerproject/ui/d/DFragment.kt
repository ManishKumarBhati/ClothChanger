package com.bmk.daggerproject.ui.d

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import com.bmk.daggerproject.R
import com.bmk.daggerproject.databinding.FragmentDBinding
import com.bmk.daggerproject.ui.a.AFragment
import com.bmk.daggerproject.ui.main.MainActivity
import com.bmk.daggerproject.util.base.CommonFragment
import com.bmk.daggerproject.util.getDefaultAdapter
import com.bmk.domain.KeyValue
import com.jakewharton.rxbinding3.view.clicks
import io.reactivex.Observable
import javax.inject.Inject

class DFragment : CommonFragment(), DView {
    @Inject
    lateinit var presenter: DPresenter
    lateinit var binding: FragmentDBinding
    lateinit var empExpAdapter: ArrayAdapter<KeyValue>

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

    override fun onSubmitClick(): Observable<Unit> {
        return binding.btnSubmit.clicks()
    }

    override fun showError(error: String) {
        Toast.makeText(requireContext(), error, Toast.LENGTH_SHORT).show()
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


    companion object {
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