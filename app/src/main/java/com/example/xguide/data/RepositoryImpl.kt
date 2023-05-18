package com.example.xguide.data

import android.content.Context
import com.example.xguide.data.converters.Mapper
import com.example.xguide.data.database.MainDatabase
import com.example.xguide.data.database.db_model.NodeDbModel
import com.example.xguide.domain.Node
import com.example.xguide.domain.Repository
import kotlinx.coroutines.NonDisposableHandle.parent
import java.security.MessageDigest

class RepositoryImpl(private val context: Context) : Repository {

    private val db = MainDatabase.getInstance(context).getDao()
    private val mapper = Mapper()

    override suspend fun addNode(item: Node) {

        val parent = item.parent ?: Node(generateNodeName())

        db.addItem(
            mapper.mapEntityToDbModel(
                parent.apply {
                    parent.children.add(item)
                }
            )
        )
    }

    override suspend fun deleteNode(name: String) {
        db.deleteItem(name)
    }

    override suspend fun getNode(name: String): Node {
        return mapper.mapDbModelToEntity(
            db.getItem(name)
        )
    }


    override suspend fun addNodeList(entityList: List<Node>, parent: Node) {
        parent.children.addAll(entityList)

        db.addItemsList(
            mapper.mapEntityListToDbModelList(parent.children)
        )
    }

    override fun generateNodeName(): String {
        val input = "node${System.currentTimeMillis()}"
        val md = MessageDigest.getInstance("SHA-256")
        val hashBytes = md.digest(input.toByteArray()).takeLast(20).toByteArray()
        return hashBytes.joinToString("") { "%02x".format(it) }
    }

    override fun createTree(): Node {
        val rootNode = NodeDbModel(name = generateNodeName())
        for (element in 1..5) {
            val child = NodeDbModel(name = generateNodeName(), parent = rootNode)
            rootNode.children.add(child)
        }

        for (child in rootNode.children) {
            for (i in 0..2) {
                val grandChildren = NodeDbModel(name = generateNodeName(), parent = child)
                child.children.add(grandChildren)
            }
        }

        return mapper.mapDbModelToEntity(rootNode)

    }

}