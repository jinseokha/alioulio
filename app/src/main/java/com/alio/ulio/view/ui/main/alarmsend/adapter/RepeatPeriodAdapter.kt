package com.alio.ulio.view.ui.main.alarmsend.adapter

import android.content.Context
import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.alio.ulio.R
import com.alio.ulio.db.entity.RepeatPeriod
import kotlinx.android.synthetic.main.list_repeat_period_item.view.*

/**
 * @author Ha Jin Seok
 * @email seok270@gmail.com
 * @created 2022-03-28
 * @desc
 */
class RepeatPeriodAdapter(context : Context) : ListAdapter<RepeatPeriod, RepeatPeriodAdapter.MyViewHolder>(DiffUtils()) {

    private var selectedExpense = arrayListOf<RepeatPeriod>()
    private val inflater = LayoutInflater.from(context)

    inner class MyViewHolder(
        itemView : View
    ) : RecyclerView.ViewHolder(itemView) {

        fun bind(repeatPeriod: RepeatPeriod) {
            itemView.txt_title.text = repeatPeriod.title

            itemView.setOnClickListener {
                applySelection(itemView, repeatPeriod)
                onItemClickListener?.let { it(repeatPeriod) }
            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        //MyViewHolder(ListRepeatPeriodItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
        MyViewHolder(inflater.inflate(R.layout.list_repeat_period_item, parent, false))

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    private fun applySelection(binding: View, expense: RepeatPeriod) {
        if (selectedExpense.contains(expense)) {
            selectedExpense.remove(expense)
            binding.txt_title.typeface = Typeface.DEFAULT
            changeBackground(binding, R.drawable.selector_btn_clock)
        } else {
            selectedExpense.add(expense)
            binding.txt_title.typeface = Typeface.DEFAULT_BOLD
            changeBackground(binding, R.drawable.select_btn_click_clock)
        }
    }

    private fun changeBackground(binding: View, resId: Int) {
        //binding.layout_container.setBackgroundResource(ContextCompat.getColor(binding.context, resId))
        binding.layout_container.background = ContextCompat.getDrawable(binding.context, resId)
    }

    fun getSelectedExpense() : List<RepeatPeriod> {
        var typeList = selectedExpense.sortedBy { it.sort }
        return typeList
    }

    private class DiffUtils : DiffUtil.ItemCallback<RepeatPeriod>() {
        override fun areItemsTheSame(oldItem: RepeatPeriod, newItem: RepeatPeriod): Boolean {
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(oldItem: RepeatPeriod, newItem: RepeatPeriod): Boolean {
            return oldItem == newItem
        }
    }


    private var onItemClickListener: ((RepeatPeriod) -> Unit)? = null

    fun setOnItemClickListener(listener: (RepeatPeriod) -> Unit) {
        onItemClickListener = listener
    }

}