package com.bmk.daggerproject.ui.about

import com.bmk.daggerproject.util.base.BaseView
import com.bmk.domain.ResponseData
import io.reactivex.Observable

/**
 * Created by manish on 07/07/201820.
 */
interface AContract : BaseView {
    fun onImageClick(): Observable<String>
    fun getSearchText(): String
    fun onQueryTextChanged(): Observable<CharSequence>

    fun render(data: List<ResponseData>)
    fun navigateToDetail(id: String)
}