package com.example.xguide.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.xguide.R
import com.example.xguide.domain.Node

class NodeAdapter : ListAdapter<Node, NodeAdapter.MyViewHolder>(NodeItemDiffCallback()) {

    private lateinit var context: Context

    private lateinit var mClickListener: OnItemClickListener
    private lateinit var mLongClickListener: OnItemLongClickListener

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    interface OnItemLongClickListener {
        fun onItemLongClick(position: Int)
    }


    fun setOnItemClickListener(listener: OnItemClickListener) {
        mClickListener = listener
    }

    fun setOnItemLongClickListener(listener: OnItemLongClickListener) {
        mLongClickListener = listener
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

            itemView.setOnLongClickListener {
                mLongClickListener.onItemLongClick(adapterPosition)
                true
            }
        }
    }

    inner class MyViewHolder(
        itemView: View,
        listener: OnItemClickListener
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