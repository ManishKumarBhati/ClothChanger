package com.bmk.daggerproject.ui.b

import com.bmk.daggerproject.ui.base.BaseView
import com.bmk.domain.DetailsData
import io.reactivex.Observable

interface BView : BaseView {
    fun getComment(): String
    fun onSubmitClick(): Observable<Unit>
    fun render(data: DetailsData)
}
