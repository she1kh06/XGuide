package com.example.xguide.data.database.db_model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.xguide.data.converters.DataConverter

@Entity(tableName = "main_table")
@TypeConverters(value = [DataConverter::class])
data class PackageDbModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val name: String,
    val position: Int
)