package com.alio.ulio.custom.recyclerLayout

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager

/**
 * @author Ha Jin Seok
 * @email seok270@dahami.com
 * @created 2022-01-20
 * @desc
 */
class LinearLayoutManager(context: Context) : LinearLayoutManager(context) {
    override fun canScrollVertically() = !SwipeMenuLayout.isSwiping

}