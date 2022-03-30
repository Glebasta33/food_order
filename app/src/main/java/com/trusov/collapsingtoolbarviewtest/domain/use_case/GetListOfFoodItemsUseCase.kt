package com.trusov.collapsingtoolbarviewtest.domain.use_case

import androidx.lifecycle.LiveData
import com.trusov.collapsingtoolbarviewtest.domain.entity.FoodItem
import com.trusov.collapsingtoolbarviewtest.domain.repository.ShopRepository
import javax.inject.Inject

class GetListOfFoodItemsUseCase @Inject constructor(private val repository: ShopRepository) {
    suspend operator fun invoke(): List<FoodItem> {
        return repository.getListOfFoodItems()
    }
}