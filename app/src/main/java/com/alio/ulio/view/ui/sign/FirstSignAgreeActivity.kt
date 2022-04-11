package com.alio.ulio.view.ui.sign

import android.content.Intent
import android.view.View
import com.alio.ulio.R
import com.alio.ulio.base.BaseAppCompatActivity
import com.alio.ulio.databinding.ActivityFirstSignAgreeBinding
import com.alio.ulio.util.eventObserve
import com.alio.ulio.view.ui.main.MainActivity
import com.alio.ulio.view.ui.main.alarmsend.VoiceRecoredActivity
import com.alio.ulio.view.ui.sign.service.PersonalActivity
import com.alio.ulio.view.ui.sign.service.ServiceActivity

class FirstSignAgreeActivity : BaseAppCompatActivity<ActivityFirstSignAgreeBinding, FirstSignAgreeViewModel>(R.layout.activity_first_sign_agree) {

    override fun ActivityFirstSignAgreeBinding.onCreate() {
        viewModel = FirstSignAgreeViewModel(application)
        binding.viewmodel = viewModel

        initObservable()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_out_right)
    }

    private fun initObservable() {
        binding.imgService.setOnClickListener {
            val intent = Intent(this, ServiceActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_slide_out_left)
        }

        binding.imgPersonalService.setOnClickListener {
            val intent = Intent(this, PersonalActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_slide_out_left)
        }

        viewModel.nextEvent.eventObserve(this) { alarm ->
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
            finish()
        }
    }
}