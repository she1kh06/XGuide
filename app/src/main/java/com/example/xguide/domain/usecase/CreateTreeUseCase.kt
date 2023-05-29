package com.example.xguide.domain.usecase

import com.example.xguide.domain.Node
import com.example.xguide.domain.Repository

class CreateTreeUseCase(private val repository: Repository) {
    operator fun invoke(): Node {
        return repository.createTree()
    }
}