package com.trusov.collapsingtoolbarviewtest.data.local.database

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.trusov.collapsingtoolbarviewtest.data.local.model.CategoryDbModel
import com.trusov.collapsingtoolbarviewtest.data.local.model.FoodItemDbModel

@Database(
    entities = [FoodItemDbModel::class, CategoryDbModel::class],
    version = 1,
    exportSchema = false
)
abstract class ShopDatabase : RoomDatabase() {

    abstract fun shopDao(): ShopDao

    companion object {
        private var db: ShopDatabase? = null
        private const val DB_NAME = "shop.db"
        private val LOCK = Any()

        fun getInstance(application: Application): ShopDatabase {
            db?.let { return it }
            synchronized(LOCK) {
                db?.let { return it }
                val instance = Room.databaseBuilder(
                    application,
                    ShopDatabase::class.java,
                    DB_NAME
                ).fallbackToDestructiveMigration().build()
                db = instance
                return instance
            }
        }
    }

}