/*
 * Copyright 2015 zengzhihao.github.io. All rights reserved.
 * Support: http://zengzhihao.github.io
 */

package io.github.zengzhihao.tngou_kotlin.components

import dagger.Component
import io.github.zengzhihao.tngou_kotlin.modules.ApiModule
import io.github.zengzhihao.tngou_kotlin.modules.AppModule
import io.github.zengzhihao.tngou_kotlin.modules.DataModule
import io.github.zengzhihao.tngou_kotlin.modules.UtilsModule
import io.github.zengzhihao.tngou_kotlin.ui.HomeActivity
import io.github.zengzhihao.tngou_kotlin.ui.base.AbstractActivity
import io.github.zengzhihao.tngou_kotlin.ui.top.TopActivity
import javax.inject.Singleton

/**
 * @author Kela.King
 */
@Singleton
@Component(modules = arrayOf(AppModule::class, DataModule::class, ApiModule::class, UtilsModule::class))
interface AppComponent {

    fun inject(abstractActivity: AbstractActivity)
    fun inject(homeActivity: HomeActivity)
    fun inject(topActivity: TopActivity)
}
