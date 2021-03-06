package com.trusov.collapsingtoolbarviewtest.presentation.fragment.menu_fragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.trusov.collapsingtoolbarviewtest.domain.entity.Category
import com.trusov.collapsingtoolbarviewtest.domain.entity.FoodItem
import com.trusov.collapsingtoolbarviewtest.domain.use_case.FilterListOfFoodItemsByCategoriesUseCase
import com.trusov.collapsingtoolbarviewtest.domain.use_case.GetListOfCategoriesUseCase
import com.trusov.collapsingtoolbarviewtest.domain.use_case.GetListOfFoodItemsUseCase
import com.trusov.collapsingtoolbarviewtest.domain.use_case.OrderFoodItemUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class MenuViewModel @Inject constructor(
    private val getListOfFoodItemsUseCase: GetListOfFoodItemsUseCase,
    private val getListOfCategoriesUseCase: GetListOfCategoriesUseCase,
    private val filterListOfFoodItemsByCategoriesUseCase: FilterListOfFoodItemsByCategoriesUseCase,
    private val orderFoodItemUseCase: OrderFoodItemUseCase
) : ViewModel() {

    private var _listOfFoodItems = MutableLiveData<List<FoodItem>>()
    val listOfFoodItems: LiveData<List<FoodItem>> = _listOfFoodItems

    private var _listOfCategories = MutableLiveData<List<Category>>()
    val listOfCategories: LiveData<List<Category>> = _listOfCategories

    init {
        viewModelScope.launch {
            _listOfFoodItems.postValue(getListOfFoodItemsUseCase())
            _listOfCategories.postValue(getListOfCategoriesUseCase())
        }
    }

    fun filterByCategory(category: Category) {
        CoroutineScope(Dispatchers.IO).launch {
            _listOfFoodItems.postValue(filterListOfFoodItemsByCategoriesUseCase(category))
        }
    }

    fun orderItem(item: FoodItem) {
        CoroutineScope(Dispatchers.IO).launch {
            orderFoodItemUseCase(item)
        }
    }
}