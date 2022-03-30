package com.trusov.collapsingtoolbarviewtest.presentation.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.trusov.collapsingtoolbarviewtest.domain.entity.Category
import com.trusov.collapsingtoolbarviewtest.domain.entity.FoodItem
import com.trusov.collapsingtoolbarviewtest.domain.use_case.FilterListOfFoodItemsByCategories
import com.trusov.collapsingtoolbarviewtest.domain.use_case.GetListOfCategoriesUseCase
import com.trusov.collapsingtoolbarviewtest.domain.use_case.GetListOfFoodItemsUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class MenuViewModel @Inject constructor(
    private val getListOfFoodItemsUseCase: GetListOfFoodItemsUseCase,
    private val getListOfCategoriesUseCase: GetListOfCategoriesUseCase,
    private val filterListOfFoodItemsByCategories: FilterListOfFoodItemsByCategories
) : ViewModel() {

    private var _listOfFoodItems = MutableLiveData<List<FoodItem>>()
    val listOfFoodItems: LiveData<List<FoodItem>> = _listOfFoodItems

    private var _listOfCategories = MutableLiveData<List<Category>>()
    val listOfCategories: LiveData<List<Category>> = _listOfCategories

    fun getListOfFoodItems() {
        viewModelScope.launch {
            _listOfFoodItems.postValue(getListOfFoodItemsUseCase())
        }
    }

    fun getListOfCategories() {
        viewModelScope.launch {
            _listOfCategories.postValue(getListOfCategoriesUseCase())
        }
    }

    fun filterByCategory(category: Category) {
        viewModelScope.launch {
            _listOfFoodItems.postValue(filterListOfFoodItemsByCategories(category))
        }
    }
}