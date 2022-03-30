package com.trusov.collapsingtoolbarviewtest

import android.app.Application
import com.trusov.collapsingtoolbarviewtest.di.DaggerApplicationComponent

class App : Application() {
    val component by lazy {
        DaggerApplicationComponent.factory()
            .create(this)
    }
}