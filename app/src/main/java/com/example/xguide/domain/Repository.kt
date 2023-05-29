package com.example.xguide.domain

import androidx.lifecycle.LiveData

interface Repository {
    suspend fun addNode(item: Node)

    suspend fun deleteNode(name: String)

    suspend fun getNode(name: String): Node

    suspend fun addNodeList(entityList: List<Node>, parent: Node)

    suspend fun getCurrentPosition(): Int

    suspend fun setCurrentPosition(position: Int)

    suspend fun setCurrentTree(tree: Node)
    suspend fun getCurrentTree(): LiveData<Node>

    fun createTree(): Node
    fun generateNodeName(): String
}