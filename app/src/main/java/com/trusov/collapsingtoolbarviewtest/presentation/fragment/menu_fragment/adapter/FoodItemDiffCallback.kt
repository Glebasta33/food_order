package com.trusov.collapsingtoolbarviewtest.presentation.fragment.menu_fragment.adapter

import androidx.recyclerview.widget.DiffUtil
import com.trusov.collapsingtoolbarviewtest.domain.entity.FoodItem

class FoodItemDiffCallback : DiffUtil.ItemCallback<FoodItem>() {
    override fun areItemsTheSame(oldItem: FoodItem, newItem: FoodItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: FoodItem, newItem: FoodItem): Boolean {
        return oldItem == newItem
    }
}