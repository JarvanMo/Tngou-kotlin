/*
 * Copyright 2015 zengzhihao.github.io. All rights reserved.
 * Support: http://zengzhihao.github.io
 */

package io.github.zengzhihao.tngou_kotlin.ui.base

import android.os.Bundle
import com.trello.rxlifecycle.ActivityEvent
import com.trello.rxlifecycle.components.support.RxAppCompatActivity
import io.github.zengzhihao.tngou_kotlin.Application
import io.github.zengzhihao.tngou_kotlin.core.rx.ScheduleTransformer
import rx.Observable
import rx.schedulers.Schedulers
import javax.inject.Inject

/**
 * @author Kela.King
 */
open class AbstractActivity : RxAppCompatActivity() {
    @Inject
    lateinit var _scheduleTransformer: ScheduleTransformer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Application.getApplicationContext(this).getAppComponent().inject(this)
    }

    protected fun <T> bindToLifecycle_(observable: Observable<T>) =
            _scheduleTransformer.bindOnMainThread_(observable).compose(this.bindToLifecycle<T>())

    protected fun <T> bindUntilEvent_(observable: Observable<T>, activityEvent: ActivityEvent) =
            _scheduleTransformer.bindOnMainThread_(observable).compose(this.bindUntilEvent<T>(activityEvent))

    protected fun <T> bindOnIOScheduler_(observable: Observable<T>) =
            bindToLifecycle_(observable).subscribeOn(Schedulers.io())

    protected fun <T> bindOnIOScheduler_(observable: Observable<T>, activityEvent: ActivityEvent) =
            bindUntilEvent_(observable, activityEvent).subscribeOn(Schedulers.io())
}
