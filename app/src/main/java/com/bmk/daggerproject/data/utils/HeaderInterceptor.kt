package com.bmk.daggerproject.data.utils

import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException
import javax.inject.Inject

class HeaderInterceptor @Inject internal constructor() : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()

        val builder = original.newBuilder().apply {
            header(AUTHORIZATION, "Client-ID 137cda6b5008a7c")

        }

        return chain.proceed(builder.build())
    }

    companion object {
        private const val AUTHORIZATION = "Authorization"
    }
}
