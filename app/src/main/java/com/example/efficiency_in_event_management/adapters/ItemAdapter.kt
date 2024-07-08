package com.example.efficiency_in_event_management.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.efficiency_in_event_management.databinding.ActivityEditItemBinding

class ItemAdapter(
    private val itemList: List<String>,
    private val onItemClick: (String) -> Unit
) : RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding = ActivityEditItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = itemList[position]
        holder.bind(item)
        holder.itemView.setOnClickListener { onItemClick(item) }
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    inner class ItemViewHolder(private val binding: ActivityEditItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: String) {
        }
    }
}

