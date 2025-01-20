package com.example.communityapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.communityapp.databinding.CommuntiyTextSampleBinding

class ItemListAdapter : ListAdapter<Item, ItemListAdapter.ItemViewHolder>(ItemDiffCallBack()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding = CommuntiyTextSampleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    inner class ItemViewHolder(private val binding: CommuntiyTextSampleBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Item) {
            with(binding) {
                ivProfileImage.setImageResource(item.profileImage)
                ivHeart.setImageResource(item.heartImage)
                ivAnswer.setImageResource(item.answerImage)
                textView.text = item.idText
                tvHeart.text = item.heartCount
                tvAnswer.text = item.answerCount
            }
        }
    }

    class ItemDiffCallBack : DiffUtil.ItemCallback<Item>() {
        override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
            return oldItem == newItem
        }
    }
}