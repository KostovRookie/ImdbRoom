package com.example.imdbroom.data.paging

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import com.example.imdbroom.databinding.LoaderItemBinding

class LoaderAdapter : LoadStateAdapter<ProgressBarHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): ProgressBarHolder {
        return ProgressBarHolder(LoaderItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ProgressBarHolder, loadState: LoadState) {
        holder.bind(loadState)
    }
}