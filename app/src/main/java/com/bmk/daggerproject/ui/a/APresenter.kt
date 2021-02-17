package com.bmk.daggerproject.ui.a

import com.bmk.daggerproject.util.base.BasePresenter
import com.bmk.domain.MatchRepository
import io.reactivex.rxkotlin.addTo
import javax.inject.Inject


class APresenter @Inject constructor(
    view: AContract,
    private val repository: MatchRepository
) : BasePresenter<AContract>(view) {
    override fun start() {

        view.onImageClick()
            .subscribe { view.navigateToDetail() }
            .addTo(disposable)
    }
}