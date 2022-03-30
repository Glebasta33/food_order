package com.trusov.collapsingtoolbarviewtest.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import com.trusov.collapsingtoolbarviewtest.domain.entity.FoodItem

class ItemDiffCallback : DiffUtil.ItemCallback<FoodItem>() {
    override fun areItemsTheSame(oldItem: FoodItem, newItem: FoodItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: FoodItem, newItem: FoodItem): Boolean {
        return oldItem == newItem
    }
}