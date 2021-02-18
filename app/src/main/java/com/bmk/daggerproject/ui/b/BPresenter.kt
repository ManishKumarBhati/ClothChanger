package com.bmk.daggerproject.ui.b

import com.bmk.daggerproject.ui.d.PersonalInputRequest
import com.bmk.daggerproject.util.base.BasePresenter
import io.reactivex.rxkotlin.addTo
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
            .subscribe { view.empScreen(getData()) }
            .addTo(disposable)

        submitObs
            .filter { it }
            .subscribe { view.showError(validateInput()!!) }
            .addTo(disposable)
    }

    private fun getData(): PersonalInputRequest {
        return PersonalInputRequest(
            fName = view.getFirstName(),
            lName = view.getLastName(),
            mob = view.getMob(),
            gender = view.getGender(),
            dob = view.getDOB()
        )
    }

    fun validateInput(): String? {
        return when {
            view.getFirstName().isEmpty() -> "Please Enter First Name"
            view.getLastName().isEmpty() -> "Please Enter Last Name"
            view.getMob().isEmpty() -> "Please Enter Mobile No"
            view.getDOB().equals("Select DOB", true) -> "Please Select DOB"
            else -> null
        }
    }
}
