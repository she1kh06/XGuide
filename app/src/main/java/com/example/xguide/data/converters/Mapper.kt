package com.example.xguide.data.converters

import com.example.xguide.data.database.db_model.NodeDbModel
import com.example.xguide.domain.Node

class Mapper {
    fun mapEntityToDbModel(nodeEntity: Node): NodeDbModel {
        return NodeDbModel(
            name = nodeEntity.name,
            parent = nodeEntity.parent?.let { mapEntityToDbModel(it) }
        )
    }

    fun mapDbModelToEntity(dbModel: NodeDbModel): Node {
        return Node(
            name = dbModel.name,
            parent = dbModel.parent?.let { mapDbModelToEntity(it) }
        )
    }

    fun mapEntityListToDbModelList(entityList: List<Node>): List<NodeDbModel> {
        val dbModelList = arrayListOf<NodeDbModel>()
        entityList.map { dbModelList.add(mapEntityToDbModel(it)) }
        return dbModelList
    }
}