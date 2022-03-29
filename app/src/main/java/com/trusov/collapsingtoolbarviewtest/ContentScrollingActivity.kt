package com.trusov.collapsingtoolbarviewtest

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView

class ContentScrollingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.content_scrolling)
        val rvFoods = findViewById<RecyclerView>(R.id.rv_foods)
        val list = listOf<Item>(
            Item(1),
            Item(2),
            Item(3),
            Item(4),
            Item(5),
        )
        rvFoods.adapter = RvAdapter().apply {
            submitList(list)
        }
    }
}