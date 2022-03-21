package com.alio.ulio.view.ui.sign

import com.alio.ulio.R
import com.alio.ulio.base.BaseAppCompatActivity
import com.alio.ulio.databinding.ActivityFirstSignAgreeBinding

class FirstSignAgreeActivity : BaseAppCompatActivity<ActivityFirstSignAgreeBinding, FirstSignAgreeViewModel>(R.layout.activity_first_sign_agree) {

    override fun ActivityFirstSignAgreeBinding.onCreate() {
        viewModel = FirstSignAgreeViewModel(application)
        binding.viewmodel = viewModel
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_out_right)
    }
}