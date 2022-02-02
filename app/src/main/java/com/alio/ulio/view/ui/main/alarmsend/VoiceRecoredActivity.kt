package com.alio.ulio.view.ui.main.alarmsend

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.alio.ulio.R
import com.alio.ulio.base.BaseAppCompatActivity
import com.alio.ulio.databinding.ActivityVoiceRecoredBinding

class VoiceRecoredActivity : BaseAppCompatActivity<ActivityVoiceRecoredBinding,
        VoiceRecoredViewModel>(R.layout.activity_voice_recored) {

    override fun ActivityVoiceRecoredBinding.onCreate() {
        viewModel = VoiceRecoredViewModel(application)
        binding.viewmodel = viewmodel
    }
}