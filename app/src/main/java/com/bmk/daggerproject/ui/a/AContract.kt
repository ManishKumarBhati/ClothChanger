package com.bmk.daggerproject.ui.a

import com.bmk.daggerproject.util.base.BaseView
import com.bmk.domain.ResponseData
import io.reactivex.Observable

interface AContract : BaseView {
    fun onImageClick(): Observable<Unit>
    fun render(data: List<ResponseData>)
    fun navigateToDetail()
}