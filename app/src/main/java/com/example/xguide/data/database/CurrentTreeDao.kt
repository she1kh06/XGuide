package com.example.xguide.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.example.xguide.data.db_model.CurrentTreeDbModel
@Dao
interface CurrentTreeDao {

    @Upsert
    suspend fun setCurrentItem(item: CurrentTreeDbModel)

    @Query("SELECT * FROM currentTreeTable LIMIT 1")
    suspend fun getCurrentItem(): LiveData<CurrentTreeDbModel>
}