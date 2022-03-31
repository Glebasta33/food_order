package com.trusov.collapsingtoolbarviewtest.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "category")
data class CategoryDbModel(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val title: String,
    var isActivated: Boolean
)
