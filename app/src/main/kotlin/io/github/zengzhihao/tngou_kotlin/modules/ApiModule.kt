/*
 * Copyright 2015 zengzhihao.github.io. All rights reserved.
 * Support: http://zengzhihao.github.io
 */

package io.github.zengzhihao.tngou_kotlin.modules

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.squareup.okhttp.OkHttpClient
import dagger.Module
import dagger.Provides
import io.github.zengzhihao.tngou_kotlin.BuildConfig
import io.github.zengzhihao.tngou_kotlin.data.model.exception.ApiException
import io.github.zengzhihao.tngou_kotlin.lib.api.ApiDefaultConfig
import io.github.zengzhihao.tngou_kotlin.lib.api.service.TopService
import retrofit.Endpoint
import retrofit.RestAdapter
import retrofit.client.Client
import retrofit.client.OkClient
import retrofit.converter.Converter
import retrofit.converter.GsonConverter
import javax.inject.Singleton

/**
 * @author Kela.King
 */
@Module
class ApiModule {

    @Provides
    @Singleton
    fun provideEndpoint() = ApiDefaultConfig.END_POINT

    @Provides
    @Singleton
    fun provideClient(okHttpClient: OkHttpClient): Client = OkClient(okHttpClient)

    @Provides
    @Singleton
    fun provideGson() = GsonBuilder().create()

    @Provides
    @Singleton
    fun provideConverter(gson: Gson): Converter = GsonConverter(gson)

    @Provides
    @Singleton
    fun provideRestAdapter(endpoint: Endpoint, client: Client, converter: Converter): RestAdapter {
        val restAdapter = RestAdapter.Builder().setEndpoint(endpoint).setClient(client).setConverter(converter)
                .setErrorHandler { cause -> ApiException.create(cause) }.build()

        if (BuildConfig.DEBUG) {
            restAdapter.logLevel = RestAdapter.LogLevel.FULL
        } else {
            restAdapter.logLevel = RestAdapter.LogLevel.NONE
        }

        return restAdapter
    }

    @Provides
    @Singleton
    fun provideTopService(restAdapter: RestAdapter) = restAdapter.create<TopService>(TopService::class.java)
}
