package com.example.xguide.domain.usecase

import com.example.xguide.domain.Repository

class GenerateNodeNameUseCase(private val repository: Repository) {

    fun generateNodeName(): String {
        return repository.generateNodeName()
    }
}