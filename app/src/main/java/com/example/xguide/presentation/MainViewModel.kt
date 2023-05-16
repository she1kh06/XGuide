package com.example.xguide.presentation

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.xguide.data.RepositoryImpl
import com.example.xguide.domain.AddNodeUseCase
import com.example.xguide.domain.DeleteNodeUseCase
import com.example.xguide.domain.GenerateNodeNameUseCase
import com.example.xguide.domain.GetNodeUseCase

class MainViewModel(context: Context) : ViewModel() {

    private val repository = RepositoryImpl(context)

    private val AddNodeUseCase = AddNodeUseCase(repository)
    private val DeleteNodeUseCase = DeleteNodeUseCase(repository)
    private val GetNodeUseCase = GetNodeUseCase(repository)
    private val GenerateNodeNameUseCase = GenerateNodeNameUseCase(repository)
    
}