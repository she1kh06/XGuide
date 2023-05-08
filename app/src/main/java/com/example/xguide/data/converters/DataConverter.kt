package com.example.xguide.data.converters

import androidx.room.TypeConverter
import com.example.xguide.data.database.db_model.PackageDbModel
import com.google.gson.Gson
import java.io.Serializable


class DataConverter : Serializable {

    @TypeConverter
    fun fromPackageItemsList(listOfItems: List<PackageDbModel>?): String {
        return Gson().toJson(listOfItems)
    }

    @TypeConverter
    fun toPackageItemsList(optionValuesString: String): List<PackageDbModel> {
        val gson = Gson()
        val objects = gson.fromJson(optionValuesString, ArrayList::class.java)
        val webItems = ArrayList<PackageDbModel>()
        for (o in objects) {
            webItems.add(gson.fromJson(o.toString(), PackageDbModel::class.java))
        }
        return webItems
    }
}