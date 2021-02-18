package com.bmk.daggerproject.ui.c

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.view.isVisible
import com.bmk.daggerproject.R
import com.bmk.daggerproject.databinding.FragmentCBinding
import com.bmk.daggerproject.ui.d.DFragment
import com.bmk.daggerproject.ui.main.MainActivity
import com.bmk.daggerproject.util.base.CommonFragment
import com.bmk.daggerproject.util.getDefaultAdapter
import com.bmk.domain.KeyValue
import com.jakewharton.rxbinding3.view.clicks
import io.reactivex.Observable
import java.util.*
import javax.inject.Inject

class CFragment : CommonFragment(), CView {
    @Inject
    lateinit var presenter: CPresenter
    lateinit var binding: FragmentCBinding
    lateinit var empExpAdapter: ArrayAdapter<KeyValue>
    lateinit var acTypeAdapter: ArrayAdapter<KeyValue>

    override fun getLayout() = R.layout.fragment_c

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentCBinding.bind(view)
        presenter.start()
        empExpAdapter = getDefaultAdapter(requireContext(), ExperienceList)
        binding.spnrEmpExp.adapter = empExpAdapter

        acTypeAdapter = getDefaultAdapter(requireContext(), AcTypeList)
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

    override fun getEmpAcType(): KeyValue {
        return binding.spnrAcType.selectedItem as KeyValue
    }

    override fun getEmpExp(): KeyValue {
        return binding.spnrEmpExp.selectedItem as KeyValue
    }

    override fun onSubmitClick(): Observable<Unit> {
        return binding.btnSubmit.clicks()
    }

    override fun showError(error: String) {
        Toast.makeText(requireContext(), error, Toast.LENGTH_SHORT).show()
    }

    override fun showProgress(show: Boolean) {
        binding.progressBar.isVisible = show
    }

    override fun showErrorMessage(error: String?) {
        Log.e("Error", error)
    }

    override fun bankScreen() {
        activity?.let {
            if (it.supportFragmentManager.findFragmentByTag(DFragment.TAG) == null) {
                it.supportFragmentManager.beginTransaction()
                    .addToBackStack(null)
                    .setCustomAnimations(
                        MainActivity.AnimType.FADE.getAnimPair().first,
                        MainActivity.AnimType.FADE.getAnimPair().second
                    )
                    .add(
                        R.id.frame,
                        DFragment.newInstance(),
                        DFragment.TAG
                    )
                    .commit()
            }
        }
    }


    companion object {
        val TAG: String = "CFragment"
        val AcTypeList =
            listOf(KeyValue(-1, "Select A/C Type"), KeyValue(1, "Saving"), KeyValue(2, "Current"))
        val ExperienceList = listOf(
            KeyValue(-1, "Select Experience"),
            KeyValue(1, "1"),
            KeyValue(2, "2"),
            KeyValue(3, "3")
        )

        fun newInstance(): CFragment {
            return CFragment()
        }
    }
}