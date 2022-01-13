package com.alio.ulio.view.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.alio.ulio.R
import com.alio.ulio.base.BaseActivity
import com.alio.ulio.databinding.ActivityFirstSignAgreeBinding
import com.alio.ulio.view.ui.viewmodel.FirstSignAgreeViewModel

class FirstSignAgreeActivity : BaseActivity<ActivityFirstSignAgreeBinding, FirstSignAgreeViewModel>(R.layout.activity_first_sign_agree) {
    override fun initStartView() {
        viewModel = FirstSignAgreeViewModel(application)
        binding.viewmodel = viewModel

    }

    override fun initDataBinding() {

    }

    override fun initAfterBinding() {

    }
}