package com.example.xguide.data.converters

import com.example.xguide.data.db_model.NodeDbModel
import com.example.xguide.domain.Node
import kotlinx.coroutines.NonCancellable.children

class Mapper {
    fun mapEntityToDbModel(nodeEntity: Node): NodeDbModel {
        val element = NodeDbModel(
            name = nodeEntity.name,
            parent = nodeEntity.parent?.let {
                mapEntityToDbModel(it)
            }
        ).apply {
            children.addAll(
                mapEntityListToDbModelList(nodeEntity.children)
            )
        }
        return element
    }

    fun mapDbModelToEntity(dbModel: NodeDbModel): Node {
        val result = Node(
            name = dbModel.name,
            parent = if (dbModel.parent != null) {
                mapDbModelToEntity(dbModel.parent!!)
            } else {
                null
            }
        ).apply {
            children.addAll(
                mapDbModelListToEntityList(
                    dbModel.children
                )
            )
        }
        return result
    }

    fun mapEntityListToDbModelList(entityList: List<Node>): List<NodeDbModel> {
        val dbModelList = arrayListOf<NodeDbModel>()
        entityList.map { dbModelList.add(mapEntityToDbModel(it)) }
        return dbModelList
    }

    fun mapDbModelListToEntityList(dbModelList: List<NodeDbModel>): List<Node> {
        val entityList = arrayListOf<Node>()
        dbModelList.map { entityList.add(mapDbModelToEntity(it)) }
        return entityList
    }
}