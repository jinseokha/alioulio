package com.alio.ulio.view.ui.main.alarmlist.adapter

import android.content.Context
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.alio.ulio.R
import com.alio.ulio.db.entity.Alarm
import com.alio.ulio.view.dialog.AlarmListeningDialog
import kotlinx.android.synthetic.main.item_listener_main.view.*
import kotlinx.android.synthetic.main.item_main.view.titleText
import java.util.*

/**
 * @author Ha Jin Seok
 * @email seok270@gmail.com
 * @created 2022-03-04
 * @desc
 */
class HorizontalViewAdapter(context : Context, fragmentManager : FragmentManager) : RecyclerView.Adapter<HorizontalViewAdapter.ViewHolder>() {
    private var mFragmentManager : FragmentManager

    init {
        mFragmentManager = fragmentManager
    }
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

        holder.setViewData(items, position, mFragmentManager)
    }

    override fun getItemCount(): Int = items.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val alarmListeningDialog : AlarmListeningDialog by lazy {
            AlarmListeningDialog(R.layout.dialog_alarm_listening)
        }

        fun setViewData(alarmData: MutableList<Alarm>, position : Int, fragmentManager: FragmentManager) {

            var size = alarmData.size - 1
            if (position == size) {
                itemView.layout_listener.margin(right = 20F)
            }

            itemView.titleText.text = alarmData[position].hour
            itemView.contentText.text = alarmData[position].minute

            itemView.layout_listener.setOnClickListener {
                alarmListeningDialog.show(fragmentManager, null)
            }
        }

        fun View.margin(left: Float? = null, top: Float? = null, right: Float? = null, bottom: Float? = null) {
            layoutParams<ViewGroup.MarginLayoutParams> {
                left?.run { leftMargin = dpToPx(this) }
                top?.run { topMargin = dpToPx(this) }
                right?.run { rightMargin = dpToPx(this) }
                bottom?.run { bottomMargin = dpToPx(this) }
            }
        }

        inline fun <reified T : ViewGroup.LayoutParams> View.layoutParams(block: T.() -> Unit) {
            if (layoutParams is T) block(layoutParams as T)
        }

        fun View.dpToPx(dp: Float): Int = context.dpToPx(dp)
        fun Context.dpToPx(dp: Float): Int = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, resources.displayMetrics).toInt()
    }
}