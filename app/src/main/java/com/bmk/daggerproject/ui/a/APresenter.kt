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

        repository.getImageList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ response ->
                view.showProgress(false)
                view.render(response)
            }, { error ->
                view.showProgress(false)
                view.showErrorMessage(error.localizedMessage)
            }).addTo(disposable)

        view.onShuffleClickCLick().switchMap {
            repository.getImageList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
        }.subscribe({ response ->
            view.showProgress(false)
            view.render(response)
        }, { error ->
            view.showProgress(false)
            view.showErrorMessage(error.localizedMessage)
        }).addTo(disposable)


        Observable.merge(
            view.onAddTopClick().map { AContract.TOP },
            view.onAddBottomClick().map { AContract.BOTTOM })
            .subscribe(view::openCamera)
            .addTo(disposable)


        view.onAddImg()
            .switchMap {
                repository.saveImage(it.url, it.position).subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
            }
            .subscribe({ response ->
                view.showProgress(false)
                view.renderImageSave(response)
            }, { error ->
                view.showProgress(false)
                view.showErrorMessage(error.localizedMessage)
            }).addTo(disposable)

        view.onScroll()
            .filter {
                it.top != -1L && it.bottom != -1L
            }.switchMap {
                repository.getFavData(it.top, it.bottom).subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
            }
            .subscribe({ response ->
                view.showProgress(false)
                view.renderFav(response)
            }, { error ->
                view.showProgress(false)
                view.showErrorMessage(error.localizedMessage)
            }).addTo(disposable)

        view.onChkChange()
            .skip(1)
            .distinctUntilChanged()
            .map { view.getData() to it }
            .filter {
                it.first.first != -1L && it.first.second != -1L
            }
            .switchMap { (id, isChecked) ->
                repository.getCount(id)
                    .flatMap {
                        if (it > 0) repository.updateFavData(id, isChecked)
                        else repository.saveFav(id, isChecked)
                    }
                    .distinctUntilChanged()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
            }.subscribe({ response ->
                view.showProgress(false)
                view.handleCheckChange()
            }, { error ->
                view.showProgress(false)
                view.showErrorMessage(error.localizedMessage)
            }).addTo(disposable)
    }
}