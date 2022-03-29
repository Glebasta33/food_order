package com.trusov.collapsingtoolbarviewtest

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class TagRvAdapter : ListAdapter<Item, TagRvAdapter.ItemViewHolder>(ItemDiffCallback()) {

    class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val id = view.findViewById<TextView>(R.id.tv_tag_id)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.tag_rv_item_layout, parent, false)
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = currentList[position]
        holder.id.text = item.id.toString()
    }
}