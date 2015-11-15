/*
 * Copyright 2015 zengzhihao.github.io. All rights reserved.
 * Support: http://zengzhihao.github.io
 */

package io.github.zengzhihao.tngou_kotlin.data.model.exception

/**
 * @author Kela.King
 */
class ServerException(detailMessage: String, throwable: Throwable, val apiError: ApiError) : ApiException(detailMessage, throwable)
