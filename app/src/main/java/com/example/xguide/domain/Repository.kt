package com.example.xguide.domain

interface Repository {
    suspend fun addNode(item: Node)
    suspend fun deleteNode(name: String)
    suspend fun getNode(name: String): Node
    suspend fun addNodeList(entityList: List<Node>, parent: Node)

    fun generateNodeName(): String
    fun createTree(): Node
}