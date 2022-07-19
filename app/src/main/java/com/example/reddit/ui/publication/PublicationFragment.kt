package com.example.reddit.ui.publication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.reddit.R
import com.example.reddit.databinding.FragmentPublicationBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class PublicationFragment : Fragment() {

    private lateinit var binding: FragmentPublicationBinding
    private lateinit var viewModel: PublicationViewModel
    private lateinit var adapter: PublicationAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_publication, container, false)

        viewModel = ViewModelProvider(this).get(PublicationViewModel::class.java)

        adapter = PublicationAdapter(
            OnPostClickListener {
                if (it != null)
                    findNavController().navigate(
                        PublicationFragmentDirections.actionPublicationFragmentToDetailFragment(it)
                    )
            })

        initViews()
        loadPost()

        return binding.root
    }

    private fun initViews() {
        binding.publicationRecyclerView.layoutManager = LinearLayoutManager(activity)
        binding.publicationRecyclerView.adapter = adapter.withLoadStateFooter(
            footer = com.example.reddit.utils.LoadStateAdapter { adapter.retry() }
        )

        adapter.addLoadStateListener { loadState ->

            binding.barProgress.isVisible = loadState.source.refresh is LoadState.Loading
            binding.retryButton.isVisible = loadState.source.refresh is LoadState.Error

            val error = when {
                loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
                loadState.append is LoadState.Error -> loadState.append as LoadState.Error
                loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
                else -> null
            }

            error?.let {
                Toast.makeText(activity, it.error.message, Toast.LENGTH_SHORT).show()
            }
        }

        binding.retryButton.setOnClickListener {
            adapter.retry()
        }
    }

    private fun loadPost() {
        lifecycleScope.launch {
            viewModel.posts.collectLatest { adapter.submitData(it) }
        }
    }
}