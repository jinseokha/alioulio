package com.alio.ulio.view.ui.main.alarmsend

import android.content.Intent
import androidx.recyclerview.widget.OrientationHelper
import com.alio.ulio.R
import com.alio.ulio.base.BaseAppCompatActivity
import com.alio.ulio.databinding.ActivityAlarmConditionBinding
import com.alio.ulio.util.eventObserve
import com.alio.ulio.view.ui.main.alarmsend.viewmodel.AlarmConditionViewModel
import com.applikeysolutions.cosmocalendar.utils.SelectionType

// 2-1. 알람 조건 설정 (오직, 그 순간을 위한 모멘트 알림 후속 페이지)
class AlarmConditionActivity : BaseAppCompatActivity<ActivityAlarmConditionBinding,
        AlarmConditionViewModel>(R.layout.activity_alarm_condition) {

    override fun ActivityAlarmConditionBinding.onCreate() {
        viewModel = AlarmConditionViewModel(application)
        binding.activity = this@AlarmConditionActivity
        binding.viewmodel = viewModel

        initView()
        initObserve()
    }

    private fun initView() {
        binding.layoutCalendarview.calendarOrientation = OrientationHelper.HORIZONTAL
        binding.layoutCalendarview.selectionType = SelectionType.SINGLE

    }

    private fun initObserve() {
        viewModel.nextEvent.eventObserve(this) { text ->
           // 녹음 페이지 화면 이동
            val intent = Intent(this, VoiceRecoredActivity::class.java)
            startActivity(intent)

        }
    }
}