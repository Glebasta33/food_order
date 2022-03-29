package com.trusov.collapsingtoolbarviewtest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.appbar.CollapsingToolbarLayout

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val rvSales = findViewById<RecyclerView>(R.id.rv_sales)
        rvSales.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
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

        val rvFoods = findViewById<RecyclerView>(R.id.rv_foods)
        rvFoods.adapter = FoodRvAdapter().apply {
            submitList(list)
        }

    }
}