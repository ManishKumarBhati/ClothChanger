package com.bmk.daggerproject.ui.b

import android.util.Log
import com.bmk.daggerproject.util.base.BasePresenter
import com.bmk.domain.MatchRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class BPresenter @Inject constructor(
    view: BView
) : BasePresenter<BView>(view) {
    override fun start() {
        view.onDobClick()
            .subscribe { view.showDatePicker() }
            .addTo(disposable)

        val submitObs = view.onSubmitClick().map { validateInput() != null }.share()

        submitObs
            .filter { !it }
            .subscribe { view.empScreen() }
            .addTo(disposable)

        submitObs
            .filter { it }
            .subscribe { view.showError(validateInput()!!) }
            .addTo(disposable)
    }

    fun validateInput(): String? {
        Log.d(
            "bmk",
            """${view.getFirstName()}${view.getLastName()} ${view.getMob()} ${view.getDOB()}"""
        )
        return when {
            view.getFirstName().isEmpty() -> "Please Enter First Name"
            view.getLastName().isEmpty() -> "Please Enter Last Name"
            view.getMob().isEmpty() -> "Please Enter Mobile No"
            view.getDOB().equals("Select DOB", true) -> "Please Select DOB"
            else -> null
        }
    }
}
