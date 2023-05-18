package com.example.xguide.domain

class CreateTreeUseCase(private val repository: Repository) {
    operator fun invoke(): Node {
        return repository.createTree()
    }
}