package com.example.xguide.data.converters

import androidx.room.TypeConverter
import com.example.xguide.data.db_model.NodeDbModel
import com.google.gson.Gson
import java.io.Serializable


class DataConverter : Serializable {

    @TypeConverter
    fun fromNodeItem(listOfItems: NodeDbModel?): String {
        return Gson().toJson(listOfItems)
    }

    @TypeConverter
    fun toNodeItem(optionValuesString: String): NodeDbModel {
        val gson = Gson()
        return gson.fromJson(optionValuesString, NodeDbModel::class.java)
    }

    @TypeConverter
    fun fromNodeItemsList(listOfItems: List<NodeDbModel>?): String {
        return Gson().toJson(listOfItems)
    }

    @TypeConverter
    fun toNodeItemsList(optionValuesString: String): List<NodeDbModel> {
        val gson = Gson()
        val objects = gson.fromJson(optionValuesString, ArrayList::class.java)
        val nodeItems = ArrayList<NodeDbModel>()
        for (o in objects) {
            nodeItems.add(gson.fromJson(o.toString(), NodeDbModel::class.java))
        }
        return nodeItems
    }
}