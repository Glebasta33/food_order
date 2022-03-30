package com.trusov.collapsingtoolbarviewtest.data.remote.source

import androidx.lifecycle.LiveData
import com.trusov.collapsingtoolbarviewtest.domain.entity.Category
import com.trusov.collapsingtoolbarviewtest.domain.entity.FoodItem

interface RemoteDataSource {
    suspend fun getListOfFoodItems(): List<FoodItem>
    suspend fun getFoodItem(id: Int): FoodItem
    suspend fun getListOfCategories(): List<Category>
    fun filterListOfFoodItemsByCategory(category: Category): List<FoodItem>
    fun orderFoodItem(item: FoodItem)
    fun getListOfOrderedFoodItems(): List<FoodItem>
}