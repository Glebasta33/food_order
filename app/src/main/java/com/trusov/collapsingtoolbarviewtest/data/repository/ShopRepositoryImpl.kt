package com.trusov.collapsingtoolbarviewtest.data.repository

import androidx.lifecycle.LiveData
import com.trusov.collapsingtoolbarviewtest.data.remote.source.RemoteDataSource
import com.trusov.collapsingtoolbarviewtest.domain.entity.Category
import com.trusov.collapsingtoolbarviewtest.domain.entity.FoodItem
import com.trusov.collapsingtoolbarviewtest.domain.repository.ShopRepository
import javax.inject.Inject

class ShopRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) : ShopRepository {
    override suspend fun getListOfFoodItems(): List<FoodItem> {
        return remoteDataSource.getListOfFoodItems()
    }

    override suspend fun getFoodItem(id: Int): FoodItem {
        return remoteDataSource.getFoodItem(id)
    }

    override suspend fun getListOfCategories(): List<Category> {
        return remoteDataSource.getListOfCategories()
    }

    override fun filterListOfFoodItemsByCategory(category: Category): List<FoodItem> {
        return remoteDataSource.filterListOfFoodItemsByCategory(category)
    }

    override fun orderFoodItem(item: FoodItem) {
        remoteDataSource.orderFoodItem(item)
    }

    override fun getListOfOrderedFoodItems(): List<FoodItem> {
        return remoteDataSource.getListOfOrderedFoodItems()
    }
}