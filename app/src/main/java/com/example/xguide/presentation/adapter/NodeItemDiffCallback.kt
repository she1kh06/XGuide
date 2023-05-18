package com.example.webapplication.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.xguide.domain.Node

class NodeItemDiffCallback : DiffUtil.ItemCallback<Node>() {

    override fun areItemsTheSame(oldItem: Node, newItem: Node): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: Node, newItem: Node): Boolean {
        return oldItem == newItem
    }

}
