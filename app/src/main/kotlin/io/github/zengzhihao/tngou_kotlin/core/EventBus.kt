/*
 * Copyright 2015 zengzhihao.github.io. All rights reserved.
 * Support: http://zengzhihao.github.io
 */

package io.github.zengzhihao.tngou_kotlin.core

import android.os.Handler
import android.os.Looper
import com.squareup.otto.Bus

/**
 * @author Kela.King
 */
class EventBus : Bus() {

    private val _handler: Handler by lazy { Handler(Looper.getMainLooper()) }

    override fun post(event: Any?) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            super.post(event)
        } else {
            _handler.post { super@EventBus.post(event) }
        }
    }

    companion object {
        fun newInstance() = EventBus()
    }
}
