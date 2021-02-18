package com.bmk.daggerproject.ui.c

import com.bmk.daggerproject.util.base.BaseView
import com.bmk.domain.DetailsData
import io.reactivex.Observable

interface CView : BaseView {
    fun getEmpNo(): String
    fun getEmpName(): String
    fun getEmpDesg(): String
    fun getEmpAcType(): String
    fun getEmpExp(): String
    fun onSubmitClick(): Observable<Unit>
    fun render()
    fun bankScreen()
}
