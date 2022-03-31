package com.trusov.collapsingtoolbarviewtest.di

import android.app.Application
import com.trusov.collapsingtoolbarviewtest.presentation.activity.MainActivity
import com.trusov.collapsingtoolbarviewtest.presentation.fragment.cart_fragment.CartFragment
import com.trusov.collapsingtoolbarviewtest.presentation.fragment.detailed_frament.FoodItemDetailedFragment
import com.trusov.collapsingtoolbarviewtest.presentation.fragment.menu_fragment.MenuFragment
import dagger.BindsInstance
import dagger.Component

@Component(modules = [DataModule::class, ViewModelModule::class])
interface ApplicationComponent {

    fun inject(instance: MenuFragment)
    fun inject(instance: CartFragment)
    fun inject(instance: MainActivity)
    fun inject(instance: FoodItemDetailedFragment)

    @Component.Factory
    interface ApplicationComponentFactory {
        fun create(
            @BindsInstance
            application: Application
        ): ApplicationComponent
    }
}