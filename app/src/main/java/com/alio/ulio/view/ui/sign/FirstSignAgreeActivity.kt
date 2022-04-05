package com.alio.ulio.view.ui.sign

import android.content.Intent
import android.view.View
import com.alio.ulio.R
import com.alio.ulio.base.BaseAppCompatActivity
import com.alio.ulio.databinding.ActivityFirstSignAgreeBinding
import com.alio.ulio.util.eventObserve
import com.alio.ulio.view.ui.main.MainActivity

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
        viewModel.nextEvent.eventObserve(this) { alarm ->
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
            finish()
        }
    }
}