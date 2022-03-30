package com.trusov.collapsingtoolbarviewtest.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.trusov.collapsingtoolbarviewtest.domain.entity.FoodItem
import com.trusov.collapsingtoolbarviewtest.R

class FoodRvAdapter : ListAdapter<FoodItem, FoodRvAdapter.ItemViewHolder>(ItemDiffCallback()) {

    class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title = view.findViewById<TextView>(R.id.tv_food_title)
        val description = view.findViewById<TextView>(R.id.tv_food_description)
        val price = view.findViewById<TextView>(R.id.tv_food_price)
        val image = view.findViewById<ImageView>(R.id.iv_food_image)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.food_rv_item_layout, parent, false)
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val foodItem = currentList[position]
        holder.title.text = foodItem.title.capitalize()
        holder.description.text = foodItem.description
        holder.price.text = "от ${foodItem.id} р."
        Picasso.get().load(foodItem.imageUrl)
            .placeholder(R.drawable.ic_launcher_background)
            .into(holder.image)
    }
}