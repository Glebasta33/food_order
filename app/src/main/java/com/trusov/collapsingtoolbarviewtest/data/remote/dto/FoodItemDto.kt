package com.trusov.collapsingtoolbarviewtest.data.remote.dto

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class FoodItemDto(
    @SerializedName("body")
    @Expose
    val description: String,
    @SerializedName("id")
    @Expose
    val id: Int,
    @SerializedName("name")
    @Expose
    val title: String,
    @SerializedName("postId")
    @Expose
    val categoryId: Int
)