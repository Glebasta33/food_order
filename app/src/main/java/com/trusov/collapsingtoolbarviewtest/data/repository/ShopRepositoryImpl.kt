package com.trusov.collapsingtoolbarviewtest.data.repository

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.trusov.collapsingtoolbarviewtest.data.local.source.LocalDataSource
import com.trusov.collapsingtoolbarviewtest.data.remote.source.RemoteDataSource
import com.trusov.collapsingtoolbarviewtest.domain.entity.Category
import com.trusov.collapsingtoolbarviewtest.domain.entity.FoodItem
import com.trusov.collapsingtoolbarviewtest.domain.repository.ShopRepository
import javax.inject.Inject

class ShopRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val application: Application
) : ShopRepository {

    @RequiresApi(Build.VERSION_CODES.M)
    override suspend fun getListOfFoodItems(): List<FoodItem> {
        return if (isOnline(application)) {
            val listOfFoodItems = remoteDataSource.getListOfFoodItems()
            localDataSource.insertListOfFoodItems(listOfFoodItems)
            listOfFoodItems
        } else {
            localDataSource.getListOfFoodItems()
        }

    }

    override suspend fun getFoodItem(id: Int): FoodItem {
        return remoteDataSource.getFoodItem(id)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override suspend fun getListOfCategories(): List<Category> {
        return if (isOnline(application)) {
            val listOfCategories = remoteDataSource.getListOfCategories()
            localDataSource.insertListOfCategories(listOfCategories)
            listOfCategories
        } else {
            localDataSource.getListOfCategories()
        }

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

    companion object {
        @RequiresApi(Build.VERSION_CODES.M)
        fun isOnline(context: Context): Boolean {
            val connectivityManager =
                context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            if (connectivityManager != null) {
                val capabilities =
                    connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
                if (capabilities != null) {
                    if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                        Log.d("Internet", "NetworkCapabilities.TRANSPORT_CELLULAR")
                        return true
                    } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                        Log.d("Internet", "NetworkCapabilities.TRANSPORT_WIFI")
                        return true
                    } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) {
                        Log.d("Internet", "NetworkCapabilities.TRANSPORT_ETHERNET")
                        return true
                    }
                }
            }
            return false
        }
    }
}