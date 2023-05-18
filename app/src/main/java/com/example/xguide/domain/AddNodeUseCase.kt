package com.example.xguide.domain

import kotlinx.coroutines.NonDisposableHandle.parent

class AddNodeUseCase(private val repository: Repository) {
    suspend fun addNode(item: Node) {
        repository.addNode(item)
    }
}