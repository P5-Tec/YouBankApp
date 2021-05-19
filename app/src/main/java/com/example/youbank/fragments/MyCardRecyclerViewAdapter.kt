package com.example.youbank.fragments

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.youbank.R
import com.example.youbank.fragments.dummy.DummyContent.DummyItem


class MyCardRecyclerViewAdapter(private val values: List<DummyItem>, private val listener: OnItemClickListener) :
    RecyclerView.Adapter<MyCardRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_item, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.idView.text = item.id

    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {
        val idView: TextView = view.findViewById(R.id.txtBalance)

        init {
            view.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val position: Int = absoluteAdapterPosition
            if (v != null) {
                Toast.makeText(v.context, "${position + 1} clicked", Toast.LENGTH_SHORT).show()
            }

            listener.onItemClick()
        }
    }
    interface OnItemClickListener{
        fun onItemClick()
    }

}