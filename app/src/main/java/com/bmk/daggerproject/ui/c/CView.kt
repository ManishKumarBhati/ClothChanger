package com.bmk.daggerproject.ui.c

import com.bmk.daggerproject.ui.d.EmployeeInputRequest
import com.bmk.daggerproject.util.base.BaseView
import com.bmk.domain.EmployeeData
import com.bmk.domain.KeyValue
import io.reactivex.Observable

interface CView : BaseView {
    fun getEmpNo(): String
    fun getEmpName(): String
    fun getEmpDesg(): String
    fun getEmpAcType(): KeyValue
    fun getEmpExp(): KeyValue
    fun showError(error: String)
    fun onSubmitClick(): Observable<Unit>
    fun bankScreen(data: EmployeeInputRequest)
    fun render(data: EmployeeData)
}
