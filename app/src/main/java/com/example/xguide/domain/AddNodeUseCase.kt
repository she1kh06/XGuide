package com.example.xguide.domain

class AddNodeUseCase(private val repository: Repository) {
    suspend fun addNode(item: Node, parent: Node) {
        repository.addNode(item, parent)
    }
}