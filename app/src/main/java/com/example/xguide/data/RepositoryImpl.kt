package com.example.xguide.data

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.example.xguide.data.converters.Mapper
import com.example.xguide.data.database.CurrentTreeDatabase
import com.example.xguide.data.database.MainDatabase
import com.example.xguide.data.db_model.MainNodeDbModel
import com.example.xguide.domain.Node
import com.example.xguide.domain.Repository
import java.security.MessageDigest

class RepositoryImpl(private val context: Context) : Repository {

    private val mapper = Mapper()

    private val main_db = MainDatabase.getInstance(context).getDao()
    private val current_tree_db = CurrentTreeDatabase.getInstance(context).getDao()

    override suspend fun addNode(item: Node) {

        val parent = item.parent ?: Node(generateNodeName())

        main_db.addItem(
            mapper.mapEntityToDbModel(
                parent.apply {
                    parent.children.add(item)
                }
            )
        )
    }

    override suspend fun deleteNode(name: String) {
        main_db.deleteItem(name)
    }

    override suspend fun getNode(name: String): Node {
        return mapper.mapDbModelToEntity(
            main_db.getItem(name)
        )
    }


    override suspend fun addNodeList(entityList: List<Node>, parent: Node) {
        parent.children.addAll(entityList)

        main_db.addItemsList(
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

        val rootNode = MainNodeDbModel(name = generateNodeName())
        for (element in 1..3) {
            val child = MainNodeDbModel(name = generateNodeName(), parent = rootNode)
            rootNode.children.add(child)
        }

        val children = rootNode.children

        for (child in children) {
            for (i in 1..4) {
                val grandChildren = MainNodeDbModel(name = generateNodeName(), parent = child)
                child.children.add(grandChildren)
            }
        }

        for (grandChild in children) {
            for (superGrandChild in grandChild.children) {
                for (i in 1..5) {
                    val input = MainNodeDbModel(name = generateNodeName(), parent = grandChild)
                    superGrandChild.children.add(input)
                }
            }
        }

        return mapper.mapDbModelToEntity(rootNode)
    }

    override suspend fun setCurrentTree(tree: Node) {
        current_tree_db.setCurrentItem(
            mapper.mapEntityToCurrentTree(tree)
        )
    }

    override suspend fun getCurrentTree() = MediatorLiveData<Node>().apply {
        addSource(current_tree_db.getCurrentItem()) {
            value = mapper.mapCurrentTreeToEntity(it)
        }
    }
}