package com.bmk.daggerproject.data.utils

import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException
import java.util.*
import javax.inject.Inject

class HeaderInterceptor @Inject internal constructor() : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()

        val builder = original.newBuilder().apply {
            header("User-Agent", "test")
            header("X-Request-ID", UUID.randomUUID().toString())
        }

        return chain.proceed(builder.build())
    }

    companion object {
        private const val API_HEADER_DATE = "Date"
        private const val API_HEADER_APP_ID = "appID"
        private const val API_HEADER_CLIENT_CODE = "clientCode"
    }
}
