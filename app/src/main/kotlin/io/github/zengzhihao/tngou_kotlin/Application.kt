/*
 * Copyright 2015 zengzhihao.github.io. All rights reserved.
 * Support: http://zengzhihao.github.io
 */

package io.github.zengzhihao.tngou_kotlin

import android.content.Context
import com.facebook.stetho.Stetho
import io.github.zengzhihao.tngou_kotlin.components.AppComponent
import io.github.zengzhihao.tngou_kotlin.components.DaggerAppComponent
import io.github.zengzhihao.tngou_kotlin.modules.AppModule
import timber.log.Timber

/**
 * @author Kela.King
 */
class Application : android.app.Application() {
    private var _appComponent: AppComponent? = null

    override fun onCreate() {
        super.onCreate()

        _buildAppComponent()
        _setupAnalytics()
    }

    private fun _buildAppComponent() {
        _appComponent = DaggerAppComponent.builder().appModule(AppModule(this)).build()
    }

    private fun _setupAnalytics() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
            Stetho.initialize(Stetho.newInitializerBuilder(this)
                    .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                    .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this)).build())
        }
    }

    public fun getAppComponent() = _appComponent!!

    companion object {
        fun getApplicationContext(context: Context) = context.applicationContext as Application
    }
}
