package com.example.xguide.domain.usecase

import com.example.xguide.domain.Repository

class GetCurrentPositionUseCase(private val repository: Repository) {

    suspend fun getCurrentPosition(): Int {
        return repository.getCurrentPosition()
    }
}