package com.alio.ulio.view.ui.main.profilemanage.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import com.alio.ulio.R

class QuestionExpandableListAdapter internal constructor(
    private val context : Context,
    private val titleList : List<String>,
    private val dataList : HashMap<String, List<String>>
) : BaseExpandableListAdapter() {
    override fun getGroupCount(): Int {
        return this.titleList.size
    }

    override fun getChildrenCount(p0: Int): Int {
        return this.dataList[this.titleList[p0]]!!.size
    }

    override fun getGroup(p0: Int): Any {
        return this.titleList[p0]
    }

    override fun getChild(p0: Int, p1: Int): Any {
        return this.dataList[this.titleList[p0]]!![p1]
    }

    override fun getGroupId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getChildId(p0: Int, p1: Int): Long {
        return p1.toLong()
    }

    override fun hasStableIds(): Boolean {
        return false
    }

    override fun getGroupView(listPosition: Int, isExpanded: Boolean, convertView: View?, parent: ViewGroup?): View {
        var convertView = convertView
        val listTitle = getGroup(listPosition) as String
        if (convertView == null) {
            val layoutInflater =
                this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            convertView = layoutInflater.inflate(R.layout.item_question_title, null)
        }

        val expandableTitleTextView = convertView!!.findViewById<TextView>(R.id.textView_title)
        expandableTitleTextView.text = listTitle

        val expandableImage = convertView!!.findViewById<AppCompatImageView>(R.id.imageView_updown)

        if (isExpanded) {
            expandableImage.setImageResource(R.drawable.ic_btn__icon_close)
        } else {
            expandableImage.setImageResource(R.drawable.ic_btn__icon_open)
        }

        return convertView!!
    }

    override fun getChildView(listPosition: Int, expandedListPosition: Int, isLastChild: Boolean, convertView: View?, parent: ViewGroup?): View {
        var convertView = convertView
        val listContent = getChild(listPosition, expandedListPosition) as String
        if (convertView == null) {
            val layoutInflater = this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            convertView = layoutInflater.inflate(R.layout.item_question_content, null)
        }

        val expandableContentTextView = convertView!!.findViewById<TextView>(R.id.textView_content)
        expandableContentTextView.text = listContent

        return convertView!!
    }

    override fun isChildSelectable(p0: Int, p1: Int): Boolean {
        return true
    }
}