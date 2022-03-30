package com.trusov.collapsingtoolbarviewtest.domain.use_case

import com.trusov.collapsingtoolbarviewtest.domain.entity.Category
import com.trusov.collapsingtoolbarviewtest.domain.entity.FoodItem
import com.trusov.collapsingtoolbarviewtest.domain.repository.ShopRepository
import javax.inject.Inject

class FilterListOfFoodItemsByCategoriesUseCase @Inject constructor(
    private val repository: ShopRepository
) {
    operator fun invoke(category: Category): List<FoodItem> {
        return repository.filterListOfFoodItemsByCategory(category)
    }
}