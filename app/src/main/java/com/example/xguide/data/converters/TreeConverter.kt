package com.example.xguide.data.converters

import androidx.room.TypeConverter
import com.example.xguide.data.db_model.MainNodeDbModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class TreeConverter {
    private val gson = Gson()

    @TypeConverter
    fun fromTree(tree: MainNodeDbModel?): String? {
        return gson.toJson(tree)
    }

    @TypeConverter
    fun toTree(treeJson: String?): MainNodeDbModel? {
        if (treeJson.isNullOrEmpty()) {
            return null
        }

        val type = object : TypeToken<MainNodeDbModel>() {}.type
        return gson.fromJson(treeJson, type)
    }

    @TypeConverter
    fun fromNodeList(nodeList: List<MainNodeDbModel>?): String? {
        return gson.toJson(nodeList)
    }

    @TypeConverter
    fun toNodeList(nodeListJson: String?): List<MainNodeDbModel>? {
        if (nodeListJson.isNullOrEmpty()) {
            return null
        }

        val type = object : TypeToken<List<MainNodeDbModel>>() {}.type
        return gson.fromJson(nodeListJson, type)
    }
}
