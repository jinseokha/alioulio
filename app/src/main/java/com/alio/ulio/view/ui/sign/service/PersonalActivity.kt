package com.alio.ulio.view.ui.sign.service

import android.webkit.WebChromeClient
import android.webkit.WebViewClient
import com.alio.ulio.R
import com.alio.ulio.base.BaseAppCompatActivity
import com.alio.ulio.databinding.ActivityFirstSignAgreeBinding
import com.alio.ulio.databinding.ActivityPersonalBinding
import com.alio.ulio.view.ui.sign.FirstSignAgreeViewModel

/**
 * @author Ha Jin Seok
 * @email seok270@gmail.com
 * @created 2022-04-10
 * @desc
 */

class PersonalActivity : BaseAppCompatActivity<ActivityPersonalBinding,
        PersonalViewModel>(
    R.layout.activity_personal)  {

    override fun ActivityPersonalBinding.onCreate() {
        viewModel = PersonalViewModel(application)
        binding.viewmodel = viewModel

        binding.webView.settings.apply {
            javaScriptEnabled = true
            domStorageEnabled = true
            setSupportMultipleWindows(true)
        }

        binding.webView.apply {
            webViewClient = WebViewClient()
            webChromeClient = WebChromeClient()
            loadUrl("https://sites.google.com/view/aliouliopersonalinfo")
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_out_right)
    }
}