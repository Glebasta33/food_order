package com.trusov.collapsingtoolbarviewtest.data.remote.retrofit

import com.trusov.collapsingtoolbarviewtest.data.remote.dto.CategoryDto
import com.trusov.collapsingtoolbarviewtest.data.remote.dto.FoodItemDto
import com.trusov.collapsingtoolbarviewtest.data.remote.dto.ImageDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("photos/{id}")
    suspend fun getImageById(
        @Path("id") id: Int
    ): ImageDto

    @GET("comments")
    suspend fun getListOfFoodItems(): List<FoodItemDto>

    @GET("photos")
    suspend fun getListOfImages(): List<ImageDto>

    @GET("albums")
    suspend fun getListOfCategories(): List<CategoryDto>

    @GET("comments")
    suspend fun getListOfFoodsByCategory(
        @Query("postId") categoryId: Int
    ): List<FoodItemDto>
}