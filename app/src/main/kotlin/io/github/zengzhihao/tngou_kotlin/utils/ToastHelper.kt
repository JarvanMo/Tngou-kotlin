/*
 * Copyright 2015 zengzhihao.github.io. All rights reserved.
 * Support: http://zengzhihao.github.io
 */

package io.github.zengzhihao.tngou_kotlin.utils

import android.content.Context
import android.widget.Toast

/**
 * @author Kela.King
 */
class ToastHelper(private val _context: Context) {

    fun show(message: String) = Toast.makeText(_context, message, Toast.LENGTH_SHORT).show()
}
