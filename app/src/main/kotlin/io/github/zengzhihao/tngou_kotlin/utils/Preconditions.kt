/*
 * Copyright 2015 zengzhihao.github.io. All rights reserved.
 * Support: http://zengzhihao.github.io
 */

package io.github.zengzhihao.tngou_kotlin.utils

/**
 * @author Kela.King
 */
object Preconditions {

    fun <T> checkNotNull(t: T?): T {
        if (t == null)
            throw NullPointerException()
        return t
    }

    fun <T> checkNotNull(t: T?, value: Any): T {
        if (t == null)
            throw NullPointerException(value.toString())
        return t
    }
}
