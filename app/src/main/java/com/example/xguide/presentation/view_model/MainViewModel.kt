package com.example.xguide.presentation.view_model

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.xguide.data.RepositoryImpl
import com.example.xguide.domain.*
import com.example.xguide.domain.usecase.*
import com.example.xguide.presentation.node_navigation.Navigation
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(context: Context) : ViewModel(), Navigation {

    private val repository = RepositoryImpl(context)

    private val addNodeUseCase = AddNodeUseCase(repository)
    private val deleteNodeUseCase = DeleteNodeUseCase(repository)
    private val getNodeUseCase = GetNodeUseCase(repository)
    private val getCurrentPositionUseCase = GetCurrentPositionUseCase(repository)
    private val setCurrentPositionUseCase = SetCurrentPositionUseCase(repository)
    private val getCurrentTreeUseCase = GetCurrentTreeUseCase(repository)
    private val setCurrentTreeUseCase = SetCurrentTreeUseCase(repository)
    private val generateNodeNameUseCase = GenerateNodeNameUseCase(repository)
    private val createTreeUseCase = CreateTreeUseCase(repository)

    private val rootNode = createTreeUseCase()
    private var currentTree = rootNode
    private var isRootNode = true

    private var position: Int = -1

    val nodes = listOf(currentTree)

    private val _ldChildrenTree = MutableLiveData<List<Node>>()
    val ldChildrenTree: LiveData<List<Node>>
        get() = _ldChildrenTree

    private val _ldParentTree = MutableLiveData<List<Node>>()
    val ldParentTree: LiveData<List<Node>>
        get() = _ldParentTree

    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    override fun toChildren(position: Int) {
        if (isRootNode) {
            _ldChildrenTree.value = rootNode.children
            isRootNode = false
        } else {
            val currentItem = currentTree.children[position]
            currentTree = currentItem
            if (currentItem.children.isNotEmpty()) {
                _ldChildrenTree.value = currentTree.children
            } else {
                _ldChildrenTree.value = listOf(currentItem)
            }
            this.position = position
        }
    }

    override fun toParent() {
        _ldParentTree.value = currentTree.children
        currentTree = currentTree.parent!!.children[position]
    }

    fun addNode() {
        coroutineScope.launch {
            val name = generateNodeNameUseCase.generateNodeName()
            addNodeUseCase.addNode(Node(name, currentTree))
        }
    }

    fun deleteNodeUseCase(name: String) {
        coroutineScope.launch {
            deleteNodeUseCase.deleteNode(name)
        }
    }
}