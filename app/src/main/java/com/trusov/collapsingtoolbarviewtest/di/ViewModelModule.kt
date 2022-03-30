package com.trusov.collapsingtoolbarviewtest.di

import androidx.lifecycle.ViewModel
import com.trusov.collapsingtoolbarviewtest.presentation.view_model.CartViewModel
import com.trusov.collapsingtoolbarviewtest.presentation.view_model.MenuViewModel
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
}