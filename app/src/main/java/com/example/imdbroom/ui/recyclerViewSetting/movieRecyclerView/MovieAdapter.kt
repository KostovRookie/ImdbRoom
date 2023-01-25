package com.example.imdbroom.ui.recyclerViewSetting.movieRecyclerView

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.example.imdbroom.databinding.RowMoviesBinding
import com.example.imdbroom.data.model.MoviesResult

class MovieAdapter(private val onclick: (Int) -> Unit) :
    PagingDataAdapter<MoviesResult, MovieViewHolder>(MoviesResultCallBack()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(
            RowMoviesBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = getItem(position)
        movie?.let {
            holder.bind(it, onclick)
        }
    }


    class MoviesResultCallBack : DiffUtil.ItemCallback<MoviesResult>() {
        override fun areItemsTheSame(oldItem: MoviesResult, newItem: MoviesResult): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: MoviesResult, newItem: MoviesResult): Boolean {
            return oldItem.title == newItem.title
        }

    }
}
