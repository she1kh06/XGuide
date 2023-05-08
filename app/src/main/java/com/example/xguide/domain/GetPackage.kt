package com.example.xguide.domain

class GetPackage(private val repository: Repository) {
    suspend fun getPackage(position: Int): PackageEntity = repository.getPackage(position)
}