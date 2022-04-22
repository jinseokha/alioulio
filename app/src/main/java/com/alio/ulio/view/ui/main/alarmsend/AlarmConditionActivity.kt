package com.alio.ulio.view.ui.main.alarmsend

import android.content.Intent
import android.util.Log
import android.view.View
import android.view.animation.AccelerateInterpolator
import android.view.animation.AlphaAnimation
import android.view.animation.TranslateAnimation
import com.alio.ulio.R
import com.alio.ulio.base.BaseAppCompatActivity
import com.alio.ulio.custom.calendar.EventDay
import com.alio.ulio.custom.calendar.listeners.OnDayClickListener
import com.alio.ulio.databinding.ActivityAlarmConditionBinding
import com.alio.ulio.util.eventObserve
import com.alio.ulio.view.ui.main.alarmsend.viewmodel.AlarmConditionViewModel
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.MaterialTimePicker.INPUT_MODE_KEYBOARD
import com.google.android.material.timepicker.TimeFormat
import java.text.SimpleDateFormat
import java.util.*

// 2-1. 알람 조건 설정 (오직, 그 순간을 위한 모멘트 알림 후속 페이지)
class AlarmConditionActivity : BaseAppCompatActivity<ActivityAlarmConditionBinding,
        AlarmConditionViewModel>(R.layout.activity_alarm_condition) {

    override fun ActivityAlarmConditionBinding.onCreate() {
        viewModel = AlarmConditionViewModel(application)
        binding.activity = this@AlarmConditionActivity
        binding.viewmodel = viewModel


        initCalendar()
        initObserve()
    }

    private fun initCalendar() {

        binding.imgCalendar.setOnClickListener {
            if (binding.layoutCalendarview.visibility == View.VISIBLE) {
                val errorFadeOut = AlphaAnimation(1f, 0f)
                errorFadeOut.interpolator = AccelerateInterpolator()
                errorFadeOut.duration = 700

                binding.layoutCalendarview.animation = errorFadeOut
                binding.layoutCalendarview.visibility = View.GONE

            } else {
                val errorFadeOut = AlphaAnimation(0f, 1f)
                errorFadeOut.interpolator = AccelerateInterpolator()
                errorFadeOut.duration = 700

                binding.layoutCalendarview.animation = errorFadeOut
                binding.layoutCalendarview.visibility = View.VISIBLE

            }
        }


        binding.layoutCalendarview.setOnDayClickListener(object : OnDayClickListener {
            override fun onDayClick(eventDay: EventDay) {
                binding.tvDate.text = convertDate(eventDay.calendar.time)
            }
        })

        binding.buttonAm.isSelected = true
        binding.buttonPm.isSelected = false

        binding.buttonAm.setOnClickListener(buttonAMPMListener)
        binding.buttonPm.setOnClickListener(buttonAMPMListener)

    }

    var buttonAMPMListener = View.OnClickListener { it ->
        if (it.id == R.id.button_am) {
            binding.buttonAm.isSelected = true
            binding.buttonPm.isSelected = false
        } else {
            binding.buttonAm.isSelected = false
            binding.buttonPm.isSelected = true
        }
    }

    private fun initObserve() {

        binding.backButton.setOnClickListener {
            finish()
            overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_out_right)
        }

        viewModel.nextEvent.eventObserve(this) { alarm ->
            // 녹음 페이지 화면 이동
            val intent = Intent(this, VoiceRecoredActivity::class.java)
            intent.putExtra("Alarm", alarm)
            startActivity(intent)
            overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_slide_out_left)

        }
    }

    private fun convertDate(date : Date) : String {
        var format = SimpleDateFormat("yyyy년 M월 d일")
        var str = format.format(date)
        return str
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_out_right)
    }
}