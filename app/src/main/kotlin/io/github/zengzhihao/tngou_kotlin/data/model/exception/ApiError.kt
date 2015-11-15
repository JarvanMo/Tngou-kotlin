/*
 * Copyright 2015 zengzhihao.github.io. All rights reserved.
 * Support: http://zengzhihao.github.io
 */

package io.github.zengzhihao.tngou_kotlin.data.model.exception

/**
 * @author Kela.King
 */
data class ApiError(
        var _code: Long = -1L,
        var _msg: String
)
