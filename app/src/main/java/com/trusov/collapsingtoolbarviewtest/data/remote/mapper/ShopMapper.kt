package com.trusov.collapsingtoolbarviewtest.data.remote.mapper

import com.trusov.collapsingtoolbarviewtest.data.remote.dto.FoodItemDto
import com.trusov.collapsingtoolbarviewtest.domain.entity.FoodItem
import javax.inject.Inject

class ShopMapper @Inject constructor() {
    fun mapDtoToEntity(dto: FoodItemDto, url: String) = FoodItem(
        id = dto.id,
        title = dto.title,
        description = dto.description,
        imageUrl = url
    )
}