package com.example.xguide.presentation

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.xguide.data.RepositoryImpl
import com.example.xguide.domain.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.newCoroutineContext

class MainViewModel(context: Context) : ViewModel() {

    private val repository = RepositoryImpl(context)

    private val AddNodeUseCase = AddNodeUseCase(repository)
    private val DeleteNodeUseCase = DeleteNodeUseCase(repository)
    private val GetNodeUseCase = GetNodeUseCase(repository)
    private val GenerateNodeNameUseCase = GenerateNodeNameUseCase(repository)
    private val CreateTreeUseCase = CreateTreeUseCase(repository)

    val rootNode = CreateTreeUseCase()

    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    fun nextLevel(node: Node): List<Node> {
        return node.children
    }

    fun addNode(name: String, parent: Node) {
        coroutineScope.launch {
            AddNodeUseCase.addNode(Node(name, parent))
        }
    }

    fun deleteNodeUseCase(name: String) {
        coroutineScope.launch {
            DeleteNodeUseCase.deleteNode(name)
        }
    }
}