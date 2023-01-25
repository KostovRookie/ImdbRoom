package com.example.imdbroom.ui.recyclerViewSetting.favouriteRecyclerView

import android.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.imdbroom.R
import com.example.imdbroom.databinding.RowFavoritesBinding
import com.example.imdbroom.utils.Constants
import com.example.imdbroom.data.model.MovieDataClassDB

class FavoriteViewHolder(private val binding: RowFavoritesBinding) :
    RecyclerView.ViewHolder(binding.root) {

    private var movieTitle = binding.textTitle
    private var movieRate = binding.textRate

    fun bind(movie: MovieDataClassDB, onclick: (Int) -> Unit, onClickDelete: (MovieDataClassDB) -> Unit) {
        movieTitle.text = movie.title
        movieRate.text = String.format("%.1f", movie.voteAverage)

     val requestOption = RequestOptions().placeholder(R.drawable.poster_placeholder)
//            .error(R.drawable.person_placeholder)

        Glide.with(itemView.context)
            .applyDefaultRequestOptions(requestOption)
            .load(Constants.URL.IMAGE_BASE + movie.posterPath)
            .into(binding.imgMoviePoster)


        Glide.with(itemView.context)
            .applyDefaultRequestOptions(requestOption)
            .load(Constants.URL.IMAGE_BASE + movie.backdropPath)
            .into(binding.imgBack)

        binding.imgMoviePoster.setOnClickListener {
            onclick(movie.id)
        }

        binding.imgFavorite.setOnClickListener {
            AlertDialog.Builder(itemView.context)
                .setTitle("Премахввне")
                .setMessage("Да се изтрие ли?")
                .setPositiveButton("Да") { _, _ ->
                    onClickDelete(movie)
                }
                .setNeutralButton("Не", null)
                .show()

        }

    }
}