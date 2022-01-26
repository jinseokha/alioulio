package com.alio.ulio.binding

import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

/**
 * @author Ha Jin Seok
 * @email seok270@gmail.com
 * @created 2022-01-22
 * @desc
 */

object ViewBinding {

    @JvmStatic
    @BindingAdapter("loadProfileImage")
    fun bindLoadProfileImage(view: AppCompatImageView, url : String) {
        Glide.with(view.context)
            .load(url)
            .apply(RequestOptions().transform(MyTransformation(view.context, 300, MyTransformation.CornerType.ALL)))
            .into(view)
    }
}