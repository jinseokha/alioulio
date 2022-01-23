package com.alio.ulio.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

/**
 * @author Ha Jin Seok
 * @email seok270@dahami.com
 * @created 2022-01-13
 * @desc
 */
open class BaseFragment<T: ViewDataBinding, R : BaseViewModel>(@LayoutRes val layoutRes: Int) : Fragment(){

    lateinit var binding: T
    lateinit var viewModel: R

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, layoutRes, container, false)
        binding.onCreateView()
        binding.onViewCreated()
        return binding.root
    }

    open fun T.onCreateView() = Unit
    open fun T.onViewCreated() = Unit
}