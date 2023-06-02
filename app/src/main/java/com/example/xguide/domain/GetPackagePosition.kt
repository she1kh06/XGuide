package com.example.xguide.domain

class GetPackagePosition(private val repository: Repository) {
    suspend fun getPackagePosition(item: PackageEntity): Int = repository.getPackagePosition(item)
}