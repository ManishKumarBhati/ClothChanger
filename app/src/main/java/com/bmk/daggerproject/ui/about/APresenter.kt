package com.bmk.daggerproject.ui.about

import com.bmk.daggerproject.util.base.BasePresenter
import com.bmk.domain.MatchRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit
import javax.inject.Inject


class APresenter @Inject constructor(
    view: AContract,
    private val repository: MatchRepository
) : BasePresenter<AContract>(view) {
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

        view.onQueryTextChanged()
            .skip(1)
            .debounce(500, TimeUnit.MILLISECONDS)
            .map { it.toString().trim() }
            .distinctUntilChanged()
            .filter { it.isNotEmpty() && it.length >= 2 }
            .switchMap {
                view.showProgress(true)
                repository.getMatchData(view.getSearchText())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
            }.subscribe({ response ->
                view.showProgress(false)
                view.render(response)
            }, { error ->
                view.showProgress(false)
                view.showErrorMessage(error.localizedMessage)
            }).addTo(disposable)

        view.onImageClick()
            .subscribe(view::navigateToDetail)
            .addTo(disposable)
    }
}