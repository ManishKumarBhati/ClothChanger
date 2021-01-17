package com.bmk.daggerproject.ui.b

import android.util.Log
import com.bmk.daggerproject.ui.base.BasePresenter
import com.bmk.domain.MatchRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class BPresenter @Inject constructor(
    view: BView,
    private val id: String?,
    private val repository: MatchRepository

) : BasePresenter<BView>(view) {
    override fun start() {
        if (id != null) repository.getData(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ response ->
                view.showProgress(false)
                view.render(response)
            }, { error ->
                view.showProgress(false)
                view.showErrorMessage(error.localizedMessage)
            }).addTo(disposable)

        view.onSubmitClick()
            .map { view.getComment() }
            .filter { id != null }

            .switchMap {
                repository.saveComment(id = id!!, comment = it)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .toObservable()
            }
            .subscribe({ response ->
                view.showProgress(false)
                Log.d("bmk ", "save comment $response")
            }, { error ->
                view.showProgress(false)
                view.showErrorMessage(error.localizedMessage)
            }).addTo(disposable)
    }


}
