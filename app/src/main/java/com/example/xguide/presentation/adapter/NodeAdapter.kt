package com.example.xguide.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.webapplication.presentation.adapter.NodeItemDiffCallback
import com.example.xguide.R
import com.example.xguide.domain.Node

class NodeAdapter : ListAdapter<Node, NodeAdapter.MyViewHolder>(NodeItemDiffCallback()) {

    private lateinit var context: Context

    private lateinit var mClickListener: onItemClickListener
    var onShopItemLongClickListener: ((Node) -> Unit)? = null

    interface onItemClickListener {
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(listener: onItemClickListener) {
        mClickListener = listener
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        context = parent.context
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.folder_item, parent, false)
        return MyViewHolder(view, mClickListener)
    }


    override fun getItemCount(): Int {
        return currentList.size;
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = getItem(position)
        with(holder) {
            tvFolderName.text = item.name

            holder.itemView.setOnLongClickListener {
                onShopItemLongClickListener?.invoke(item)
                true
            }

        }
    }

    inner class MyViewHolder(
        itemView: View,
        listener: onItemClickListener
    ) :
        RecyclerView.ViewHolder(itemView) {
        val tvFolderName: TextView = itemView.findViewById(R.id.tv_folder_name)

        init {
            itemView.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }
        }
    }
}