package com.example.xguide.presentation.node_navigation

interface Navigation {
    fun toChildren(position: Int)

    fun toParent()
}