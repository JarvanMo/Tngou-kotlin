/*
 * Copyright 2015 zengzhihao.github.io. All rights reserved.
 * Support: http://zengzhihao.github.io
 */

package io.github.zengzhihao.tngou_kotlin.lib.api.service

import io.github.zengzhihao.tngou_kotlin.lib.api.model.Result
import retrofit.http.GET
import rx.Observable

/**
 * @author Kela.King
 */
interface TopService {

    @GET("/top/list")
    fun list(): Observable<Result>
}
