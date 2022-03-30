package com.trusov.collapsingtoolbarviewtest.data.repository

import androidx.lifecycle.LiveData
import com.trusov.collapsingtoolbarviewtest.data.remote.source.RemoteDataSource
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
}