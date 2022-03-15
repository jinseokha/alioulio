package com.alio.ulio.view.ui.main.profilemanage.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.alio.ulio.R
import com.alio.ulio.db.entity.Alarm
import com.alio.ulio.view.ui.main.profilemanage.NotifyActivity
import com.alio.ulio.view.ui.main.profilemanage.model.Notify
import kotlinx.android.synthetic.main.item_notice.view.*
import java.util.*

/**
 * @author Ha Jin Seok
 * @email seok270@gmail.com
 * @created 2022-03-09
 * @desc
 */

class NotifyAdapter(context : Context) : RecyclerView.Adapter<NotifyAdapter.ViewHolder>() {

    private val inflater = LayoutInflater.from(context)
    private val items = mutableListOf<Notify>()

    private val random = Random()

    init {
        prepareSampleData()
    }
    private fun prepareSampleData() {
        for (itemNumber in 1..30) {
            val randomNumberOfDescriptions = random.nextInt(10)

            items.add(Notify("descri [$itemNumber]", "content test"))
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(inflater.inflate(R.layout.item_notice, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setViewData(items[position])
    }

    override fun getItemCount(): Int = items.size

    inner class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        fun setViewData(notify: Notify) {
            itemView.textView_notice.text = notify.title

            itemView.setOnClickListener { it ->
                val intent = Intent(itemView?.context, NotifyActivity::class.java)
                intent.putExtra("notify", notify)
                ContextCompat.startActivity(itemView.context, intent, null)
            }
        }
    }
}