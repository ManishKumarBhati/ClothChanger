package com.bmk.daggerproject.api

import com.bmk.daggerproject.data.ApiDataResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by manish on 07/07/201820.
 */
interface ApiServiceInterface {
    @GET("search/1")
    fun getPostList(@Query("q") query: String): Observable<ApiDataResponse>
}