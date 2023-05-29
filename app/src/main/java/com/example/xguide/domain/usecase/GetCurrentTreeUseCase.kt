package com.example.xguide.domain.usecase

import androidx.lifecycle.LiveData
import com.example.xguide.domain.Node
import com.example.xguide.domain.Repository

class GetCurrentTreeUseCase(private val repository: Repository) {

    suspend fun getCurrentTree(): LiveData<Node> {
        return repository.getCurrentTree()
    }
}