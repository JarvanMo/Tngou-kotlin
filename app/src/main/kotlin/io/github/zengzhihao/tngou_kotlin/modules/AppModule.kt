/*
 * Copyright 2015 zengzhihao.github.io. All rights reserved.
 * Support: http://zengzhihao.github.io
 */

package io.github.zengzhihao.tngou_kotlin.modules

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import io.github.zengzhihao.tngou_kotlin.core.qualifier.ForApplication
import io.github.zengzhihao.tngou_kotlin.utils.Preconditions
import javax.inject.Singleton

/**
 * @author Kela.King
 */
@Module
class AppModule {
    private val _context: Context

    constructor(application: Application) {
        _context = Preconditions.checkNotNull(application, "application context can't be null.")
    }

    @Provides
    @Singleton
    @ForApplication
    fun provideApplicationContext() = _context
}
