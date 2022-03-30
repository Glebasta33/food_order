package com.trusov.collapsingtoolbarviewtest.data.remote.mapper

import com.trusov.collapsingtoolbarviewtest.data.remote.dto.CategoryDto
import com.trusov.collapsingtoolbarviewtest.data.remote.dto.FoodItemDto
import com.trusov.collapsingtoolbarviewtest.domain.entity.Category
import com.trusov.collapsingtoolbarviewtest.domain.entity.FoodItem
import javax.inject.Inject

class ShopMapper @Inject constructor() {
    fun mapDtoToFoodItem(dto: FoodItemDto, url: String) = FoodItem(
        id = dto.id,
        title = dto.title,
        description = dto.description,
        imageUrl = url,
        categoryId = dto.categoryId
    )
    fun mapDtoToCategory(dto: CategoryDto) = Category(
        id = dto.id,
        title = dto.title,
        isActivated = false
    )
}