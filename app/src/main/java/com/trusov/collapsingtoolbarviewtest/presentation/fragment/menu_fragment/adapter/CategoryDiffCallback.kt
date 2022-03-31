package com.trusov.collapsingtoolbarviewtest.presentation.fragment.menu_fragment.adapter

import androidx.recyclerview.widget.DiffUtil
import com.trusov.collapsingtoolbarviewtest.domain.entity.Category

class CategoryDiffCallback : DiffUtil.ItemCallback<Category>() {
    override fun areItemsTheSame(oldItem: Category, newItem: Category): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Category, newItem: Category): Boolean {
        return oldItem == newItem
    }
}