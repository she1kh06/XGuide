package com.example.xguide.data.database

import androidx.room.Insert
import androidx.room.Query
import com.example.xguide.data.db_model.MainNodeDbModel

@androidx.room.Dao
interface MainDao {

    @Insert
    suspend fun addItem(item: MainNodeDbModel)

    @Query("DELETE FROM main_table WHERE name = :name")
    suspend fun deleteItem(name: String)

    @Query("SELECT * FROM main_table WHERE name = :name")
    suspend fun getItem(name: String): MainNodeDbModel

    @Insert
    suspend fun addItemsList(itemsList: List<MainNodeDbModel>)

}