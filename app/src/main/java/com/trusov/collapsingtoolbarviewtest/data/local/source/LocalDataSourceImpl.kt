package com.trusov.collapsingtoolbarviewtest.data.local.source

import android.util.Log
import com.trusov.collapsingtoolbarviewtest.data.local.database.ShopDao
import com.trusov.collapsingtoolbarviewtest.data.local.mapper.DbModelMapper
import com.trusov.collapsingtoolbarviewtest.domain.entity.Category
import com.trusov.collapsingtoolbarviewtest.domain.entity.FoodItem
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(
    private val mapper: DbModelMapper,
    private val shopDao: ShopDao
) : LocalDataSource {

    override suspend fun insertListOfFoodItems(list: List<FoodItem>) {
        shopDao.insertListOfFoodItems(list.map { mapper.mapFoodItemEntityToDbModel(it) })
    }

    override suspend fun insertListOfCategories(list: List<Category>) {
        shopDao.insertListOfCategories(list.map { mapper.mapCategoryEntityToDbModel(it) })
    }

    override suspend fun getListOfFoodItems(): List<FoodItem> {
        val foodItems = shopDao.getListOfFoodItems()
        val activatedCategories = shopDao.getListOfCategories().filter { it.isActivated }
        if (activatedCategories.isNotEmpty()) {
            for (category in activatedCategories) {
                filterListOfFoodItemsByCategory(mapper.mapCategoryDbModelToEntity(category))
            }
            return filteredList?.toList() ?: foodItems.map { mapper.mapFoodItemDbModelToEntity(it) }
        }
        return foodItems.map { mapper.mapFoodItemDbModelToEntity(it) }
    }

    override suspend fun getFoodItem(id: Int): FoodItem {
        return mapper.mapFoodItemDbModelToEntity(shopDao.getFoodItemById(id))
    }

    override suspend fun getListOfCategories(): List<Category> {
        val categories = shopDao.getListOfCategories()
        return categories.map { mapper.mapCategoryDbModelToEntity(it) }
    }


    override suspend fun filterListOfFoodItemsByCategory(category: Category): List<FoodItem> {
        shopDao.updateCategoryStatus(mapper.mapCategoryEntityToDbModel(category))
        val newFoodItems = shopDao.filterListOfFoodItems(category.id)
            .map { mapper.mapFoodItemDbModelToEntity(it) }
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
            return getListOfFoodItems()
        }
        filteredList?.sortBy { it.id }
        return filteredList ?: newFoodItems.toMutableList()

    }

    override suspend fun orderFoodItem(item: FoodItem) {
        item.isOrdered = !item.isOrdered
        shopDao.orderFoodItem(mapper.mapFoodItemEntityToDbModel(item))
    }

    override suspend fun getListOfOrderedFoodItems(): List<FoodItem> {
        return shopDao.getListOfOrderedFoodItems().map { mapper.mapFoodItemDbModelToEntity(it) }
    }

    companion object {
        private lateinit var list: List<FoodItem>
        private var filteredList: MutableList<FoodItem>? = null
        private var orderedList: MutableList<FoodItem>? = null
    }

}