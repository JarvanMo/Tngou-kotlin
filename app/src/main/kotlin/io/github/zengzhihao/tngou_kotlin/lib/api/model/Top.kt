/*
 * Copyright 2015 zengzhihao.github.io. All rights reserved.
 * Support: http://zengzhihao.github.io
 */

package io.github.zengzhihao.tngou_kotlin.lib.api.model

/**
 * @author Kela.King
 */
data class Top(
        val title: String,
        val topclass: Int,
        val img: String,
        val description: String,
        val keywords: String,
        val message: String,
        val count: Int,
        val fcount: Int,
        val rcount: Int,
        val fromname: String,
        val fromurl: String,
        val time: Long
)

data class Result(
        val total: Long,
        val tngou: List<Top>
)
