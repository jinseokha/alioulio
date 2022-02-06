package com.alio.ulio.view.ui.main.alarmsend

import com.alio.ulio.R
import com.alio.ulio.base.BaseAppCompatActivity
import com.alio.ulio.databinding.ActivityVoiceRecoredBinding
import com.alio.ulio.util.eventObserve
import com.alio.ulio.view.ui.main.alarmsend.viewmodel.VoiceRecoredViewModel

// 3. 음성 녹음 페이지 (뭐라고 보내면 좋을까요? 페이지)
class VoiceRecoredActivity : BaseAppCompatActivity<ActivityVoiceRecoredBinding,
        VoiceRecoredViewModel>(R.layout.activity_voice_recored) {


    override fun ActivityVoiceRecoredBinding.onCreate() {
        viewModel = VoiceRecoredViewModel(application)
        binding.viewmodel = viewmodel

        initObserve()
    }

    private fun initObserve() {
        viewModel.nextEvent.eventObserve(this) { text ->
            // 친구에게 전하는 화면 이동

        }
    }
}