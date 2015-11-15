/*
 * Copyright 2015 zengzhihao.github.io. All rights reserved.
 * Support: http://zengzhihao.github.io
 */

package io.github.zengzhihao.tngou_kotlin.core.rx

import rx.Observable
import rx.android.schedulers.AndroidSchedulers

/**
 * @author Kela.King
 */
class ScheduleTransformer {

    fun <T> bindOnMainThread_(observable: Observable<T>) = observable.observeOn(AndroidSchedulers.mainThread())
}
