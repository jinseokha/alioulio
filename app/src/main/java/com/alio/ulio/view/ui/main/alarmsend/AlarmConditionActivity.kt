package com.alio.ulio.view.ui.main.alarmsend

import android.content.Intent
import android.util.Log
import com.alio.ulio.R
import com.alio.ulio.base.BaseAppCompatActivity
import com.alio.ulio.custom.calendar.EventDay
import com.alio.ulio.custom.calendar.listeners.OnDayClickListener
import com.alio.ulio.databinding.ActivityAlarmConditionBinding
import com.alio.ulio.util.eventObserve
import com.alio.ulio.view.ui.main.alarmsend.viewmodel.AlarmConditionViewModel
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
}