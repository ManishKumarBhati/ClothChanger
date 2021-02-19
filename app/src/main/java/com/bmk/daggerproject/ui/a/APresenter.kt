package com.bmk.daggerproject.ui.a

import com.bmk.daggerproject.util.base.BasePresenter
import com.bmk.domain.MatchRepository
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


class APresenter @Inject constructor(
    view: AContract,
    private val repository: MatchRepository
) : BasePresenter<AContract>(view) {
    override fun start() {
        repository.getData()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ response ->
                view.showProgress(false)
                view.render(response)
            }, { error ->
                view.showProgress(false)
                view.showErrorMessage(error.localizedMessage)
            }).addTo(disposable)
            .addTo(disposable)

        Observable.merge(view.onAddClick().map { -1L }, view.onitemClick().map { it.id })
            .subscribe(view::navigateToDetail)
            .addTo(disposable)
    }
}