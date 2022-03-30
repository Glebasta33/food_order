package com.trusov.collapsingtoolbarviewtest.data.remote.source

import com.trusov.collapsingtoolbarviewtest.data.remote.mapper.ShopMapper
import com.trusov.collapsingtoolbarviewtest.data.remote.retrofit.ApiService
import com.trusov.collapsingtoolbarviewtest.domain.entity.Category
import com.trusov.collapsingtoolbarviewtest.domain.entity.FoodItem
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(
    private val mapper: ShopMapper,
    private val apiService: ApiService
) : RemoteDataSource {

    override suspend fun getListOfFoodItems(): List<FoodItem> {
        val images = apiService.getListOfImages().take(500)
        list = apiService.getListOfFoodItems().map {
            mapper.mapDtoToFoodItem(
                it,
                images[it.id - 1].smallImageUrl
            )
        }
        return list
    }

    override suspend fun getFoodItem(id: Int): FoodItem {
        TODO("Not yet implemented")
    }

    override suspend fun getListOfCategories(): List<Category> {
        return apiService.getListOfCategories().map { mapper.mapDtoToCategory(it) }
    }

    override suspend fun filterListOfFoodItemsByCategory(category: Category): List<FoodItem> {
        val newFoodItems = list.filter { it.categoryId == category.id }
        if (filteredList == null) {
            filteredList = newFoodItems.toMutableList()
        } else {
            if (category.isActivated) {
                filteredList?.addAll(newFoodItems)
            } else {
                filteredList?.removeAll(newFoodItems)
            }
        }
        if (filteredList?.size == 0) {
            return list
        }
        filteredList?.sortBy { it.id }
        return filteredList ?: newFoodItems.toMutableList()
    }

    companion object {
        private lateinit var list: List<FoodItem>
        private var filteredList: MutableList<FoodItem>? = null
    }

}