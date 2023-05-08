package com.example.xguide.domain

class DeletePackage(private val repository: Repository) {
    suspend fun deletePackage(position: Int) {
        repository.deletePackage(position)
    }
}