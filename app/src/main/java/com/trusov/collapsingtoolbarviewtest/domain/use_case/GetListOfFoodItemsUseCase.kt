package com.trusov.collapsingtoolbarviewtest.domain.use_case

import androidx.lifecycle.LiveData
import com.trusov.collapsingtoolbarviewtest.domain.entity.FoodItem
import com.trusov.collapsingtoolbarviewtest.domain.repository.ShopRepository

class GetListOfFoodItemsUseCase(private val repository: ShopRepository) {
    operator fun invoke(): LiveData<List<FoodItem>> {
        return repository.getListOfFoodItems()
    }
}