package com.bmk.daggerproject.ui.d

import com.bmk.daggerproject.util.base.BasePresenter
import com.bmk.domain.DataRequest
import com.bmk.domain.MatchRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class DPresenter @Inject constructor(
    view: DView,
    val pageData: EmployeeInputRequest?,
    private val repository: MatchRepository
) : BasePresenter<DView>(view) {
    override fun start() {
        val submitObs = view.onSubmitClick().map { validateInput() != null }.share()

        submitObs
            .filter { !it && pageData != null }
            .map {
                repository.saveData(getData())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
            }.subscribe({ response ->
                view.showProgress(false)
//                view.backToHome()
            }, { error ->
                view.showProgress(false)
                view.showErrorMessage(error.localizedMessage)
            })
            .addTo(disposable)

        submitObs
            .filter { it }
            .subscribe { view.showError(validateInput()!!) }
            .addTo(disposable)
    }

    private fun getData(): DataRequest {
        return DataRequest(
            fName = pageData!!.personalInfo.fName,
            lName = pageData.personalInfo.lName,
            mob = pageData.personalInfo.mob,
            gender = pageData.personalInfo.gender,
            dob = pageData.personalInfo.dob,
            empNo = pageData.empNo,
            empName = pageData.empName,
            empdesg = pageData.empdesg,
            accountType = pageData.accountType,
            exp = pageData.exp,
            bankName = view.getBankName(),
            branch = view.getBranchName().value,
            acNo = view.getAcNo(),
            ifscCode = view.getIfsc(),
            image = ""
        )
    }

    private fun validateInput(): String? {
        return when {
            view.getBankName().isEmpty() -> "Please Enter Branch Name"
            view.getBranchName().id == -1 -> "Please Select branch"
            view.getAcNo().isEmpty() -> "Please Enter A/C No"
            view.getIfsc().isEmpty() -> "Please Enter IFSC Code"
            else -> null
        }
    }
}
