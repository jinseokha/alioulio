package com.alio.ulio.binding

import android.view.View
import android.view.animation.AlphaAnimation
import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import com.alio.ulio.custom.calendar.utils.setSelectedDayColors
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

    @JvmStatic
    @BindingAdapter(value = ["android:visibility", "visibleAnimType", "goneAnimType"], requireAll = true)
    fun animationBindingMethod(view: View,
                               visible: Boolean,
                               visibleAnimType: Int,
                               goneAnimType: Int) {

        if(visible) {
            when(visibleAnimType) {
                1   ->  {
                    /*val anim = AlphaAnimation(0.0f, 1.0f).apply {
                        fillAfter = true
                        duration = 700
                    }
                    view.startAnimation(anim)*/
                    view.visibility = View.VISIBLE
                }
            }
        } else {
            when(goneAnimType) {
                2   ->  {
                    /*val anim = AlphaAnimation(1.0f, 0.0f).apply {
                        fillAfter = true
                        duration = 700
                    }
                    view.startAnimation(anim)*/
                    view.visibility = View.GONE
                }
            }
        }
    }

    @JvmStatic
    @BindingAdapter("selected")
    fun setSelected(view: View, selected: Boolean) {
        view.isSelected = selected
    }
}