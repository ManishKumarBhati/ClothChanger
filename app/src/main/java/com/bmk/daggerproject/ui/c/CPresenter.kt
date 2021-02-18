package com.bmk.daggerproject.ui.c

import com.bmk.daggerproject.util.base.BasePresenter
import com.bmk.domain.MatchRepository
import io.reactivex.rxkotlin.addTo
import javax.inject.Inject

class CPresenter @Inject constructor(view: CView) : BasePresenter<CView>(view) {
    override fun start() {
        val submitObs = view.onSubmitClick().map { validateInput() != null }.share()

        submitObs
            .filter { !it }
            .subscribe { view.bankScreen() }
            .addTo(disposable)

        submitObs
            .filter { it }
            .subscribe { view.showError(validateInput()!!) }
            .addTo(disposable)
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
