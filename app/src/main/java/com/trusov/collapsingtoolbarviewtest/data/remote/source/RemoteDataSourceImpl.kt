package com.trusov.collapsingtoolbarviewtest.data.remote.source

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.trusov.collapsingtoolbarviewtest.data.remote.mapper.ShopMapper
import com.trusov.collapsingtoolbarviewtest.data.remote.retrofit.ApiService
import com.trusov.collapsingtoolbarviewtest.domain.entity.FoodItem
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(
    private val mapper: ShopMapper,
    private val apiService: ApiService
) : RemoteDataSource {
    override suspend fun getListOfFoodItems(): List<FoodItem> {
        val images = apiService.getListOfImages().take(100)
        return apiService.getListOfFoodItems().map { mapper.mapDtoToEntity(
            it,
            images[it.id - 1].smallImageUrl
        ) }

    }

    override suspend fun getFoodItem(id: Int): FoodItem {
        TODO("Not yet implemented")
    }
}