package com.alio.ulio.view.ui.main.alarmsend

import android.content.Intent
import android.text.Html
import android.view.View
import com.alio.ulio.R
import com.alio.ulio.base.BaseFragment
import com.alio.ulio.databinding.AlarmSendFragmentBinding
import com.alio.ulio.view.ui.main.alarmsend.viewmodel.AlarmSendViewModel

// 1. 알림 보내기
class AlarmSendFragment : BaseFragment<AlarmSendFragmentBinding,
        AlarmSendViewModel>(R.layout.alarm_send_fragment) {

    override fun AlarmSendFragmentBinding.onCreateView() {
        binding.fragment = this@AlarmSendFragment


        var someText : String = "오직,<b><br>그 순간을 위한<br>모멘트</b> 알림"
        binding.someId.text = Html.fromHtml(someText)

        var coupleText : String = "<b>항상,<br>곁에서 챙겨주는<br></b>일상 알림"
        binding.coupleId.text = Html.fromHtml(coupleText)
    }

    fun onlyAlarmClick(view : View) {
        val intent = Intent(requireActivity(), AlarmConditionActivity::class.java)
        startActivity(intent)
        requireActivity().overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_slide_out_left)
    }

    fun alwaysAlarmClick(view : View) {
        val intent = Intent(requireActivity(), AlarmAlwaysActivity::class.java)
        startActivity(intent)
        requireActivity().overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_slide_out_left)
    }

}