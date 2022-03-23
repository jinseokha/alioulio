package com.alio.ulio.view.ui.main.alarmsend

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.alio.ulio.R
import com.alio.ulio.base.BaseAppCompatActivity
import com.alio.ulio.databinding.ActivityAlarmAlwaysBinding
import com.alio.ulio.view.ui.main.alarmsend.viewmodel.AlarmAlwaysViewModel

class AlarmAlwaysActivity : BaseAppCompatActivity<ActivityAlarmAlwaysBinding,
        AlarmAlwaysViewModel>(R.layout.activity_alarm_always) {
    override fun ActivityAlarmAlwaysBinding.onCreate() {
        viewModel = AlarmAlwaysViewModel(application)
        binding.activity = this@AlarmAlwaysActivity
        binding.viewmodel = viewModel

    }
}