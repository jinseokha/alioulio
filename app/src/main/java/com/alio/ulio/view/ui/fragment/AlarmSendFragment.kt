package com.alio.ulio.view.ui.fragment

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.alio.ulio.R
import com.alio.ulio.view.ui.viewmodel.AlarmSendViewModel

class AlarmSendFragment : Fragment() {

    companion object {
        fun newInstance() = AlarmSendFragment()
    }

    private lateinit var viewModel: AlarmSendViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.alarm_send_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(AlarmSendViewModel::class.java)
        // TODO: Use the ViewModel
    }

}