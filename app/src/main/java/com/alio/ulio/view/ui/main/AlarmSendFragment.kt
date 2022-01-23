package com.alio.ulio.view.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.alio.ulio.R
import com.alio.ulio.base.BaseFragment
import com.alio.ulio.databinding.AlarmSendFragmentBinding

class AlarmSendFragment : BaseFragment<AlarmSendFragmentBinding,
        AlarmSendViewModel>(R.layout.alarm_send_fragment) {

    override fun AlarmSendFragmentBinding.onCreateView() {
        viewmodel = ViewModelProvider(this@AlarmSendFragment).get(AlarmSendViewModel::class.java)
        binding.viewmodel = viewmodel

    }


}