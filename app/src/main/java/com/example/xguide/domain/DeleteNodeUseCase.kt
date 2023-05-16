package com.example.xguide.domain

class DeleteNodeUseCase(private val repository: Repository) {
    suspend fun deleteNode(name: String) {
        repository.deleteNode(name)
    }
}