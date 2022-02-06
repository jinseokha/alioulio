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
        setBackgroundResource(R.drawable.ic_btn_line_b_rcrd_enbabled)
    }

    fun updateIconWithState(state : State) {
        when (state) {
            // 녹음 전
            State.BEFORE_RECORDING -> {
                //setImageResource(R.drawable.ic_recorde)
                setImageResource(R.drawable.ic_btn_line_b_rcrd_enbabled)
            }
            // 녹음 중
            State.ON_RECORDING -> {
                setImageResource(R.drawable.ic_btn_line_b_rcrd_enbabled)
            }
            // 다시듣기
            State.AFTER_RECORDING -> {
                setImageResource(R.drawable.ic_play)
            }
            // 듣기 도중 바뀌는 아이콘
            State.ON_PLAYING -> {
                setImageResource(R.drawable.ic_stop)
            }
        }
    }
}