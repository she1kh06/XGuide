package com.example.xguide.data.converters

import com.example.xguide.data.database.db_model.PackageDbModel
import com.example.xguide.domain.PackageEntity

class Mapper {
    fun mapEntityToDbModel(packageEntity: PackageEntity): PackageDbModel {
        return PackageDbModel(
            name = packageEntity.name,
            position = packageEntity.position
        )
    }

    fun mapDbModelToEntity(dbModel: PackageDbModel): PackageEntity {
        return PackageEntity(
            name = dbModel.name,
            position = dbModel.position
        )
    }

    fun mapEntityListToDbModelList(entityList: List<PackageEntity>): List<PackageDbModel> {
        val dbModelList = arrayListOf<PackageDbModel>()
        entityList.map { dbModelList.add(mapEntityToDbModel(it)) }
        return dbModelList
    }
}