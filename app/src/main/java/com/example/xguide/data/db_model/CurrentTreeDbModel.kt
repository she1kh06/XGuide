package com.example.xguide.data.db_model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.xguide.data.converters.TreeConverter
import com.example.xguide.data.database.CurrentTreeDatabase

@Entity(tableName = "currentTreeTable")
@TypeConverters(value = [TreeConverter::class])
data class CurrentTreeDbModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    var parent: CurrentTreeDbModel? = null
) {
    var children = mutableListOf<CurrentTreeDbModel>()
}