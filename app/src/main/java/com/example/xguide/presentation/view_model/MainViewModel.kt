package com.example.xguide.presentation.view_model

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.xguide.data.RepositoryImpl
import com.example.xguide.domain.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(context: Context) : ViewModel() {

    private val repository = RepositoryImpl(context)

    private val AddNodeUseCase = AddNodeUseCase(repository)
    private val DeleteNodeUseCase = DeleteNodeUseCase(repository)
    private val GetNodeUseCase = GetNodeUseCase(repository)
    private val GenerateNodeNameUseCase = GenerateNodeNameUseCase(repository)
    private val CreateTreeUseCase = CreateTreeUseCase(repository)

    private var _tree = CreateTreeUseCase()
    val tree: Node
        get() = _tree

    private var list = _tree.children

    private val _ldTree = MutableLiveData<List<Node>>()
    val ldTree: LiveData<List<Node>>
        get() = _ldTree

    init {
        if (ldTree.value == null) {
            _ldTree.value = _tree.children
        }
    }

    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    fun nextLevel(position: Int) {
        _tree = _tree.children[position]
        val childrenNodes = _tree.children
        _ldTree.value = childrenNodes
    }

    fun getChildren(): List<Node> {
        return list
    }

//    fun addNode(name: String, parent: Node) {
//        coroutineScope.launch {
//            AddNodeUseCase.addNode(Node(name, parent))
//        }
//    }
//
//    fun deleteNodeUseCase(name: String) {
//        coroutineScope.launch {
//            DeleteNodeUseCase.deleteNode(name)
//        }
//    }
}