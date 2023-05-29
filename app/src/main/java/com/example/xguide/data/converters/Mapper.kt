package com.example.xguide.data.converters

import com.example.xguide.data.db_model.CurrentTreeDbModel
import com.example.xguide.data.db_model.MainNodeDbModel
import com.example.xguide.domain.Node

class Mapper {
    var parent: Node? = null

    fun mapEntityToDbModel(nodeEntity: Node): MainNodeDbModel {
        val element = MainNodeDbModel(
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

    fun mapDbModelToEntity(dbModel: MainNodeDbModel): Node {
        val mappedChildren = mapDbModelListToEntityList(dbModel.children)
        val mappedNode = Node(dbModel.name, null).apply {
            children.addAll(mappedChildren)
        }
        mappedChildren.forEach { it.parent = mappedNode }
        return mappedNode
    }


    fun mapEntityListToDbModelList(entityList: List<Node>): List<MainNodeDbModel> {
        val dbModelList = arrayListOf<MainNodeDbModel>()
        entityList.map { dbModelList.add(mapEntityToDbModel(it)) }
        return dbModelList
    }

    fun mapDbModelListToEntityList(dbModelList: List<MainNodeDbModel>): List<Node> {
        val entityList = arrayListOf<Node>()
        dbModelList.map { entityList.add(mapDbModelToEntity(it)) }
        return entityList
    }

    fun mapCurrentTreeListToEntityList(currentTreeList: List<CurrentTreeDbModel>): List<Node> {
        val entityList = arrayListOf<Node>()
        currentTreeList.map { entityList.add(mapCurrentTreeToEntity(it)) }
        return entityList
    }

    fun mapEntityListToCurrentTreeList(entityList: List<Node>): List<CurrentTreeDbModel> {
        val currentTreeList = arrayListOf<CurrentTreeDbModel>()
        entityList.map { currentTreeList.add(mapEntityToCurrentTree(it)) }
        return currentTreeList
    }

    fun mapCurrentTreeToEntity(currentTree: CurrentTreeDbModel): Node {
        val mappedChildren = mapCurrentTreeListToEntityList(currentTree.children)
        val mappedNode = Node(currentTree.name, null).apply {
            children.addAll(mappedChildren)
        }
        mappedChildren.forEach { it.parent = mappedNode }
        return mappedNode
    }

    fun mapEntityToCurrentTree(entity: Node): CurrentTreeDbModel {
        val mappedChildren = mapEntityListToCurrentTreeList(entity.children)
        val mappedNode = CurrentTreeDbModel(name = entity.name, parent = null).apply {
            children.addAll(mappedChildren)
        }
        mappedChildren.forEach { it.parent = mappedNode }
        return mappedNode
    }
}