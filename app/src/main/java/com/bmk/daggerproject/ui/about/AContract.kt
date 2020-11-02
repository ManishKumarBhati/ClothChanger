package com.bmk.daggerproject.ui.about

import com.bmk.daggerproject.domain.ResponseData
import com.bmk.daggerproject.ui.base.BaseView
import io.reactivex.Observable

/**
 * Created by manish on 07/07/201820.
 */
interface AContract : BaseView {
    fun onImageClick(): Observable<String>
    fun render(data: List<ResponseData>)
    fun navigateToDetail(id: String)
}