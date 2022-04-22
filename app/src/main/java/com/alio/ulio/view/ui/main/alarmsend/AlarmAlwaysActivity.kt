package com.alio.ulio.view.ui.main.alarmsend

import android.content.Intent
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.alio.ulio.R
import com.alio.ulio.base.BaseAppCompatActivity
import com.alio.ulio.databinding.ActivityAlarmAlwaysBinding
import com.alio.ulio.db.entity.RepeatPeriod
import com.alio.ulio.util.eventObserve
import com.alio.ulio.view.ui.main.alarmsend.adapter.RepeatPeriodAdapter
import com.alio.ulio.view.ui.main.alarmsend.viewmodel.AlarmAlwaysViewModel

class AlarmAlwaysActivity : BaseAppCompatActivity<ActivityAlarmAlwaysBinding,
        AlarmAlwaysViewModel>(R.layout.activity_alarm_always) {

    private lateinit var repeatPeriodAdpater : RepeatPeriodAdapter
    private lateinit var repeatPeriodData : ArrayList<RepeatPeriod>

    override fun ActivityAlarmAlwaysBinding.onCreate() {
        viewModel = AlarmAlwaysViewModel(application)
        binding.activity = this@AlarmAlwaysActivity
        binding.viewmodel = viewModel

        loadFakeData()

        binding.backButton.setOnClickListener {
            finish()
            overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_out_right)
        }

        binding.recyclerview.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@AlarmAlwaysActivity)
            repeatPeriodAdpater = RepeatPeriodAdapter(this@AlarmAlwaysActivity)
            adapter = repeatPeriodAdpater
        }

        repeatPeriodAdpater.submitList(repeatPeriodData)

        repeatPeriodAdpater.setOnItemClickListener { reponse ->
            // "다음" 버튼 활성화 체크

            // (매주) 텍스트 추가
            Log.d("test", "" + repeatPeriodAdpater.getSelectedExpense())

            var day : ArrayList<String> = ArrayList()

            for (data in repeatPeriodAdpater.getSelectedExpense()) {
                day.add(data.short_title)
            }

            if (day.size == 0) {
                binding.textViewDay.text = ""
            } else {
                var stringDay = day.toString()
                stringDay = stringDay.replace("[", "")
                stringDay = stringDay.replace("]", "")
                binding.textViewDay.text = "(매주) $stringDay"
            }
        }

        initObserve()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_out_right)
    }

    var buttonAMPMListener = View.OnClickListener { it ->
        if (it.id == R.id.button_am) {
            binding.buttonAm.isSelected = true
            binding.buttonPm.isSelected = false
        } else {
            binding.buttonAm.isSelected = false
            binding.buttonPm.isSelected = true
        }
    }

    private fun loadFakeData() {
        repeatPeriodData = ArrayList()
        repeatPeriodData.clear()

        repeatPeriodData.add(RepeatPeriod(title = "월요일", short_title = "월", sort = 0))
        repeatPeriodData.add(RepeatPeriod(title = "화요일", short_title = "화", sort = 1))
        repeatPeriodData.add(RepeatPeriod(title = "수요일", short_title = "수", sort = 2))
        repeatPeriodData.add(RepeatPeriod(title = "목요일", short_title = "목", sort = 3))
        repeatPeriodData.add(RepeatPeriod(title = "금요일", short_title = "금", sort = 4))
        repeatPeriodData.add(RepeatPeriod(title = "토요일", short_title = "토", sort = 5))
        repeatPeriodData.add(RepeatPeriod(title = "일요일", short_title = "일", sort = 6))
    }

    private fun initObserve() {

        binding.buttonAm.isSelected = true
        binding.buttonPm.isSelected = false

        binding.buttonAm.setOnClickListener(buttonAMPMListener)
        binding.buttonPm.setOnClickListener(buttonAMPMListener)

        viewModel.nextEvent.eventObserve(this) { alarm ->
            // 녹음 페이지 화면 이동
            val intent = Intent(this, VoiceRecoredActivity::class.java)
            intent.putExtra("Alarm", alarm)
            startActivity(intent)
            overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_slide_out_left)

        }
    }
}