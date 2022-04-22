package com.alio.ulio.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

/**
 * @author Ha Jin Seok
 * @email seok270@gmail.com
 * @created 2022-01-13
 * @desc
 */
open class BaseAppCompatActivity <T : ViewDataBinding, R : BaseViewModel>(@LayoutRes val layoutRes : Int)
    : AppCompatActivity() {

    lateinit var binding : T
    lateinit var viewModel: R

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, layoutRes)
        binding.onCreate()
    }

    open fun T.onCreate() = Unit
}