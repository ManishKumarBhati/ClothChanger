package com.bmk.daggerproject.api

import com.bmk.daggerproject.models.Album
import com.bmk.daggerproject.models.Post
import com.bmk.daggerproject.models.User
import com.bmk.daggerproject.util.Constants
import io.reactivex.Observable
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by manish on 07/07/201820.
 */
interface ApiServiceInterface {

    @GET("albums")
    fun getAlbumList(): Observable<List<Album>>

    @GET("albums/{id}")
    fun getAlbum(@Path("id") id: Int): Observable<Album>

    @DELETE("albums/{id}")
    fun deleteAlbum(@Path("id") id: Int)

    @GET("posts")
    fun getPostList(): Observable<List<Post>>

    @GET("posts/{id}")
    fun getPost(@Path("id") id: Int): Observable<Post>

    @DELETE("albums/{id}")
    fun deletePost(@Path("id") id: Int)

    @GET("users")
    fun getUserList(): Observable<List<User>>

    @GET("posts/{id}")
    fun getUser(@Path("id") id: Int): Observable<User>

    @DELETE("albums/{id}")
    fun deleteUser(@Path("id") id: Int)

    companion object Factory {
        fun create(client: OkHttpClient): ApiServiceInterface {

            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Constants.BASE_URL)
                .client(client)
                .build()

            return retrofit.create(ApiServiceInterface::class.java)
        }
    }
}