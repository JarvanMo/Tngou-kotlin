/*
 * Copyright 2015 zengzhihao.github.io. All rights reserved.
 * Support: http://zengzhihao.github.io
 */

package io.github.zengzhihao.tngou_kotlin.ui.top

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.ListView
import com.squareup.picasso.Picasso
import com.trello.rxlifecycle.ActivityEvent
import io.github.zengzhihao.tngou_kotlin.Application
import io.github.zengzhihao.tngou_kotlin.R
import io.github.zengzhihao.tngou_kotlin.lib.api.model.Result
import io.github.zengzhihao.tngou_kotlin.lib.api.service.TopService
import io.github.zengzhihao.tngou_kotlin.ui.base.AbstractActivity
import io.github.zengzhihao.tngou_kotlin.utils.bindView
import rx.Observer
import rx.Subscription
import timber.log.Timber
import javax.inject.Inject

/**
 * @author Kela.King
 */
class TopActivity : AbstractActivity() {
    @Inject
    lateinit var _topService: TopService
    @Inject
    lateinit var _picasso: Picasso

    val _listView by bindView<ListView>(R.id.listView)

    private var _topAdapter: TopAdapter? = null
    private var _subscription: Subscription? = null

    override protected fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Application.getApplicationContext(this).getAppComponent().inject(this)
        setContentView(R.layout.activity_top)

        _topAdapter = TopAdapter(this, _picasso)
        _listView.adapter = _topAdapter

        _subscription = bindOnIOScheduler_(_topService.list(), ActivityEvent.PAUSE).subscribe(object : Observer<Result> {
            override fun onCompleted() {
                Timber.i("### onCompleted.")
            }

            override fun onError(e: Throwable) {
                Timber.e("### onError. error is %s", e)
            }

            override fun onNext(result: Result) {
                _topAdapter!!.setResult(result.tngou)
            }
        })
    }

    override protected fun onResume() {
        super.onResume()
        Timber.i("### onResume. subscription is unsubscribed ? %s", _subscription!!.isUnsubscribed)
    }

    override protected fun onPause() {
        super.onPause()
        Timber.i("### onPause. subscription is unsubscribed ? %s", _subscription!!.isUnsubscribed)
    }

    companion object {

        fun start(context: Context) = context.startActivity(Intent(context, TopActivity::class.java))
    }
}
