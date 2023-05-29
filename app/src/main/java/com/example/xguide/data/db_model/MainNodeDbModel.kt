package com.example.xguide.data.db_model

import com.example.xguide.data.converters.TreeConverter
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters

@Entity(tableName = "main_table")
@TypeConverters(value = [TreeConverter::class])
data class MainNodeDbModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    var parent: MainNodeDbModel? = null
) {
    var children = mutableListOf<MainNodeDbModel>()
}