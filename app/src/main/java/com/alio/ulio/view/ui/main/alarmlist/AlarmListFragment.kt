package com.alio.ulio.view.ui.main.alarmlist

import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.alio.ulio.R
import com.alio.ulio.base.BaseFragment
import com.alio.ulio.custom.recyclerLayout.LinearLayoutManager
import com.alio.ulio.databinding.AlarmListFragmentBinding
import com.alio.ulio.view.ui.main.alarmlist.adapter.HorizontalViewAdapter
import com.alio.ulio.view.ui.main.alarmlist.adapter.ReceiverAlarmAdapter
import com.alio.ulio.view.ui.main.alarmlist.adapter.SendAlarmAdapter
import kotlinx.android.synthetic.main.layout_receive_alarm.view.*

class AlarmListFragment : BaseFragment<AlarmListFragmentBinding,
        AlarmListViewModel>(R.layout.alarm_list_fragment) {

    private lateinit var receiverAdapter : ReceiverAlarmAdapter
    private lateinit var receiverManager : RecyclerView.LayoutManager

    private lateinit var sendAdapter : SendAlarmAdapter
    private lateinit var sendManager : RecyclerView.LayoutManager

    private lateinit var horizontalAdapter : HorizontalViewAdapter

    override fun AlarmListFragmentBinding.onCreateView() {
        viewModel = ViewModelProvider(this@AlarmListFragment).get(AlarmListViewModel::class.java)
        binding.viewmodel = viewmodel

        receiverAdapter = ReceiverAlarmAdapter(requireContext())
        receiverManager = LinearLayoutManager(requireContext())

        sendAdapter = SendAlarmAdapter(requireContext())
        sendManager = LinearLayoutManager(requireContext())

        horizontalAdapter = HorizontalViewAdapter(requireContext())

        binding.layoutReceiver.isSelected = true
        binding.layoutSender.isSelected = false
        selectLayout()

        binding.includeSend.recyclerView.layoutManager = sendManager
        binding.includeSend.recyclerView.adapter = sendAdapter

        binding.includeReceive.recyclerView.layoutManager = receiverManager
        binding.includeReceive.recyclerView.adapter = receiverAdapter

        binding.includeReceive.horizontal_recyclerView.adapter = horizontalAdapter

        binding.layoutReceiver.setOnClickListener {
            binding.layoutReceiver.isSelected = true
            binding.layoutSender.isSelected = false
            selectLayout()
        }

        binding.layoutSender.setOnClickListener {
            binding.layoutSender.isSelected = true
            binding.layoutReceiver.isSelected = false
            selectLayout()
        }
    }

    private fun selectLayout() {
        if (binding.layoutReceiver.isSelected) {
            binding.includeSend.visibility = View.GONE
            binding.includeReceive.visibility = View.VISIBLE
        }

        if (binding.layoutSender.isSelected) {
            binding.includeSend.visibility = View.VISIBLE
            binding.includeReceive.visibility = View.GONE
        }
    }
}