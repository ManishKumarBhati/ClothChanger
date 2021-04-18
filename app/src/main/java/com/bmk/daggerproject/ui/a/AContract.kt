package com.bmk.daggerproject.ui.a

import com.bmk.daggerproject.util.base.BaseView
import com.bmk.domain.UserData
import io.reactivex.Observable

interface AContract : BaseView {
    fun onAddTopClick(): Observable<Unit>
    fun onAddBottomClick(): Observable<Unit>
    fun onShuffleClickCLick(): Observable<Unit>
    fun onAddImg(): Observable<UIEvent.AddImage>
    fun render(data: List<UserData>)
    fun renderImageSave(data: Long)
    fun navigateToDetail(id: Long)
    fun openCamera(id: Int)

    companion object {
        const val TOP = 1
        const val BOTTOM = 2
    }
}

sealed class UIEvent {
    data class AddImage(@JvmField val url: String, @JvmField val position: Int) : UIEvent()
}