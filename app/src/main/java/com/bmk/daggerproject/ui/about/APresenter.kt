package com.bmk.daggerproject.ui.about

import com.bmk.daggerproject.domain.MatchRepository
import com.bmk.daggerproject.ui.base.BasePresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


class APresenter @Inject constructor(
    view: AContract,
    private val repository: MatchRepository
) :
    BasePresenter<AContract>(view) {
    override fun start() {
        repository.getMatchData()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ response ->
                view.showProgress(false)
                view.render(response)
            }, { error ->
                view.showProgress(false)
                view.showErrorMessage(error.localizedMessage)
            }).addTo(disposable)
    }
}