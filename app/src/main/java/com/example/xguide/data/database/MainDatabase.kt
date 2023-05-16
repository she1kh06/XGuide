package com.example.xguide.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.xguide.data.database.db_model.NodeDbModel

@Database(entities = [NodeDbModel::class], version = 1, exportSchema = false)
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

    abstract fun getDao(): Dao
}