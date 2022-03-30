package com.trusov.collapsingtoolbarviewtest.presentation.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.trusov.collapsingtoolbarviewtest.*
import com.trusov.collapsingtoolbarviewtest.domain.entity.FoodItem
import com.trusov.collapsingtoolbarviewtest.presentation.adapter.FoodRvAdapter
import com.trusov.collapsingtoolbarviewtest.presentation.adapter.RvAdapter
import com.trusov.collapsingtoolbarviewtest.presentation.adapter.TagRvAdapter

class MenuFragment : Fragment(R.layout.fragment_menu) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val rvSales = view.findViewById<RecyclerView>(R.id.rv_sales)
        rvSales.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        val list = listOf<FoodItem>(
            FoodItem(1, "", "", ""),
            FoodItem(2,"", "", ""),
            FoodItem(3,"", "", ""),
            FoodItem(4,"", "", ""),
            FoodItem(5,"", "", ""),
            FoodItem(6,"", "", ""),
            FoodItem(7,"", "", ""),
            FoodItem(8,"", "", ""),
            FoodItem(9,"", "", ""),
            FoodItem(10,"", "", "")
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