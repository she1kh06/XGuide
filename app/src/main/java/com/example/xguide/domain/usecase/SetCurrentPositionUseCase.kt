package com.example.xguide.domain.usecase

import com.example.xguide.domain.Repository

class SetCurrentPositionUseCase(private val repository: Repository) {
    suspend fun setCurrentPosition(position: Int) {
        repository.setCurrentPosition(position)
    }
}