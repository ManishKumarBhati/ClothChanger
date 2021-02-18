package com.bmk.daggerproject.ui.a

import com.bmk.daggerproject.util.base.BaseView
import com.bmk.domain.DataResponse
import io.reactivex.Observable

interface AContract : BaseView {
    fun onImageClick(): Observable<Unit>
    fun render(data: List<DataResponse>)
    fun navigateToDetail()
}