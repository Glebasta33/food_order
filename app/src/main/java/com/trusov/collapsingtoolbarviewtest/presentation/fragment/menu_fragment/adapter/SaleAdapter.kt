package com.trusov.collapsingtoolbarviewtest.presentation.fragment.menu_fragment.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.trusov.collapsingtoolbarviewtest.R
import com.trusov.collapsingtoolbarviewtest.domain.entity.Sale

class SaleAdapter : ListAdapter<Sale, SaleAdapter.ItemViewHolder>(SaleDiffCallback()) {

    init {
        submitList(
            mutableListOf<Sale>().apply {
                for (id in 1..10) {
                    add(Sale(id))
                }
            }
        )
    }

    class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.sale_rv_item_layout, parent, false)
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
    }
}