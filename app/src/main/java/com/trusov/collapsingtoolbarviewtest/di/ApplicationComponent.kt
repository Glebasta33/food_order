package com.trusov.collapsingtoolbarviewtest.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component

@Component
interface ApplicationComponent {

    @Component.Factory
    interface ApplicationComponentFactory {
        fun create(
            @BindsInstance
            application: Application
        ): ApplicationComponent
    }
}