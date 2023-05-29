package com.example.xguide.domain.usecase

import com.example.xguide.domain.Node
import com.example.xguide.domain.Repository

class SetCurrentTreeUseCase(private val repository: Repository) {

    suspend fun setCurrentTree(tree: Node) {
        repository.setCurrentTree(tree)
    }
}