package com.trusov.collapsingtoolbarviewtest.data.local.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.trusov.collapsingtoolbarviewtest.data.local.model.CategoryDbModel
import com.trusov.collapsingtoolbarviewtest.data.local.model.FoodItemDbModel

@Dao
interface ShopDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertListOfFoodItems(list: List<FoodItemDbModel>)

    @Query("SELECT * FROM food_item ORDER BY id")
    suspend fun getListOfFoodItems(): List<FoodItemDbModel>

    @Query("SELECT * FROM food_item ORDER BY categoryId = :categoryId")
    suspend fun filterListOfFoodItems(categoryId: Int): List<FoodItemDbModel>

    @Query("SELECT * FROM food_item WHERE id = :itemId LIMIT 1")
    suspend fun getFoodItemById(itemId: Int): FoodItemDbModel

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun orderFoodItem(item: FoodItemDbModel)

    @Query("SELECT * FROM food_item WHERE isOrdered = 1")
    suspend fun getListOfOrderedFoodItems(): List<FoodItemDbModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertListOfCategories(list: List<CategoryDbModel>)

    @Query("SELECT * FROM category ORDER BY id")
    suspend fun getListOfCategories(): List<CategoryDbModel>

}