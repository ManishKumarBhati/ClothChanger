package com.bmk.daggerproject.ui.c

import android.util.Log
import com.bmk.daggerproject.util.base.BasePresenter
import com.bmk.domain.MatchRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class CPresenter @Inject constructor(
    view: CView,
    private val repository: MatchRepository
) : BasePresenter<CView>(view) {
    override fun start() {
        view.onSubmitClick()
            .subscribe { view.render() }
            .addTo(disposable)
    }
}
