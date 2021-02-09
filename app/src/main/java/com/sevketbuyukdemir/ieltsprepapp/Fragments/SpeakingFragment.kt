package com.sevketbuyukdemir.ieltsprepapp.Fragments

import android.content.Context
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ListView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.sevketbuyukdemir.ieltsprepapp.R

class SpeakingFragment (private var sectionNo: Int,
                        private val dataSource: ArrayList<String>) : Fragment() {
    private lateinit var listView : ListView
    private lateinit var listViewAdapter: ListViewAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_speaking, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init(view)
    }

    private fun init(view : View){
        // Bind views with Ids
        listView = view.findViewById(R.id.speaking_tv_list_view)
        listViewAdapter = context?.let { ListViewAdapter(sectionNo, it, dataSource) }!!
        listView.adapter = listViewAdapter
    }

    private class ListViewAdapter(private var sectionNo : Int,
                                  private var context : Context,
                                  private val dataSource: ArrayList<String>) : BaseAdapter() {

        private val inflater: LayoutInflater =
            context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        override fun getCount(): Int {
            return dataSource.size
        }

        override fun getItem(position: Int): Any {
            return dataSource[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
        override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
            // Get view for row item
            val rowView = inflater.inflate(R.layout.speaking_text_item, parent, false)

            val textView = rowView.findViewById(R.id.tv_speaking_text_item) as TextView
            if(position == 0) {
                textView.setTextColor(Color.RED)
                textView.textSize = 20F
                textView.textAlignment = TextView.TEXT_ALIGNMENT_CENTER
            } else if(position == 1) {
                textView.setTextColor(Color.GRAY)
                textView.textSize = 18F
                textView.textAlignment = TextView.TEXT_ALIGNMENT_CENTER
            } else if(position > 1) {
                textView.setTextColor(Color.WHITE)
                textView.textSize = 16F
            }
            textView.text = dataSource[position]
            return rowView
        }
    }
}