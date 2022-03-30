package com.trusov.collapsingtoolbarviewtest.domain.use_case

import androidx.lifecycle.LiveData
import com.trusov.collapsingtoolbarviewtest.domain.entity.FoodItem
import com.trusov.collapsingtoolbarviewtest.domain.repository.ShopRepository

class GetFoodItemUseCase(private val repository: ShopRepository) {
    suspend operator fun invoke(id: Int): FoodItem {
        return repository.getFoodItem(id)
    }
}