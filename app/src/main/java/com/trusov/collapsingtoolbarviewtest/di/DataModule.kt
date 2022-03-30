package com.trusov.collapsingtoolbarviewtest.di

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

    companion object {
        @Provides
        fun provideApiService(): ApiService {
            return ApiFactory.service
        }
    }
}