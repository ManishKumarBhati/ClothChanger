package com.bmk.daggerproject.ui.a

import com.bmk.daggerproject.util.base.BaseView
import com.bmk.domain.UserData
import io.reactivex.Observable

interface AContract : BaseView {
    fun onAddTopClick(): Observable<Unit>
    fun onAddBottomClick(): Observable<Unit>
    fun onShuffleClickCLick(): Observable<Unit>
    fun onAddImg(): Observable<UIEvent.AddImage>
    fun onScroll(): Observable<UIEvent.OnScroll>

    fun render(data: List<UserData>)
    fun renderImageSave(data: Long)
    fun renderFav(isFav: Boolean)
    fun openCamera(id: Int)
    fun onChkChange(): Observable<Boolean>
    fun handleCheckChange()
    fun getData(): Pair<Long, Long>

    companion object {
        const val REQUEST_CAPTURE_IMAGE = 100
        const val TOP = 1
        const val BOTTOM = 2
    }
}

sealed class UIEvent {
    data class AddImage(@JvmField val url: String, @JvmField val position: Int) : UIEvent()
    data class OnScroll(@JvmField val top: Long, @JvmField val bottom: Long) : UIEvent()
}