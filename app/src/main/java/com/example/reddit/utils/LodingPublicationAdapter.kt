package com.example.reddit.utils

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.reddit.R
import com.example.reddit.databinding.LoadingPublicationBinding

class LoadStateAdapter(private val retry: () -> Unit) : LoadStateAdapter<LoadingViewHolder>() {

    override fun onBindViewHolder(holder: LoadingViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoadingViewHolder {
        return LoadingViewHolder.create(parent, retry)
    }
}

class LoadingViewHolder(
    private val binding: LoadingPublicationBinding,
    retry: () -> Unit) : RecyclerView.ViewHolder(binding.root) {

    init {
        binding.buttonRetry.setOnClickListener { retry.invoke() }
    }

    fun bind(loadState: LoadState) {
        if (loadState is LoadState.Error) {
            binding.errorText.isVisible = loadState is LoadState.Loading
        }
        binding.progressBar.isVisible = loadState is LoadState.Loading
        binding.buttonRetry.isVisible = loadState !is LoadState.Loading
    }

    companion object {
        fun create(parent: ViewGroup, retry: () -> Unit): LoadingViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.loading_publication, parent, false)
            val binding = LoadingPublicationBinding.bind(view)
            return LoadingViewHolder(binding, retry)
        }
    }
}