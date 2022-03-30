package com.trusov.collapsingtoolbarviewtest.presentation.view_model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.trusov.collapsingtoolbarviewtest.domain.entity.FoodItem
import com.trusov.collapsingtoolbarviewtest.domain.use_case.GetListOfOrderedFoodItemsUseCase
import com.trusov.collapsingtoolbarviewtest.domain.use_case.OrderFoodItemUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class CartViewModel @Inject constructor(
    private val getListOfOrderedFoodItemsUseCase: GetListOfOrderedFoodItemsUseCase,
    private val orderFoodItemUseCase: OrderFoodItemUseCase
) : ViewModel() {

    private var _listOfFoodItems = MutableLiveData<List<FoodItem>>()
    val listOfFoodItems: LiveData<List<FoodItem>> = _listOfFoodItems

    fun getListOfOrderedFoodItems() {
        Log.d(
            "OrderingTest",
            "getListOfOrderedFoodItems ${getListOfOrderedFoodItemsUseCase().size}"
        )
        _listOfFoodItems.postValue(getListOfOrderedFoodItemsUseCase())

    }

    fun orderItem(item: FoodItem) {
        orderFoodItemUseCase(item)
        getListOfOrderedFoodItems()
        Log.d("OrderingTest", "orderItem")
    }
}