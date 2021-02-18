package com.bmk.daggerproject.ui.b

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.RadioButton
import android.widget.Toast
import androidx.core.view.isVisible
import com.bmk.daggerproject.R
import com.bmk.daggerproject.databinding.FragmentBBinding
import com.bmk.daggerproject.ui.c.CFragment
import com.bmk.daggerproject.ui.d.PersonalInputRequest
import com.bmk.daggerproject.ui.main.MainActivity
import com.bmk.daggerproject.util.base.CommonFragment
import com.bmk.daggerproject.util.dateFormat
import com.bmk.daggerproject.util.showDatePicker
import com.jakewharton.rxbinding3.view.clicks
import io.reactivex.Observable
import java.util.*
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

    override fun getFirstName(): String {
        return binding.etFirstName.text.toString().trim()
    }

    override fun getLastName(): String {
        return binding.etLastName.text.toString().trim()
    }

    override fun getMob(): String {
        return binding.etMob.text.toString().trim()
    }

    override fun getGender(): String {
        return binding.rgGender.findViewById<RadioButton>(binding.rgGender.checkedRadioButtonId).text.toString()
            .trim()
    }

    override fun getDOB(): String {
        return binding.tvDob.text.toString().trim()
    }

    override fun onSubmitClick(): Observable<Unit> {
        return binding.btnSubmit.clicks()
    }

    override fun onDobClick(): Observable<Unit> {
        return binding.tvDob.clicks()
    }

    override fun showDatePicker() {
        requireContext().showDatePicker({ dob ->
            val date = dateFormat.format(dob)
            binding.tvDob.text = date
        }, Date(), null, Date())
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

    override fun empScreen(data: PersonalInputRequest) {
        activity?.let {
            if (it.supportFragmentManager.findFragmentByTag(CFragment.TAG) == null) {
                it.supportFragmentManager.beginTransaction()
                    .addToBackStack(null)
                    .setCustomAnimations(
                        MainActivity.AnimType.FADE.getAnimPair().first,
                        MainActivity.AnimType.FADE.getAnimPair().second
                    )
                    .add(
                        R.id.frame,
                        CFragment.newInstance(data),
                        CFragment.TAG
                    )
                    .commit()
            }
        }
    }


    companion object {
        val TAG: String = "BFragment"
        fun newInstance(): BFragment {
            return BFragment()
        }
    }
}