package com.example.xguide.data.database

import androidx.room.Insert
import androidx.room.Query
import com.example.xguide.data.database.db_model.NodeDbModel

@androidx.room.Dao
interface Dao {

    @Insert
    suspend fun addItem(item: NodeDbModel)

    @Query("DELETE FROM main_table WHERE name=:name")
    suspend fun deleteItem(name: String)

    @Query("SELECT * FROM main_table WHERE name=:name")
    suspend fun getItem(name: String): NodeDbModel

    @Insert
    suspend fun addItemsList(itemsList: List<NodeDbModel>)
}