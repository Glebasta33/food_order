package com.trusov.collapsingtoolbarviewtest.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "food_item")
data class FoodItemDbModel(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val title: String,
    val description: String,
    val imageUrl: String,
    val categoryId: Int,
    val isOrdered: Boolean
)
