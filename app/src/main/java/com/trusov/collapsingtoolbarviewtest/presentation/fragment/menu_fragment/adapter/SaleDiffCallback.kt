package com.trusov.collapsingtoolbarviewtest.presentation.fragment.menu_fragment.adapter

import androidx.recyclerview.widget.DiffUtil
import com.trusov.collapsingtoolbarviewtest.domain.entity.Category
import com.trusov.collapsingtoolbarviewtest.domain.entity.FoodItem
import com.trusov.collapsingtoolbarviewtest.domain.entity.Sale

class SaleDiffCallback : DiffUtil.ItemCallback<Sale>() {
    override fun areItemsTheSame(oldItem: Sale, newItem: Sale): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Sale, newItem: Sale): Boolean {
        return oldItem == newItem
    }
}