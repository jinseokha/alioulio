package com.alio.ulio.custom

import android.graphics.Outline
import android.os.Build
import android.view.View
import android.view.ViewOutlineProvider
import android.widget.ImageView
import androidx.annotation.RequiresApi

/**
 * @author Ha Jin Seok
 * @email seok270@gmail.com
 * @created 2022-04-10
 * @desc
 */

fun roundLeft(iv: ImageView, curveRadius : Float)  : ImageView {

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

        iv.outlineProvider = object : ViewOutlineProvider() {

            @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
            override fun getOutline(view: View?, outline: Outline?) {
                outline?.setRoundRect(0, 0, (view!!.width+curveRadius).toInt(), (view.height).toInt(), curveRadius)
            }
        }

        iv.clipToOutline = true
    }
    return iv
}

fun roundAll(iv: ImageView, curveRadius : Float)  : ImageView {

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

        iv.outlineProvider = object : ViewOutlineProvider() {

            @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
            override fun getOutline(view: View?, outline: Outline?) {
                outline?.setRoundRect(0, 0, view!!.width, view.height, curveRadius)
            }
        }

        iv.clipToOutline = true
    }
    return iv
}

fun roundTop(iv: ImageView, curveRadius : Float)  : ImageView {

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

        iv.outlineProvider = object : ViewOutlineProvider() {

            @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
            override fun getOutline(view: View?, outline: Outline?) {
                outline?.setRoundRect(0, 0, view!!.width, (view.height+curveRadius).toInt(), curveRadius)
            }
        }

        iv.clipToOutline = true
    }
    return iv
}