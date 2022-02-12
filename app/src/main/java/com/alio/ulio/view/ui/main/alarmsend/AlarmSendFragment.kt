package com.alio.ulio.view.ui.main.alarmsend

import android.content.Intent
import android.view.View
import android.widget.Toast
import com.alio.ulio.R
import com.alio.ulio.base.BaseFragment
import com.alio.ulio.databinding.AlarmSendFragmentBinding
import com.alio.ulio.view.ui.main.alarmsend.viewmodel.AlarmSendViewModel

// 1. 알림 보내기
class AlarmSendFragment : BaseFragment<AlarmSendFragmentBinding,
        AlarmSendViewModel>(R.layout.alarm_send_fragment) {

    override fun AlarmSendFragmentBinding.onCreateView() {
        binding.fragment = this@AlarmSendFragment

    }

    fun onlyAlarmClick(view : View) {
        val intent = Intent(requireActivity(), AlarmConditionActivity::class.java)
        startActivity(intent)
    }

    fun alwaysAlarmClick(view : View) {

    }

}