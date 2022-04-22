package com.alio.ulio.view.ui.main.alarmsend

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alio.ulio.R
import com.alio.ulio.base.BaseAppCompatActivity
import com.alio.ulio.databinding.ActivityFriendSendListBinding
import com.alio.ulio.view.ui.main.alarmsend.adapter.FriendSendListAdapter
import com.alio.ulio.view.ui.main.alarmsend.viewmodel.FriendSendListViewModel

//
class FriendSendListActivity : BaseAppCompatActivity<ActivityFriendSendListBinding,
        FriendSendListViewModel>(R.layout.activity_friend_send_list) {

    private lateinit var friendSendListAdapter: FriendSendListAdapter
    private lateinit var friendSendListManager : RecyclerView.LayoutManager

    override fun ActivityFriendSendListBinding.onCreate() {
        viewModel = FriendSendListViewModel(application)
        binding.activity = this@FriendSendListActivity
        binding.viewmodel = viewModel

        initAdapter()
        initObserve()
    }

    private fun initAdapter() {
        friendSendListAdapter = FriendSendListAdapter(this)
        friendSendListManager = LinearLayoutManager(this)

        binding.recyclerView.layoutManager = friendSendListManager
        binding.recyclerView.adapter = friendSendListAdapter
    }

    private fun initObserve() {
        binding.backButton.setOnClickListener {
            finish()
            overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_out_right)
        }

        binding.checkAll.setOnCheckedChangeListener { buttonView, isChecked ->
            if(friendSendListAdapter.getAllCheck())
                friendSendListAdapter.setAllCheck(false)
            else
                friendSendListAdapter.setAllCheck(true)

        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_out_right)
    }
}