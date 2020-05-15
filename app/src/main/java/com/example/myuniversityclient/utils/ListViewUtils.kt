package com.example.myuniversityclient.utils

import android.view.ViewGroup
import android.widget.ListView

class ListViewUtils {
    companion object{
        fun justifyListViewHeightBasedOnChildren(listView: ListView) {
            val adapter = listView.adapter ?: return
            val vg: ViewGroup = listView
            var totalHeight = 0
            for (i in 0 until adapter.count) {
                val listItem = adapter.getView(i, null, vg)
                listItem.measure(0, 0)
                totalHeight += listItem.measuredHeight
            }
            val par = listView.layoutParams
            par.height = totalHeight + listView.dividerHeight * (adapter.count - 1)
            listView.layoutParams = par
            listView.requestLayout()
        }
    }
}