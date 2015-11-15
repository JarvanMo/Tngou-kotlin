/*
 * Copyright 2015 zengzhihao.github.io. All rights reserved.
 * Support: http://zengzhihao.github.io
 */

package io.github.zengzhihao.tngou_kotlin.modules

import android.content.Context
import com.facebook.stetho.okhttp.StethoInterceptor
import com.squareup.okhttp.Cache
import com.squareup.okhttp.OkHttpClient
import com.squareup.otto.Bus
import com.squareup.picasso.OkHttpDownloader
import com.squareup.picasso.Picasso
import dagger.Module
import dagger.Provides
import io.github.zengzhihao.tngou_kotlin.BuildConfig
import io.github.zengzhihao.tngou_kotlin.core.EventBus
import io.github.zengzhihao.tngou_kotlin.core.qualifier.ForApplication
import io.github.zengzhihao.tngou_kotlin.core.rx.ScheduleTransformer
import timber.log.Timber
import java.io.File
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * @author Kela.King
 */
@Module
class DataModule {

    fun createOkHttpClient(context: Context): OkHttpClient {
        val okHttpClient = OkHttpClient()
        okHttpClient.setConnectTimeout(15000L, TimeUnit.MILLISECONDS)
        okHttpClient.setReadTimeout(20000L, TimeUnit.MILLISECONDS)

        if (BuildConfig.DEBUG) {
            okHttpClient.networkInterceptors().add(StethoInterceptor())
            okHttpClient.setCache(Cache(File(context.externalCacheDir, "http-cache"),
                    20 * 1024 * 1024.toLong()))
        } else {
            okHttpClient.setCache(Cache(File(context.cacheDir, "http-cache"), 20 * 1024 * 1024.toLong()))
        }

        return okHttpClient
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(@ForApplication context: Context) = createOkHttpClient(context)

    @Provides
    @Singleton
    fun provideSharedPreferences(@ForApplication context: Context) = context.getSharedPreferences(context.packageName, Context.MODE_PRIVATE)

    @Provides
    @Singleton
    fun providePicasso(@ForApplication context: Context, okHttpClient: OkHttpClient): Picasso {
        val picasso = Picasso.Builder(context).downloader(OkHttpDownloader(okHttpClient))
                .listener { picasso, uri, exception -> Timber.e(exception, "### Failed to load image: %s", uri) }
                .build()

        if (BuildConfig.DEBUG)
            picasso.setIndicatorsEnabled(true)

        return picasso
    }

    @Provides
    @Singleton
    fun provideReactiveTransformer() = ScheduleTransformer()

    @Provides
    @Singleton
    fun provideEventBus(): Bus = EventBus.newInstance()
}
