package com.trusov.collapsingtoolbarviewtest.presentation.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.trusov.collapsingtoolbarviewtest.domain.entity.FoodItem
import com.trusov.collapsingtoolbarviewtest.domain.use_case.GetFoodItemUseCase
import com.trusov.collapsingtoolbarviewtest.domain.use_case.OrderFoodItemUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class FoodItemDetailedViewModel @Inject constructor(
    private val getFoodItemUseCase: GetFoodItemUseCase,
    private val orderFoodItemUseCase: OrderFoodItemUseCase
) : ViewModel() {

    private var _item = MutableLiveData<FoodItem>()
    val item: LiveData<FoodItem> = _item

    fun getItem(id: Int) {
        viewModelScope.launch {
            _item.postValue(getFoodItemUseCase(id))
        }
    }

    fun orderItem(item: FoodItem) {
        orderFoodItemUseCase(item)
        getItem(item.id)
    }
}