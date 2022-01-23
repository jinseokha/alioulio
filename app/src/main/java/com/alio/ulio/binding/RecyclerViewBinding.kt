package com.alio.ulio.binding

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.skydoves.baserecyclerviewadapter.BaseAdapter

/**
 * @author Ha Jin Seok
 * @email seok270@gmail.com
 * @created 2022-01-22
 * @desc
 */

object RecyclerViewBinding {

    @JvmStatic
    @BindingAdapter("adapter")
    fun bindAdapter(view : RecyclerView, baseAdapter: BaseAdapter) {
        view.adapter = baseAdapter
    }


}