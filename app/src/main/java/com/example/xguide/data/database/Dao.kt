package com.example.xguide.data.database

import androidx.room.Insert
import androidx.room.Query
import com.example.xguide.data.database.db_model.PackageDbModel

@androidx.room.Dao
interface Dao {

    @Insert
    suspend fun addItem(item: PackageDbModel)

    @Query("DELETE * FROM main_table WHERE position = :position ")
    suspend fun deleteItem(position: Int)

    @Query("GET * FROM main_table WHERE position = :position")
    suspend fun getItem(position: Int): PackageDbModel

    @Insert
    suspend fun addItemsList(itemsList: List<PackageDbModel>)
}