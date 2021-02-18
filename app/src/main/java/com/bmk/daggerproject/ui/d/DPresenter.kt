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
        view.onSubmitClick()
            .subscribe { view.render() }
            .addTo(disposable)
    }
}
