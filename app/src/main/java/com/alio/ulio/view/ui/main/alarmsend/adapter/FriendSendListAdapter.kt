package com.alio.ulio.view.ui.main.alarmsend.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView
import com.alio.ulio.R
import com.alio.ulio.db.entity.Friend
import com.alio.ulio.db.entity.FriendSend
import kotlinx.android.synthetic.main.activity_friend_send_list.view.*
import kotlinx.android.synthetic.main.item_friend_list.view.*
import kotlinx.android.synthetic.main.item_friend_list.view.textView_name
import kotlinx.android.synthetic.main.item_friend_send_list.view.*

/**
 * @author Ha Jin Seok
 * @email seok270@gmail.com
 * @created 2022-04-21
 * @desc
 */
class FriendSendListAdapter(context : Context) :
    RecyclerView.Adapter<FriendSendListAdapter.ViewHolder>() {

    private val inflater = LayoutInflater.from(context)
    private val items = mutableListOf<FriendSend>()

    private var isAllChecked = false

    init {
        prepareSampleData()
    }

    private fun prepareSampleData() {
        for (itemNumber in 1..30) {
            var description = "description [$itemNumber]"

            items.add(FriendSend("bb", description))
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(inflater.inflate(R.layout.item_friend_send_list, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setViewData(items, position)

        //holder.itemView.check_all

        holder.checkBox.setOnCheckedChangeListener(null)
        if (isAllChecked)
            holder.checkBox.isChecked = true
        else
            holder.checkBox.isChecked = false


        holder.checkBox.setOnCheckedChangeListener { buttonView, isChecked ->


        }
    }

    fun setAllCheck(isAllChecked : Boolean) {
        this.isAllChecked = isAllChecked
        notifyDataSetChanged()
    }

    fun getAllCheck() : Boolean
            = this.isAllChecked

    override fun getItemCount(): Int
            = items.size


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val textViewName : TextView = itemView.findViewById(R.id.textView_name)

        var checkBox : CheckBox = itemView.findViewById(R.id.check_select)

        fun setViewData(friendSend: MutableList<FriendSend>, position : Int) {
            textViewName.text = friendSend[position].name
        }

        fun selectAll(friendSend: MutableList<FriendSend>) {
            for (idx in friendSend) {
                itemView.check_select.isChecked = true
            }
        }

        fun unSelectAll(friendSend: MutableList<FriendSend>) {
            for (idx in friendSend) {
                itemView.check_select.isChecked = false
            }
        }
    }
}