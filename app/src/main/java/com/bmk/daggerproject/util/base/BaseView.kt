package com.bmk.daggerproject.util.base


interface BaseView {
    fun showErrorMessage(error: String?)
    fun showProgress(show: Boolean)

}