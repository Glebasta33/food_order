package com.trusov.collapsingtoolbarviewtest.di

import android.app.Application
import com.trusov.collapsingtoolbarviewtest.data.local.database.ShopDao
import com.trusov.collapsingtoolbarviewtest.data.local.database.ShopDatabase
import com.trusov.collapsingtoolbarviewtest.data.local.source.LocalDataSource
import com.trusov.collapsingtoolbarviewtest.data.local.source.LocalDataSourceImpl
import com.trusov.collapsingtoolbarviewtest.data.remote.retrofit.ApiFactory
import com.trusov.collapsingtoolbarviewtest.data.remote.retrofit.ApiService
import com.trusov.collapsingtoolbarviewtest.data.remote.source.RemoteDataSource
import com.trusov.collapsingtoolbarviewtest.data.remote.source.RemoteDataSourceImpl
import com.trusov.collapsingtoolbarviewtest.data.repository.ShopRepositoryImpl
import com.trusov.collapsingtoolbarviewtest.domain.repository.ShopRepository
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface DataModule {
    @Binds
    fun bindShopRepository(impl: ShopRepositoryImpl): ShopRepository

    @Binds
    fun bindRemoteDataSource(impl: RemoteDataSourceImpl): RemoteDataSource

    @Binds
    fun bindLocalDataSource(impl: LocalDataSourceImpl): LocalDataSource


    companion object {

        @ApplicationScope
        @Provides
        fun provideDao(application: Application): ShopDao {
            return ShopDatabase.getInstance(application).shopDao()
        }

        @ApplicationScope
        @Provides
        fun provideApiService(): ApiService {
            return ApiFactory.service
        }
    }
}