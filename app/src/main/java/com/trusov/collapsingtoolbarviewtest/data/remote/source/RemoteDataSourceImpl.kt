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
        val itemDto = apiService.getItemById(id)
        val imageDto = apiService.getImageById(id)
        return mapper.mapDtoToFoodItem(itemDto, imageDto.bigImageUrl)
    }

    override suspend fun getListOfCategories(): List<Category> {
        return apiService.getListOfCategories().map { mapper.mapDtoToCategory(it) }
    }

    override fun filterListOfFoodItemsByCategory(category: Category): List<FoodItem> {
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

    override fun orderFoodItem(item: FoodItem) {
        item.isOrdered = !item.isOrdered
        if (orderedList == null) {
            orderedList = mutableListOf(item)
        } else {
            if (item.isOrdered) {
                orderedList?.add(item)
            } else {
                orderedList?.remove(item)
            }
        }
        val listOfTitles = mutableListOf<String>()
        for (i in orderedList!!) {
            listOfTitles.add(i.title)
        }
    }

    override fun getListOfOrderedFoodItems(): List<FoodItem> {
        return orderedList?.toMutableList() ?: listOf()
    }

    companion object {
        private lateinit var list: List<FoodItem>
        private var filteredList: MutableList<FoodItem>? = null
        private var orderedList: MutableList<FoodItem>? = null
    }

}