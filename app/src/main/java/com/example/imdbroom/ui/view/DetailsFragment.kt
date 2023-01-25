package com.example.imdbroom.ui.view

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.imdbroom.R
import com.example.imdbroom.databinding.FragmentDetailsBinding
import com.example.imdbroom.ui.viewmodel.DetailsViewModel
import com.example.imdbroom.utils.Constants
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailsFragment : Fragment(), View.OnClickListener {

    private lateinit var binding: FragmentDetailsBinding
    private val viewModel: DetailsViewModel by viewModel()
    private val args: DetailsFragmentArgs by navArgs()
    private val progressBar: ProgressBar by lazy {
        binding.progressbar
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        binding = FragmentDetailsBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.fabFavoriteButton.setOnClickListener(this)
        binding.buttonBack.setOnClickListener(this)

        viewModel.progressBar.observe(viewLifecycleOwner) {
            if (it) showProgressBar() else (hideProgressBar())
        }
        observer()
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.fabFavoriteButton -> {
                viewModel.favoriteMovie()
                viewModel.checkFavorite()
            }
            R.id.button_back -> {
                findNavController().navigateUp()
            }
        }
    }


    private fun observer() {
        val id = args.id

        viewModel.getMovieById(id)
        viewModel.movieDetails.observe(viewLifecycleOwner) {
            binding.apply {
                textTitle.text = it.title
                textReleaseDate.text = it.release_date
                textOverview.text = it.overview
                textAverage.text = String.format("%.1f", it.vote_average)

                Glide.with(requireContext())
                    .load(Constants.URL.IMAGE_BASE + it.backdrop_path)
                    .into(imgMovieLargePoster)
            }
            checkFavorite()
        }
        viewModel.errorMessage.observe(viewLifecycleOwner) {
            Toast.makeText(activity, it, Toast.LENGTH_LONG).show()
        }
    }

    private fun checkFavorite() {
        viewModel.favorite.observe(viewLifecycleOwner) {
            binding.apply {
                if (it) {
                    fabFavoriteButton.setColorFilter(Color.RED)
                } else {
                    fabFavoriteButton.setColorFilter(Color.WHITE)
                }
            }
        }
        viewModel.checkFavorite()
    }

    private fun showProgressBar() {
        progressBar.visibility = View.VISIBLE
    }

    private fun hideProgressBar() {
        progressBar.visibility = View.GONE
    }
}