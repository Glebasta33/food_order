package com.trusov.collapsingtoolbarviewtest.data.local.source

import com.trusov.collapsingtoolbarviewtest.domain.entity.Category
import com.trusov.collapsingtoolbarviewtest.domain.entity.FoodItem

interface LocalDataSource {
    suspend fun insertListOfFoodItems(list: List<FoodItem>)
    suspend fun insertListOfCategories(list: List<Category>)
    suspend fun getListOfFoodItems(): List<FoodItem>
    suspend fun getFoodItem(id: Int): FoodItem
    suspend fun getListOfCategories(): List<Category>
    suspend fun filterListOfFoodItemsByCategory(category: Category): List<FoodItem>
    suspend fun getListOfOrderedFoodItems(): List<FoodItem>
    suspend fun orderFoodItem(item: FoodItem)
}