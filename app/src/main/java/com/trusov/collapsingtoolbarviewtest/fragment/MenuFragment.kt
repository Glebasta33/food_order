package com.trusov.collapsingtoolbarviewtest.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.trusov.collapsingtoolbarviewtest.*

class MenuFragment : Fragment(R.layout.fragment_menu) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val rvSales = view.findViewById<RecyclerView>(R.id.rv_sales)
        rvSales.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        val list = listOf<Item>(
            Item(1),
            Item(2),
            Item(3),
            Item(4),
            Item(5),
            Item(6),
            Item(7),
            Item(8),
            Item(9),
            Item(10),
        )
        rvSales.adapter = RvAdapter().apply {
            submitList(list)
        }

        val rvFoods = view.findViewById<RecyclerView>(R.id.rv_foods)
        rvFoods.adapter = FoodRvAdapter().apply {
            submitList(list)
        }

        val rvTags = view.findViewById<RecyclerView>(R.id.rv_tags)
        rvTags.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        rvTags.adapter = TagRvAdapter().apply {
            submitList(list)
        }
    }
}