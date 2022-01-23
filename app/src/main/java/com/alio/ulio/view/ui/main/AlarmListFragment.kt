package com.alio.ulio.view.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.alio.ulio.R
import com.alio.ulio.base.BaseFragment
import com.alio.ulio.databinding.AlarmListFragmentBinding

class AlarmListFragment : BaseFragment<AlarmListFragmentBinding,
        AlarmListViewModel>(R.layout.alarm_list_fragment) {

    override fun AlarmListFragmentBinding.onCreateView() {
        viewModel = ViewModelProvider(this@AlarmListFragment).get(AlarmListViewModel::class.java)
        binding.viewmodel = viewmodel


    }

}