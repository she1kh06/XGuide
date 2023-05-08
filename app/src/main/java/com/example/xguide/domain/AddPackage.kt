package com.example.xguide.domain

class AddPackage(private val repository: Repository) {
    suspend fun addPackage(item: PackageEntity) {
        repository.addPackage(item)
    }
}