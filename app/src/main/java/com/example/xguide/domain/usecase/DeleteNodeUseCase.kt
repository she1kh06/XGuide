package com.example.xguide.domain.usecase

import com.example.xguide.domain.Repository

class DeleteNodeUseCase(private val repository: Repository) {
    suspend fun deleteNode(name: String) {
        repository.deleteNode(name)
    }
}