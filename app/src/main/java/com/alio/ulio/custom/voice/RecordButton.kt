package com.alio.ulio.custom.voice

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageButton
import com.alio.ulio.R

/**
 * @author Ha Jin Seok
 * @email seok270@gmail.com
 * @created 2022-02-03
 * @desc
 */

class RecordButton(
    context : Context,
    attrs : AttributeSet
) : AppCompatImageButton(context, attrs) {
    init {
        setBackgroundResource(R.drawable.shape_oval_button)
    }

    fun updateIconWithState(state : State) {
        when (state) {
            State.BEFORE_RECORDING -> {
                setImageResource(R.drawable.ic_recorde)
            }
            State.ON_RECORDING -> {
                setImageResource(R.drawable.ic_stop)
            }
            State.AFTER_RECORDING -> {
                setImageResource(R.drawable.ic_play)
            }
            State.ON_PLAYING -> {
                setImageResource(R.drawable.ic_stop)
            }
        }
    }
}