package com.bmk.daggerproject.ui.b

import com.bmk.daggerproject.ui.d.PersonalInputRequest
import com.bmk.daggerproject.util.base.BasePresenter
import com.bmk.domain.MatchRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import javax.inject.Inject

class BPresenter @Inject constructor(
    view: BView,
    val id: Long?,
    val repository: MatchRepository
) : BasePresenter<BView>(view) {
    override fun start() {
        id?.let {
            if (it > 0L) {
                repository.getPersonalData(it)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ response ->
                        view.showProgress(false)
                        view.render(response)
                    }, { error ->
                        view.showProgress(false)
                        view.showErrorMessage(error.localizedMessage)
                    })
                    .addTo(disposable)
            }
        }
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
            id = id ?: -1,
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
