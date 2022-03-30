package com.trusov.collapsingtoolbarviewtest.domain.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class FoodItem(
    val id: Int,
    val title: String,
    val description: String,
    val imageUrl: String,
    val categoryId: Int,
    var isOrdered: Boolean = false
) : Parcelable
