package com.alio.ulio.view.ui.main.alarmlist.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.alio.ulio.R
import com.alio.ulio.custom.recyclerLayout.OnSwipeListener
import com.alio.ulio.db.entity.Alarm
import kotlinx.android.synthetic.main.item_main.view.*
import java.util.*

/**
 * @author Ha Jin Seok
 * @email seok270@dahami.com
 * @created 2022-03-03
 * @desc
 */
class SendAlarmAdapter(context : Context) : RecyclerView.Adapter<SendAlarmAdapter.SampleHolder>() {

    private val inflater = LayoutInflater.from(context)
    private val items = mutableListOf<Alarm>()

    private val random = Random()

    init {
        prepareSampleData()
    }

    private fun prepareSampleData() {
        for (itemNumber in 1..30) {
            val randomNumberOfDescriptions = random.nextInt(10)
            var description = ""

            for (d in 1..randomNumberOfDescriptions) description += "description [$d]\n"

            items.add(
                Alarm(
                    "Item [$itemNumber] should have [$randomNumberOfDescriptions] lines of description",
                    description
                )
            )
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SampleHolder {
        return SampleHolder(inflater.inflate(R.layout.item_send_main, parent, false))
    }

    override fun onBindViewHolder(holder: SampleHolder, position: Int) {
        holder.setSampleData(items[position], position)
    }

    override fun getItemCount(): Int = items.size

    inner class SampleHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun setSampleData(alarmData: Alarm, position : Int) {
            itemView.swipeContainer.setOnClickListener{
                Toast.makeText(it.context, "알림 수정", Toast.LENGTH_LONG).show()
            }

            itemView.swipeContainer.setOnSwipeListener(object : OnSwipeListener {
                override fun onSwipe(isExpanded: Boolean) {
                    alarmData.isExpanded = isExpanded
                }
            })

            itemView.layout_alarmOff.setOnClickListener {
                Toast.makeText(it.context, "알림 오프", Toast.LENGTH_LONG).show()
            }

            itemView.layout_delete.setOnClickListener {
                notifyItemRemoved(position)
            }

            itemView.layout_siren.setOnClickListener {
                Toast.makeText(it.context, "알림 신고", Toast.LENGTH_LONG).show()
            }

            itemView.swipeContainer.apply(alarmData.isExpanded)
        }
    }
}