package com.trusov.collapsingtoolbarviewtest.presentation.util

import com.trusov.collapsingtoolbarviewtest.domain.entity.FoodItem

interface NavigationController {
    fun launchFoodItemDetailedFragment(item: FoodItem)
}