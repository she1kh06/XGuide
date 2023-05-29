package com.example.xguide.domain

data class Node(
    val name: String,
    var parent: Node? = null
) {
    val children = mutableListOf<Node>()
}