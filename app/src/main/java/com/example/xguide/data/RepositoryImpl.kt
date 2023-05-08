package com.example.xguide.data

import android.content.Context
import com.example.xguide.data.converters.Mapper
import com.example.xguide.data.database.MainDatabase
import com.example.xguide.domain.PackageEntity
import com.example.xguide.domain.Repository

class RepositoryImpl(private val context: Context) : Repository {

    private val db = MainDatabase.getInstance(context).getDao()
    private val mapper = Mapper()

    override suspend fun addPackage(item: PackageEntity) {
        db.addItem(
            mapper.mapEntityToDbModel(item)
        )
    }

    override suspend fun deletePackage(position: Int) {
        db.deleteItem(position)
    }

    override suspend fun getPackage(position: Int): PackageEntity {
        return mapper.mapDbModelToEntity(
            db.getItem(position)
        )
    }

    override suspend fun getPackagePosition(item: PackageEntity): Int {
        return 1
    }

    override suspend fun addPackageList(entityList: List<PackageEntity>) {
        db.addItemsList(
            mapper.mapEntityListToDbModelList(entityList)
        )
    }
}