package com.trusov.collapsingtoolbarviewtest.domain.use_case

import com.trusov.collapsingtoolbarviewtest.domain.entity.FoodItem
import com.trusov.collapsingtoolbarviewtest.domain.repository.ShopRepository
import javax.inject.Inject

class OrderFoodItemUseCase @Inject constructor(private val repository: ShopRepository) {
    suspend operator fun invoke(item: FoodItem) {
        repository.orderFoodItem(item)
    }
}