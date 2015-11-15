/*
 * Copyright 2015 zengzhihao.github.io. All rights reserved.
 * Support: http://zengzhihao.github.io
 */

package io.github.zengzhihao.tngou_kotlin.ui.top

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import io.github.zengzhihao.tngou_kotlin.R
import io.github.zengzhihao.tngou_kotlin.lib.api.ApiDefaultConfig
import io.github.zengzhihao.tngou_kotlin.lib.api.model.Top
import java.text.DateFormat
import java.util.*

/**
 * @author Kela.King
 */
class TopAdapter(val _context: Context, val _picasso: Picasso) : BaseAdapter() {
    private var _result = ArrayList<Top>()
    private var _layoutInflater = LayoutInflater.from(_context)

    fun setResult(result: List<Top>) {
        _result.addAll(result)
        notifyDataSetChanged()
    }

    override fun getCount() = _result.size

    override fun getItem(position: Int) = _result[position]

    override fun getItemId(position: Int) = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
        var viewHolder: ViewHolder
        var view: View

        if (convertView == null) {
            view = _layoutInflater.inflate(R.layout.item_common, parent, false)
            viewHolder = ViewHolder(view)
            view.tag = viewHolder
        } else {
            view = convertView
            viewHolder = view.tag as ViewHolder
        }

        _picasso.load(ApiDefaultConfig.IMG_URL + _result[position].img)
                .placeholder(R.drawable.list_icon_no_image)
                .error(R.drawable.list_icon_error_image).into(viewHolder.icon)

        viewHolder.title.text = _result[position].title
        viewHolder.tag.text = _result[position].keywords
        viewHolder.time.text = DateFormat.getDateTimeInstance().format(_result[position].time)
        viewHolder.browser.text = "浏览：" + _result[position].count

        return view
    }

    class ViewHolder(view: View) {
        var icon: ImageView = view.findViewById(R.id.list_item_icon) as ImageView
        var title: TextView = view.findViewById(R.id.list_item_title) as TextView
        var tag: TextView = view.findViewById(R.id.list_item_tag) as TextView
        var time: TextView = view.findViewById(R.id.list_item_time) as TextView
        var browser: TextView = view.findViewById(R.id.list_item_browser) as TextView
    }
}
