package com.example.xguide.data.database

import com.example.xguide.data.converters.TreeConverter
import android.content.Context
import androidx.room.*
import com.example.xguide.data.db_model.CurrentTreeDbModel

@Database(entities = [CurrentTreeDbModel::class], version = 1, exportSchema = false)
@TypeConverters(TreeConverter::class)
abstract class CurrentTreeDatabase : RoomDatabase() {

    companion object {
        private var database: CurrentTreeDatabase? = null
        private const val DB_NAME = "main.db"

        fun getInstance(context: Context): CurrentTreeDatabase {
            database?.let { return it }

            val instance =
                Room.databaseBuilder(context, CurrentTreeDatabase::class.java, DB_NAME).build()
            database = instance
            return instance
        }
    }

    abstract fun getDao(): CurrentTreeDao
}