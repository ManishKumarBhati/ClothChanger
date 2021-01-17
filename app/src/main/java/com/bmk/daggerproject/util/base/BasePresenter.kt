package com.bmk.daggerproject.util.base

import androidx.annotation.CallSuper
import io.reactivex.disposables.CompositeDisposable

abstract class BasePresenter<out T : BaseView> constructor(@JvmField protected val view: T) {
    @JvmField
    protected val disposable: CompositeDisposable = CompositeDisposable()

    abstract fun start()

    @CallSuper
    open fun stop() {
        disposable.clear()
    }
}