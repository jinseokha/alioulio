package com.alio.ulio.view.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.alio.ulio.R
import com.alio.ulio.base.BaseActivity
import com.alio.ulio.databinding.ActivitySignBinding
import com.alio.ulio.databinding.DialogAccessDeniedBinding
import com.alio.ulio.util.EventObserver
import com.alio.ulio.view.dialog.DialogAccessDeniedDialog
import com.alio.ulio.view.ui.viewmodel.SignViewModel
import java.util.*

class SignActivity : BaseActivity<ActivitySignBinding, SignViewModel>(R.layout.activity_sign) {

    override fun initStartView() {
        viewModel = SignViewModel(application)
        binding.viewmodel = viewModel

    }

    override fun initDataBinding() {
        viewModel.kakaoLoginClick.observe(this, EventObserver {
            var dialog = DialogAccessDeniedDialog()
            dialog.setDialogListener(object : DialogAccessDeniedDialog.DialogClickListener {
                override fun onAgreeClicked() {
                    // 동의
                }

                override fun onNotAgreeClick() {
                    // 비동의

                }
            })

            dialog.show(supportFragmentManager, "testDialog")
        })

        viewModel.memberInfoRule.observe(this, EventObserver {

        })
    }

    override fun initAfterBinding() {

    }

}