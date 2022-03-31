package com.trusov.collapsingtoolbarviewtest.di

import androidx.lifecycle.ViewModel
import com.trusov.collapsingtoolbarviewtest.presentation.fragment.cart_fragment.CartViewModel
import com.trusov.collapsingtoolbarviewtest.presentation.fragment.detailed_frament.FoodItemDetailedViewModel
import com.trusov.collapsingtoolbarviewtest.presentation.fragment.menu_fragment.MenuViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {
    @IntoMap
    @ViewModelKey(MenuViewModel::class)
    @Binds
    fun bindsMenuViewModel(impl: MenuViewModel): ViewModel

    @IntoMap
    @ViewModelKey(CartViewModel::class)
    @Binds
    fun bindsCartViewModel(impl: CartViewModel): ViewModel

    @IntoMap
    @ViewModelKey(FoodItemDetailedViewModel::class)
    @Binds
    fun bindsFoodItemDetailedViewModel(impl: FoodItemDetailedViewModel): ViewModel
}