package com.alio.ulio.view.ui.main.alarmsend

import android.content.Intent
import android.util.Log
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


        binding.layoutCalendarview.setOnDayClickListener(object : OnDayClickListener {
            override fun onDayClick(eventDay: EventDay) {
                binding.tvDate.text = convertDate(eventDay.calendar.time)
            }
        })

        binding.selectAm.addOnButtonCheckedListener{ group, buttonId, isChecked ->
            if (isChecked) {
                val checkedIndex = group.indexOfChild(findViewById(buttonId))
                Log.d("test", "" + checkedIndex)
                viewModel.checkAM_PM(checkedIndex)
            }
        }

    }

    private fun initObserve() {

        binding.layoutClock.setOnClickListener {
            val materialTimePicker: MaterialTimePicker = MaterialTimePicker.Builder()
                // set the title for the alert dialog
                .setTitleText("SELECT YOUR TIMING")
                .setInputMode(INPUT_MODE_KEYBOARD)
                // set the default hour for the
                // dialog when the dialog opens
                //.setHour(12)
                // set the default minute for the
                // dialog when the dialog opens
                //.setMinute(10)
                // set the time format
                // according to the region
                .setTimeFormat(TimeFormat.CLOCK_12H)
                .build()

            materialTimePicker.show(supportFragmentManager, "MainActivity")

            // on clicking the positive button of the time picker
            // dialog update the TextView accordingly
            materialTimePicker.addOnPositiveButtonClickListener {

                val pickedHour: Int = materialTimePicker.hour
                val pickedMinute: Int = materialTimePicker.minute

                // check for single digit hour hour and minute
                // and update TextView accordingly
                val formattedTime: String = when {
                    pickedHour > 12 -> {
                        if (pickedMinute < 10) {
                            "${materialTimePicker.hour - 12}:0${materialTimePicker.minute} pm"
                        } else {
                            "${materialTimePicker.hour - 12}:${materialTimePicker.minute} pm"
                        }
                    }
                    pickedHour == 12 -> {
                        if (pickedMinute < 10) {
                            "${materialTimePicker.hour}:0${materialTimePicker.minute} pm"
                        } else {
                            "${materialTimePicker.hour}:${materialTimePicker.minute} pm"
                        }
                    }
                    pickedHour == 0 -> {
                        if (pickedMinute < 10) {
                            "${materialTimePicker.hour + 12}:0${materialTimePicker.minute} am"
                        } else {
                            "${materialTimePicker.hour + 12}:${materialTimePicker.minute} am"
                        }
                    }
                    else -> {
                        if (pickedMinute < 10) {
                            "${materialTimePicker.hour}:0${materialTimePicker.minute} am"
                        } else {
                            "${materialTimePicker.hour}:${materialTimePicker.minute} am"
                        }
                    }
                }

                // then update the preview TextView
                //previewPickedTimeTextView.text = formattedTime
            }

            viewModel.nextEvent.eventObserve(this) { alarm ->
                // 녹음 페이지 화면 이동
                val intent = Intent(this, VoiceRecoredActivity::class.java)
                intent.putExtra("Alarm", alarm)
                startActivity(intent)
                overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_slide_out_left)

            }
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