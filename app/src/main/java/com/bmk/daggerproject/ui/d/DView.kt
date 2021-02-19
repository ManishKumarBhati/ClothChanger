package com.bmk.daggerproject.ui.d

import com.bmk.daggerproject.util.base.BaseView
import com.bmk.domain.BankData
import com.bmk.domain.KeyValue
import io.reactivex.Observable

interface DView : BaseView {
    fun getBankName(): String
    fun getBranchName(): KeyValue
    fun getAcNo(): String
    fun getIfsc(): String
    fun getImageURL(): String?
    fun onSubmitClick(): Observable<Unit>
    fun backToHome()
    fun showToast(error: String)
    fun render(data: BankData)
}
