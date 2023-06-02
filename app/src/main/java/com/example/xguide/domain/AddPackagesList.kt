package com.example.xguide.domain

class AddPackagesList(private val repository: Repository) {
    suspend fun getPackagesList(packagesList: List<PackageEntity>) {
        repository.addPackageList(packagesList)
    }
}