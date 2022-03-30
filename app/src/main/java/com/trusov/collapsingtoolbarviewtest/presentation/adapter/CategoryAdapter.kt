package com.trusov.collapsingtoolbarviewtest.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.trusov.collapsingtoolbarviewtest.domain.entity.FoodItem
import com.trusov.collapsingtoolbarviewtest.R
import com.trusov.collapsingtoolbarviewtest.domain.entity.Category

class CategoryAdapter : ListAdapter<Category, CategoryAdapter.ItemViewHolder>(CategoryDiffCallback()) {

    var onCategoryItemClickListener: ((Category) -> Unit)? = null

    class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title = view.findViewById<TextView>(R.id.tv_category_title)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val layout = when(viewType){
            NOT_ACTIVE_RC -> R.layout.category_not_activated_rv_item_layout
            ACTIVE_RC -> R.layout.category_activated_rv_item_layout
            else -> throw RuntimeException("Unknown viewType: $viewType")
        }
        val view = LayoutInflater.from(parent.context).inflate(layout, parent, false)
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val category = currentList[position]
        val title = category.title.split(" ")[0].capitalize()
        holder.title.text = title
        holder.title.setOnClickListener {
            onCategoryItemClickListener?.invoke(category)
            notifyItemChanged(position)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (currentList[position].isActivated) {
            false -> NOT_ACTIVE_RC
            true -> ACTIVE_RC
        }
    }

    companion object {
        private const val ACTIVE_RC = 200
        private const val NOT_ACTIVE_RC = 400
    }
}