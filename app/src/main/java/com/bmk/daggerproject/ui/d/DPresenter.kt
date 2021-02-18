package com.bmk.daggerproject.ui.d

import android.util.Log
import com.bmk.daggerproject.util.base.BasePresenter
import com.bmk.domain.MatchRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class DPresenter @Inject constructor(
    view: DView,
    private val repository: MatchRepository
) : BasePresenter<DView>(view) {
    override fun start() {
        val submitObs = view.onSubmitClick().map { validateInput() != null }.share()

        submitObs
            .filter { !it }
//            .map { repository.saveData() }
            .subscribe { view.backToHome() }
            .addTo(disposable)

        submitObs
            .filter { it }
            .subscribe { view.showError(validateInput()!!) }
            .addTo(disposable)
    }

    fun validateInput(): String? {
        return when {
            view.getBankName().isEmpty() -> "Please Enter Branch Name"
            view.getBranchName().id == -1 -> "Please Select branch"
            view.getAcNo().isEmpty() -> "Please Enter A/C No"
            view.getIfsc().isEmpty() -> "Please Enter IFSC Code"
            else -> null
        }
    }
}
