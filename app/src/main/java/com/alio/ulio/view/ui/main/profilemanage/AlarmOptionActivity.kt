package com.alio.ulio.view.ui.main.profilemanage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.alio.ulio.R
import com.alio.ulio.base.BaseAppCompatActivity
import com.alio.ulio.databinding.ActivityAlarmOptionBinding

// 알림 센터
class AlarmOptionActivity : BaseAppCompatActivity<ActivityAlarmOptionBinding,
        AlarmOptionViewModel>(R.layout.activity_alarm_option) {
    override fun ActivityAlarmOptionBinding.onCreate() {
        viewModel = AlarmOptionViewModel(application)

    }
}