package com.trusov.collapsingtoolbarviewtest.presentation.activity

import com.trusov.collapsingtoolbarviewtest.domain.entity.FoodItem

interface NavigationController {
    fun launchFoodItemDetailedFragment(item: FoodItem)
}