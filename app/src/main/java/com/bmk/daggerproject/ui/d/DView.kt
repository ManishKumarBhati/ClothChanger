package com.bmk.daggerproject.ui.d

import com.bmk.daggerproject.util.base.BaseView
import com.bmk.domain.DetailsData
import io.reactivex.Observable

interface DView : BaseView {
    fun getBankName(): String
    fun getBranchName(): String
    fun getAcNo(): String
    fun getIfsc(): String
    fun onSubmitClick(): Observable<Unit>
    fun render()
}
