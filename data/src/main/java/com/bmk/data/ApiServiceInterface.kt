package com.bmk.data

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServiceInterface {
    @GET("search/1")
    fun getPostList(@Query("q") query: String): Observable<com.bmk.data.ApiDataResponse>
}