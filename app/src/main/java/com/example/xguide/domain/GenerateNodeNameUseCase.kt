package com.example.xguide.domain

class GenerateNodeNameUseCase(private val repository: Repository) {
    operator fun invoke(repository: Repository): String = repository.generateNodeName()
}