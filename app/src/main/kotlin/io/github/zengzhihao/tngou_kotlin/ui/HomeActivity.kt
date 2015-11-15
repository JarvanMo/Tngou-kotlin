/*
 * Copyright 2015 zengzhihao.github.io. All rights reserved.
 * Support: http://zengzhihao.github.io
 */

package io.github.zengzhihao.tngou_kotlin.ui

import android.os.Bundle
import com.squareup.otto.Bus
import com.squareup.otto.Subscribe
import io.github.zengzhihao.tngou_kotlin.Application
import io.github.zengzhihao.tngou_kotlin.ui.base.AbstractActivity
import io.github.zengzhihao.tngou_kotlin.ui.top.TopActivity
import io.github.zengzhihao.tngou_kotlin.utils.ToastHelper
import javax.inject.Inject

/**
 * @author Kela.King
 */
class HomeActivity : AbstractActivity() {
    @Inject
    lateinit var _toastHelper: ToastHelper
    @Inject
    lateinit var _bus: Bus

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Application.getApplicationContext(this).getAppComponent().inject(this)

        _bus.register(this)
        _toastHelper.show("Hello Tngou!")
        _bus.post(OnStartTopActivityEvent())
    }

    @Subscribe
    fun onStartTopActivity(event: OnStartTopActivityEvent) {
        TopActivity.start(this);
        finish();
    }

    override fun onDestroy() {
        _bus.unregister(this);
        super.onDestroy()
    }

    companion object {
        class OnStartTopActivityEvent {

        }
    }
}
