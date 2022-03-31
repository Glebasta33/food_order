package com.trusov.collapsingtoolbarviewtest.presentation.fragment.cart_fragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.trusov.collapsingtoolbarviewtest.domain.entity.FoodItem
import com.trusov.collapsingtoolbarviewtest.domain.use_case.GetListOfOrderedFoodItemsUseCase
import com.trusov.collapsingtoolbarviewtest.domain.use_case.OrderFoodItemUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class CartViewModel @Inject constructor(
    private val getListOfOrderedFoodItemsUseCase: GetListOfOrderedFoodItemsUseCase,
    private val orderFoodItemUseCase: OrderFoodItemUseCase
) : ViewModel() {

    private var _listOfFoodItems = MutableLiveData<List<FoodItem>>()
    val listOfFoodItems: LiveData<List<FoodItem>> = _listOfFoodItems

    fun getListOfOrderedFoodItems() {
        CoroutineScope(Dispatchers.IO).launch {
            _listOfFoodItems.postValue(getListOfOrderedFoodItemsUseCase())
        }

    }

    fun orderItem(item: FoodItem) {
        CoroutineScope(Dispatchers.IO).launch {
            orderFoodItemUseCase(item)
        }
        getListOfOrderedFoodItems()
    }
}