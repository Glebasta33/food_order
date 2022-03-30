package com.trusov.collapsingtoolbarviewtest.data.remote.dto

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ImageDto(
    @SerializedName("albumId")
    @Expose
    val categoryId: Int,
    @SerializedName("id")
    @Expose
    val id: Int,
    @SerializedName("thumbnailUrl")
    @Expose
    val smallImageUrl: String,
    @SerializedName("title")
    @Expose
    val title: String,
    @SerializedName("url")
    @Expose
    val bigImageUrl: String
)