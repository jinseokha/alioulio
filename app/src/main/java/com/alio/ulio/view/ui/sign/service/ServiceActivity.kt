package com.alio.ulio.view.ui.sign.service

import android.webkit.WebChromeClient
import android.webkit.WebViewClient
import com.alio.ulio.R
import com.alio.ulio.base.BaseAppCompatActivity
import com.alio.ulio.databinding.ActivityFirstSignAgreeBinding
import com.alio.ulio.databinding.ActivityServiceBinding
import com.alio.ulio.view.ui.sign.FirstSignAgreeViewModel

/**
 * @author Ha Jin Seok
 * @email seok270@gmail.com
 * @created 2022-04-10
 * @desc
 */

class ServiceActivity : BaseAppCompatActivity<ActivityServiceBinding, ServiceViewModel>(
    R.layout.activity_service)  {

    override fun ActivityServiceBinding.onCreate() {
        viewModel = ServiceViewModel(application)
        binding.viewmodel = viewmodel

        binding.webView.settings.apply {
            javaScriptEnabled = true
            domStorageEnabled = true
            setSupportMultipleWindows(true)
        }

        binding.webView.apply {
            webViewClient = WebViewClient()
            webChromeClient = WebChromeClient()
            loadUrl("https://sites.google.com/view/alioulioservice")
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_out_right)
    }
}