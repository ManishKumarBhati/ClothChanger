package com.bmk.daggerproject.ui.c

import com.bmk.daggerproject.ui.d.EmployeeInputRequest
import com.bmk.daggerproject.ui.d.PersonalInputRequest
import com.bmk.daggerproject.util.base.BasePresenter
import io.reactivex.rxkotlin.addTo
import javax.inject.Inject

class CPresenter @Inject constructor(
    view: CView,
    val pageData: PersonalInputRequest?
) : BasePresenter<CView>(view) {
    override fun start() {
        val submitObs = view.onSubmitClick().map { validateInput() != null }.share()

        submitObs
            .filter { !it && pageData != null }
            .subscribe { view.bankScreen(getData()) }
            .addTo(disposable)

        submitObs
            .filter { it }
            .subscribe { view.showError(validateInput()!!) }
            .addTo(disposable)
    }

    private fun getData(): EmployeeInputRequest {
        return EmployeeInputRequest(
            personalInfo = pageData!!,
            empNo = view.getEmpNo(),
            empName = view.getEmpName(),
            empdesg = view.getEmpDesg(),
            accountType = view.getEmpAcType().value,
            exp = view.getEmpExp().value
        )
    }

    fun validateInput(): String? {
        return when {
            view.getEmpNo().isEmpty() -> "Please Enter Emp No"
            view.getEmpName().isEmpty() -> "Please Enter Name"
            view.getEmpDesg().isEmpty() -> "Please Enter Designation"
            view.getEmpAcType().id == -1 -> "Please Select A/C Type"
            view.getEmpExp().id == -1 -> "Please Select Experience"
            else -> null
        }
    }
}
