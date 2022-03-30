package com.trusov.collapsingtoolbarviewtest.di

import android.app.Application
import com.trusov.collapsingtoolbarviewtest.presentation.fragment.MenuFragment
import dagger.BindsInstance
import dagger.Component

@Component(modules = [DataModule::class, ViewModelModule::class])
interface ApplicationComponent {

    fun inject(instance: MenuFragment)

    @Component.Factory
    interface ApplicationComponentFactory {
        fun create(
            @BindsInstance
            application: Application
        ): ApplicationComponent
    }
}