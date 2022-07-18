package com.example.reddit.ui.publication

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.reddit.model.ModelPost
import com.example.reddit.databinding.ItemCardBinding

class PublicationAdapter(private val clickListener: OnPostClickListener) :
    PagingDataAdapter<ModelPost, PublicationAdapter.ViewHolder>(PublicationDiffCallback()){

    class ViewHolder (private val binding:ItemCardBinding): RecyclerView.ViewHolder(binding.root) {

        companion object {
            fun from(parent: ViewGroup) : ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemCardBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }

        fun bind(post: ModelPost, clickListener: OnPostClickListener) {
            binding.post = post
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it, clickListener)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }
}

class PublicationDiffCallback : DiffUtil.ItemCallback<ModelPost>() {

    override fun areItemsTheSame(oldItem: ModelPost, newItem: ModelPost): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: ModelPost, newItem: ModelPost): Boolean {
        return oldItem == newItem
    }
}

class OnPostClickListener(val clickListener: (imageUrl: String?) -> Unit) {
    fun onClick(post: ModelPost) = clickListener(post.bigImageUrl)
}