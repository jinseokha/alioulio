package com.alio.ulio.view.ui.main.profilemanage

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

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_out_right)
    }

}