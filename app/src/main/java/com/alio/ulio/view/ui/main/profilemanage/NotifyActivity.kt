package com.alio.ulio.view.ui.main.profilemanage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.alio.ulio.R
import com.alio.ulio.base.BaseAppCompatActivity
import com.alio.ulio.databinding.ActivityNotifyBinding
import com.alio.ulio.view.ui.main.profilemanage.model.Notify

class NotifyActivity : BaseAppCompatActivity<ActivityNotifyBinding,
        NotifyViewModel>(R.layout.activity_notify) {

    override fun ActivityNotifyBinding.onCreate() {
        viewModel = NotifyViewModel(application)
        binding.viewmodel = viewModel

        initViews()
    }

    private fun initViews() {
        val notifyData = intent.getSerializableExtra("notify") as Notify
        binding.title.text = notifyData.title
        binding.content.text = notifyData.content
    }


}