package com.example.xguide.domain

import com.example.xguide.data.db_model.NodeDbModel

data class Node(
    val name: String,
    var parent: Node? = null
) {
    val children = mutableListOf<Node>()
}