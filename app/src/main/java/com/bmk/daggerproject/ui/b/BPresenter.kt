package com.bmk.daggerproject.ui.b

import android.util.Log
import com.bmk.daggerproject.util.base.BasePresenter
import com.bmk.domain.MatchRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class BPresenter @Inject constructor(
    view: BView,
    private val repository: MatchRepository
) : BasePresenter<BView>(view) {
    override fun start() {
        view.onDobClick()
            .subscribe { view.showDatePicker() }
            .addTo(disposable)
    }
}
