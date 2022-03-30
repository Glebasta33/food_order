package com.trusov.collapsingtoolbarviewtest.presentation.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.trusov.collapsingtoolbarviewtest.domain.entity.FoodItem
import com.trusov.collapsingtoolbarviewtest.domain.use_case.GetListOfFoodItemsUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class MenuViewModel @Inject constructor(
    private val getListOfFoodItemsUseCase: GetListOfFoodItemsUseCase
) : ViewModel() {

    private var _listOfFoodItems = MutableLiveData<List<FoodItem>>()
    val listOfFoodItems: LiveData<List<FoodItem>> = _listOfFoodItems

    fun getListOfFoodItems() {
        viewModelScope.launch {
            _listOfFoodItems.postValue(getListOfFoodItemsUseCase())
        }
    }
}