package com.trusov.collapsingtoolbarviewtest.presentation.fragment.menu_fragment.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Callback
import com.squareup.picasso.NetworkPolicy
import com.squareup.picasso.Picasso
import com.trusov.collapsingtoolbarviewtest.R
import com.trusov.collapsingtoolbarviewtest.domain.entity.FoodItem
import java.lang.Exception

class FoodItemAdapter :
    ListAdapter<FoodItem, FoodItemAdapter.ItemViewHolder>(FoodItemDiffCallback()) {

    var onFoodItemLongClickListener: ((FoodItem) -> Unit)? = null
    var onFoodItemClickListener: ((FoodItem) -> Unit)? = null

    class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title = view.findViewById<TextView>(R.id.tv_food_title)
        val description = view.findViewById<TextView>(R.id.tv_food_description)
        val price = view.findViewById<TextView>(R.id.tv_food_price)
        val image = view.findViewById<ImageView>(R.id.iv_food_image)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val layout = when (viewType) {
            NOT_ORDERED_RC -> R.layout.food_not_ordered_rv_item_layout
            ORDERED_RC -> R.layout.food_ordered_rv_item_layout
            else -> throw RuntimeException("Unknown viewType: $viewType")
        }
        val view = LayoutInflater.from(parent.context).inflate(layout, parent, false)
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val foodItem = currentList[position]
        holder.title.text = foodItem.title.capitalize()
        holder.description.text = foodItem.description
        holder.price.text = "от ${foodItem.id} р."
        Picasso.get().load(foodItem.imageUrl)
            .networkPolicy(NetworkPolicy.OFFLINE)
            .placeholder(R.drawable.ic_launcher_background)
            .into(holder.image, object : Callback {
                override fun onSuccess() {
                }

                override fun onError(e: Exception?) {
                    Picasso.get().load(foodItem.imageUrl)
                        .networkPolicy(NetworkPolicy.OFFLINE)
                        .placeholder(R.drawable.ic_launcher_background)
                        .error(R.drawable.ic_launcher_background)
                        .into(holder.image)
                    Log.d("Picasso", "Picasso can`t fetch image")
                }

            })
        holder.itemView.setOnLongClickListener {
            onFoodItemLongClickListener?.invoke(foodItem)
            notifyItemChanged(position)
            true
        }
        holder.itemView.setOnClickListener {
            onFoodItemClickListener?.invoke(foodItem)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (currentList[position].isOrdered) {
            false -> NOT_ORDERED_RC
            true -> ORDERED_RC
        }
    }

    companion object {
        private const val ORDERED_RC = 200
        private const val NOT_ORDERED_RC = 400
    }
}