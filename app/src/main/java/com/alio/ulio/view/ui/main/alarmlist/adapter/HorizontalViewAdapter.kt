package com.alio.ulio.view.ui.main.alarmlist.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alio.ulio.R
import com.alio.ulio.db.entity.Alarm
import kotlinx.android.synthetic.main.item_listener_main.view.*
import kotlinx.android.synthetic.main.item_main.view.titleText
import java.util.*

/**
 * @author Ha Jin Seok
 * @email seok270@dahami.com
 * @created 2022-03-04
 * @desc
 */
class HorizontalViewAdapter(context : Context) : RecyclerView.Adapter<HorizontalViewAdapter.ViewHolder>() {

    private val inflater = LayoutInflater.from(context)
    private val items = mutableListOf<Alarm>()

    private val random = Random()

    init {
        prepareSampleData()
    }

    private fun prepareSampleData() {
        for (itemNumber in 1..30) {
            val randomNumberOfDescriptions = random.nextInt(10)

            items.add(
                Alarm(
                    "description [$itemNumber]",
                    "[$randomNumberOfDescriptions] description"
                )
            )
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(inflater.inflate(R.layout.item_listener_main, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setViewData(items[position])
    }

    override fun getItemCount(): Int = items.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun setViewData(alarmData: Alarm) {
            itemView.titleText.text = alarmData.hour
            itemView.contentText.text = alarmData.minute
        }
    }
}