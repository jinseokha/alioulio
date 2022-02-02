package com.alio.ulio.view.ui.main.alarmlist

import androidx.lifecycle.ViewModelProvider
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