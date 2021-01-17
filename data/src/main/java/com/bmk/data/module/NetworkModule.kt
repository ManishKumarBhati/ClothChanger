package com.bmk.data.module

import android.content.Context
import android.util.Log
import androidx.room.Room
import com.bmk.data.ApiServiceInterface
import com.bmk.data.db.MatchDataBase
import com.bmk.data.utils.Constants
import com.bmk.data.utils.HeaderInterceptor
import com.bmk.domain.MatchRepository
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.internal.bind.DateTypeAdapter
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import javax.inject.Singleton


@Module
class NetworkModule {
    @Provides
    @Singleton
    fun provideGson(): Gson {
        return GsonBuilder()
            .registerTypeAdapter(EncodedString::class.java, EncodedStringTypeAdapter().nullSafe())
            .registerTypeAdapter(Date::class.java, DateTypeAdapter().nullSafe())
            .create()
    }

    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor(HttpLoggingInterceptor
            .Logger { Log.d("OkHttp", it) }
        ).setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    @Provides
    fun provideStethoInterceptor(): StethoInterceptor {
        return StethoInterceptor()
    }

    @Provides
    fun provideOkHttpClient(
        logger: HttpLoggingInterceptor,
        headerInterceptor: HeaderInterceptor,
        stethoInterceptor: StethoInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(headerInterceptor)
            .addInterceptor(stethoInterceptor)
            .addInterceptor(logger)
            .build()
    }

    @Provides
    fun provideRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//            .addConverterFactory(ScalarsConverterFactory.create()) this need to add when we dont wants to parse gson data
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(Constants.BASE_URL)
            .client(client)
            .build()
    }

    @Provides
    fun provideApiService(retrofit: Retrofit): ApiServiceInterface {
        return retrofit.create(ApiServiceInterface::class.java)
    }

    @Provides
    @Singleton
    fun provideAppDatabase(context: Context): MatchDataBase {
        return Room.databaseBuilder(context, MatchDataBase::class.java, "test_app_db")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun provideRepository(repository: com.bmk.data.MatchRepositoryImpl): MatchRepository {
        return repository
    }

}