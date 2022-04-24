package com.alio.ulio.view.ui.main.alarmlist

import android.view.View
import android.view.WindowManager
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.alio.ulio.R
import com.alio.ulio.base.BaseFragment
import com.alio.ulio.custom.recyclerLayout.LinearLayoutManager
import com.alio.ulio.databinding.AlarmListFragmentBinding
import com.alio.ulio.view.ui.main.alarmlist.adapter.HorizontalViewAdapter
import com.alio.ulio.view.ui.main.alarmlist.adapter.ReceiverAlarmAdapter
import com.alio.ulio.view.ui.main.alarmlist.adapter.SendAlarmAdapter

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

        initView()

        binding.layoutReceiver.setOnClickListener {
            binding.layoutReceiver.isSelected = true
            binding.layoutSender.isSelected = false
            binding.viewReceiver.visibility = View.VISIBLE
            binding.viewSend.visibility = View.INVISIBLE
            binding.imageViewLogo.setImageResource(R.drawable.ic_top_receiver_img)
            binding.layoutBackground.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.gray_F7F7F7))

            var window = requireActivity().window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            window.statusBarColor = ContextCompat.getColor(requireContext(), R.color.gray_F7F7F7)
            selectLayout()
        }

        binding.layoutSender.setOnClickListener {
            binding.layoutSender.isSelected = true
            binding.layoutReceiver.isSelected = false
            binding.viewReceiver.visibility = View.INVISIBLE
            binding.viewSend.visibility = View.VISIBLE
            binding.imageViewLogo.setImageResource(R.drawable.ic_top_send_img)
            binding.layoutBackground.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.background_blue))

            var window = requireActivity().window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            window.statusBarColor = ContextCompat.getColor(requireContext(), R.color.background_blue)
            selectLayout()
        }
    }

    private fun initView() {

        var window = requireActivity().window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.statusBarColor = ContextCompat.getColor(requireContext(), R.color.gray_F7F7F7)

        receiverAdapter = ReceiverAlarmAdapter(requireContext())
        receiverManager = LinearLayoutManager(requireContext())

        sendAdapter = SendAlarmAdapter(requireContext())
        sendManager = LinearLayoutManager(requireContext())

        horizontalAdapter = HorizontalViewAdapter(requireContext())

        binding.layoutReceiver.isSelected = true
        binding.layoutSender.isSelected = false
        binding.viewReceiver.visibility = View.VISIBLE
        binding.viewSend.visibility = View.GONE
        binding.imageViewLogo.setImageResource(R.drawable.ic_top_receiver_img)
        binding.layoutBackground.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.gray_F7F7F7))
        selectLayout()

        binding.includeSend.recyclerView.layoutManager = sendManager
        binding.includeSend.recyclerView.adapter = sendAdapter

        binding.includeReceive.recyclerView.layoutManager = receiverManager
        binding.includeReceive.recyclerView.adapter = receiverAdapter

        binding.includeReceive.horizontalRecyclerView.adapter = horizontalAdapter
    }

    private fun selectLayout() {
        if (binding.layoutReceiver.isSelected) {
            binding.includeSend.root.visibility = View.GONE
            binding.includeReceive.root.visibility = View.VISIBLE
        }

        if (binding.layoutSender.isSelected) {
            binding.includeSend.root.visibility = View.VISIBLE
            binding.includeReceive.root.visibility = View.GONE
        }
    }
}