package com.trusov.collapsingtoolbarviewtest.data.repository

import android.os.Build
import androidx.annotation.RequiresApi
import com.trusov.collapsingtoolbarviewtest.data.local.source.LocalDataSource
import com.trusov.collapsingtoolbarviewtest.data.remote.source.RemoteDataSource
import com.trusov.collapsingtoolbarviewtest.domain.entity.Category
import com.trusov.collapsingtoolbarviewtest.domain.entity.FoodItem
import com.trusov.collapsingtoolbarviewtest.domain.repository.ShopRepository
import com.trusov.collapsingtoolbarviewtest.util.NetworkChecker
import javax.inject.Inject

class ShopRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val networkChecker: NetworkChecker
) : ShopRepository {

    @RequiresApi(Build.VERSION_CODES.M)
    override suspend fun getListOfFoodItems(): List<FoodItem> {
        return if (networkChecker.isOnline()) {
            val listOfFoodItems = remoteDataSource.getListOfFoodItems()
            localDataSource.insertListOfFoodItems(listOfFoodItems)
            listOfFoodItems
        } else {
            localDataSource.getListOfFoodItems()
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override suspend fun getFoodItem(id: Int): FoodItem {
        return if (networkChecker.isOnline()) {
            remoteDataSource.getFoodItem(id)
        } else {
            localDataSource.getFoodItem(id)
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override suspend fun getListOfCategories(): List<Category> {
        return if (networkChecker.isOnline()) {
            val listOfCategories = remoteDataSource.getListOfCategories()
            localDataSource.insertListOfCategories(listOfCategories)
            listOfCategories
        } else {
            localDataSource.getListOfCategories()
        }

    }

    @RequiresApi(Build.VERSION_CODES.M)
    override suspend fun filterListOfFoodItemsByCategory(category: Category): List<FoodItem> {
        return if (networkChecker.isOnline()) {
            remoteDataSource.filterListOfFoodItemsByCategory(category)
        } else {
            localDataSource.filterListOfFoodItemsByCategory(category)
        }

    }

    @RequiresApi(Build.VERSION_CODES.M)
    override suspend fun orderFoodItem(item: FoodItem) {
        return if (networkChecker.isOnline()) {
            remoteDataSource.orderFoodItem(item)
        } else {
            localDataSource.orderFoodItem(item)
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override suspend fun getListOfOrderedFoodItems(): List<FoodItem> {
        return if (networkChecker.isOnline()) {
            remoteDataSource.getListOfOrderedFoodItems()
        } else {
            localDataSource.getListOfOrderedFoodItems()
        }
    }
}