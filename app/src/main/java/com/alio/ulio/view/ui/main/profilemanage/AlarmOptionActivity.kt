package com.alio.ulio.view.ui.main.profilemanage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ExpandableListAdapter
import com.alio.ulio.R
import com.alio.ulio.base.BaseAppCompatActivity
import com.alio.ulio.databinding.ActivityAlarmOptionBinding
import com.alio.ulio.view.ui.main.profilemanage.adapter.NotifyAdapter
import com.alio.ulio.view.ui.main.profilemanage.adapter.QuestionExpandableListAdapter
import com.alio.ulio.view.ui.main.profilemanage.model.Question.data

// 알림 센터
class AlarmOptionActivity : BaseAppCompatActivity<ActivityAlarmOptionBinding,
        AlarmOptionViewModel>(R.layout.activity_alarm_option) {

    private var adapter : ExpandableListAdapter? = null
    private var titleList : List<String>? = null

    private lateinit var notifyAdapter : NotifyAdapter

    override fun ActivityAlarmOptionBinding.onCreate() {
        viewModel = AlarmOptionViewModel(application)

        initExpandableView()

        initNotifyView()
    }

    private fun initExpandableView() {
        val listData = data
        titleList = ArrayList(listData.keys)
        adapter = QuestionExpandableListAdapter(this@AlarmOptionActivity, titleList as ArrayList<String>, listData)
        binding.expendableList.setAdapter(adapter)
        binding.expendableList.setOnGroupExpandListener { groupPosition ->

        }

        binding.expendableList.setOnGroupCollapseListener { groupPosition ->

        }

        binding.expendableList.setOnChildClickListener { _, _, groupPosition, childPosition, _ ->
            false
        }
    }

    private fun initNotifyView() {
        notifyAdapter = NotifyAdapter(this)

        binding.recyclerviewNotice.adapter = notifyAdapter
    }
}