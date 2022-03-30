package com.trusov.collapsingtoolbarviewtest.domain.repository

import androidx.lifecycle.LiveData
import com.trusov.collapsingtoolbarviewtest.domain.entity.FoodItem

interface ShopRepository {
    fun getListOfFoodItems(): LiveData<List<FoodItem>>
    suspend fun getFoodItem(id: Int): FoodItem
}