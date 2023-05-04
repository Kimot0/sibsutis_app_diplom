package com.example.diplom.data.remote.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit


class Network(
    private val interceptor: SupportInterceptor
): INetwork {

    private val moshi = Moshi.Builder() // adapter
        .add(KotlinJsonAdapterFactory())
        .build()

    override val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(Urls.SibsutisURLS().BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(buildClient())
            .build()
    }

    private fun buildClient() : OkHttpClient  {
        return  OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .connectTimeout(20, TimeUnit.SECONDS)
            .readTimeout(20, TimeUnit.SECONDS)
            .addInterceptor(EmptyBodyInterceptor)
            .addInterceptor(HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY })
            .build()
    }

}