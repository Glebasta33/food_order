package com.trusov.collapsingtoolbarviewtest.data.local.mapper

import com.trusov.collapsingtoolbarviewtest.data.local.model.CategoryDbModel
import com.trusov.collapsingtoolbarviewtest.data.local.model.FoodItemDbModel
import com.trusov.collapsingtoolbarviewtest.data.remote.dto.CategoryDto
import com.trusov.collapsingtoolbarviewtest.data.remote.dto.FoodItemDto
import com.trusov.collapsingtoolbarviewtest.domain.entity.Category
import com.trusov.collapsingtoolbarviewtest.domain.entity.FoodItem
import javax.inject.Inject

class DbModelMapper @Inject constructor() {

    fun mapFoodItemDbModelToEntity(dbModel: FoodItemDbModel) = FoodItem(
        id = dbModel.id,
        title = dbModel.title,
        description = dbModel.description,
        imageUrl = dbModel.imageUrl,
        categoryId = dbModel.categoryId,
        isOrdered = dbModel.isOrdered
    )

    fun mapFoodItemEntityToDbModel(entity: FoodItem) = FoodItemDbModel(
        id = entity.id,
        title = entity.title,
        description = entity.description,
        imageUrl = entity.imageUrl,
        categoryId = entity.categoryId,
        isOrdered = entity.isOrdered
    )

    fun mapCategoryDbModelToEntity(dbModel: CategoryDbModel) = Category(
        id = dbModel.id,
        title = dbModel.title,
        isActivated = dbModel.isActivated
    )

    fun mapCategoryEntityToDbModel(entity: Category) = CategoryDbModel(
        id = entity.id,
        title = entity.title,
        isActivated = entity.isActivated
    )
}