/*
 * Copyright 2015 zengzhihao.github.io. All rights reserved.
 * Support: http://zengzhihao.github.io
 */

package io.github.zengzhihao.tngou_kotlin.lib.api

import retrofit.Endpoints

/**
 * @author Kela.King
 */
class ApiDefaultConfig {

    companion object {
        val END_POINT = Endpoints.newFixedEndpoint("http://www.tngou.net/api", "base-url")
        val IMG_URL = "http://tnfs.tngou.net/img"
    }
}
