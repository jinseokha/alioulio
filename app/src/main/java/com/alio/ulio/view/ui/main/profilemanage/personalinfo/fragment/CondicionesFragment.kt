package com.alio.ulio.view.ui.main.profilemanage.personalinfo.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.alio.ulio.R
import com.alio.ulio.base.BaseFragment
import com.alio.ulio.databinding.FragmentCondicionesBinding


class CondicionesFragment : BaseFragment<FragmentCondicionesBinding,
        CondicionesViewModel>(R.layout.fragment_condiciones) {

    override fun FragmentCondicionesBinding.onCreateView() {
        viewModel = ViewModelProvider(this@CondicionesFragment).get(CondicionesViewModel::class.java)
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
}