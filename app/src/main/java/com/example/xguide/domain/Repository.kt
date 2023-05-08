package com.example.xguide.domain

interface Repository {
    suspend fun addPackage(item: PackageEntity)
    suspend fun deletePackage(position: Int)
    suspend fun getPackage(position: Int): PackageEntity
    suspend fun getPackagePosition(item: PackageEntity): Int
    suspend fun addPackageList(entityList: List<PackageEntity>)
}