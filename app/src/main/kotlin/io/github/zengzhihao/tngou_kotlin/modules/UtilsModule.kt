/*
 * Copyright 2015 zengzhihao.github.io. All rights reserved.
 * Support: http://zengzhihao.github.io
 */

package io.github.zengzhihao.tngou_kotlin.modules

import android.content.Context
import dagger.Module
import dagger.Provides
import io.github.zengzhihao.tngou_kotlin.core.qualifier.ForApplication
import io.github.zengzhihao.tngou_kotlin.utils.ToastHelper
import javax.inject.Singleton

/**
 * @author Kela.King
 */
@Module
class UtilsModule {

    @Provides
    @Singleton
    fun provideToastHelper(@ForApplication context: Context) = ToastHelper(context)
}
