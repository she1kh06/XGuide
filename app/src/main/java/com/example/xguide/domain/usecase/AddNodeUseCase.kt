package com.example.xguide.domain.usecase

import com.example.xguide.domain.Node
import com.example.xguide.domain.Repository

class AddNodeUseCase(private val repository: Repository) {
    suspend fun addNode(item: Node) {
        repository.addNode(item)
    }
}