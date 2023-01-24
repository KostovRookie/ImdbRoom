package com.example.imdbroom.data.paging

import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import com.example.imdbroom.databinding.LoaderItemBinding

class ProgressBarHolder(binding: LoaderItemBinding) : RecyclerView.ViewHolder(binding.root) {
    val progressBar = binding.progressBar

    fun bind(loadState: LoadState){
        progressBar.isVisible = loadState is LoadState.Loading
    }
}