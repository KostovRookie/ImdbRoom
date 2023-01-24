package com.example.imdbroom.ui.recyclerViewSetting.favouriteRecyclerView

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.imdbroom.databinding.RowFavoritesBinding
import com.example.imdbroom.data.model.MovieDataClassDB

class FavoriteAdapter(private val onclick: (Int) -> Unit,
                      private val onClickDelete: (MovieDataClassDB) -> Unit)
    : ListAdapter<MovieDataClassDB, FavoriteViewHolder>(
    MovieDBToCallBack()
) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        return FavoriteViewHolder(RowFavoritesBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        val movie = getItem(position)
        holder.bind(movie, onclick, onClickDelete)
    }

    class MovieDBToCallBack: DiffUtil.ItemCallback<MovieDataClassDB>(){
        override fun areItemsTheSame(oldItem: MovieDataClassDB, newItem: MovieDataClassDB): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: MovieDataClassDB, newItem: MovieDataClassDB): Boolean {
            return oldItem.title == newItem.title
        }

    }
}

