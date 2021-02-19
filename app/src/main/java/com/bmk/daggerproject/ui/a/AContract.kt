package com.bmk.daggerproject.ui.a

import com.bmk.daggerproject.util.base.BaseView
import com.bmk.domain.DataResponse
import io.reactivex.Observable

interface AContract : BaseView {
    fun onAddClick(): Observable<Unit>
    fun onitemClick(): Observable<DataResponse>
    fun render(data: List<DataResponse>)
    fun navigateToDetail(id: Long)
}