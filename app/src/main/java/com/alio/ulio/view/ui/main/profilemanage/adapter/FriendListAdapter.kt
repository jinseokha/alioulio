package com.alio.ulio.view.ui.main.profilemanage.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.alio.ulio.R
import com.alio.ulio.db.entity.Friend
import kotlinx.android.synthetic.main.item_friend_list.view.*

/**
 * @author Ha Jin Seok
 * @email seok270@gmail.com
 * @created 2022-03-15
 * @desc
 */

class FriendListAdapter(context : Context) : RecyclerView.Adapter<FriendListAdapter.ViewHolder>() {

    private val inflater = LayoutInflater.from(context)
    private val items = mutableListOf<Friend>()

    init {
        prepareSampleData()
    }

    private fun prepareSampleData() {
        for (itemNumber in 1..30) {
            var description = "description [$itemNumber]"

            items.add(Friend(description))
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(inflater.inflate(R.layout.item_friend_list, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setViewData(items[position], position)
    }

    override fun getItemCount(): Int
            = items.size

    inner class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        fun setViewData(friend : Friend, position : Int) {
            itemView.textView_name.text = friend.title

            itemView.imageView_block.setOnClickListener {
                Toast.makeText(it.context, "차단", Toast.LENGTH_SHORT).show()
            }

            itemView.imageView_send.setOnClickListener {
                Toast.makeText(it.context, "알림 보내기", Toast.LENGTH_SHORT).show()
            }
        }
    }
}