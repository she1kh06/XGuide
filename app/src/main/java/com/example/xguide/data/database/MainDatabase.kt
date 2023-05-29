package com.example.xguide.data.database

import com.example.xguide.data.converters.TreeConverter
import android.content.Context
import androidx.room.*
import com.example.xguide.data.db_model.MainNodeDbModel

@Database(entities = [MainNodeDbModel::class], version = 1, exportSchema = false)
@TypeConverters(TreeConverter::class)
abstract class MainDatabase : RoomDatabase() {

    companion object {
        private var database: MainDatabase? = null
        private const val DB_NAME = "main.db"

        fun getInstance(context: Context): MainDatabase {
            database?.let { return it }

            val instance = Room.databaseBuilder(context, MainDatabase::class.java, DB_NAME).build()
            database = instance
            return instance
        }
    }

    abstract fun getDao(): MainDao
}