/*
 * Copyright 2015 zengzhihao.github.io. All rights reserved.
 * Support: http://zengzhihao.github.io
 */

package io.github.zengzhihao.tngou_kotlin.data.model.exception

/**
 * @author Kela.King
 */
class UnexpectedException(detailMessage: String, throwable: Throwable) : ApiException(detailMessage, throwable)
