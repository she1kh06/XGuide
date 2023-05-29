package com.example.xguide.domain.usecase

import com.example.xguide.domain.Node
import com.example.xguide.domain.Repository

class AddNodeListUseCase(private val repository: Repository) {
    suspend fun addNodeList(packagesList: List<Node>, parent: Node) {
        repository.addNodeList(packagesList, parent)
    }
}