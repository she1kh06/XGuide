package com.example.xguide.domain.usecase

import com.example.xguide.domain.Node
import com.example.xguide.domain.Repository

class GetNodeUseCase(private val repository: Repository) {
    suspend fun getNode(name: String): Node = repository.getNode(name)
}