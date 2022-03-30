package com.trusov.collapsingtoolbarviewtest.data.remote.retrofit

import com.trusov.collapsingtoolbarviewtest.data.remote.dto.FoodItemDto
import com.trusov.collapsingtoolbarviewtest.data.remote.dto.ImageDto
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("photos/{id}")
    suspend fun getImageById(
        @Path("id") id: Int
    ): ImageDto

    @GET("comments")
    suspend fun getListOfFoodItems(): List<FoodItemDto>

    @GET("photos")
    suspend fun getListOfImages(): List<ImageDto>
}