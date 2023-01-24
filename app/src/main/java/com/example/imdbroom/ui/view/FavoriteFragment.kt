package com.example.imdbroom.ui.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.imdbroom.R
import com.example.imdbroom.data.model.MovieDataClassDB
import com.example.imdbroom.databinding.FragmentFavoriteBinding
import com.example.imdbroom.ui.recyclerViewSetting.favouriteRecyclerView.FavoriteAdapter
import com.example.imdbroom.ui.viewmodel.FavoritesViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavoriteFragment : Fragment(), View.OnClickListener {

    private lateinit var binding: FragmentFavoriteBinding
    private val viewModel: FavoritesViewModel by viewModel()
    private var adapter = FavoriteAdapter({ movieId ->
        onItemClicked(movieId)
    }, { movie ->
        onItemDelete(movie)

    })


    private lateinit var searchView: SearchView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoriteBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initSearchBar()

        viewModel.listFavorites()
    }

    override fun onResume() {
        super.onResume()
        viewModel.listFavorites()

        viewModel.movies.observe(viewLifecycleOwner) {
            binding.recyclerView.layoutManager =
                LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
            binding.recyclerView.adapter = adapter
            adapter.submitList(it)
        }

    }

    override fun onClick(v: View) {
        findNavController().navigateUp()
    }

    private fun onItemDelete(movie: MovieDataClassDB) {
        viewModel.deleteFavorite(movie)
        onResume()
    }

    private fun onItemClicked(movieId: Int) {
        val action = FavoriteFragmentDirections.navDetails(movieId)

        findNavController().navigate(action)
    }

    private fun initSearchBar() {
        with(requireActivity().findViewById<Toolbar>(R.id.toolbar)) {
            this.menu.clear()
            this.inflateMenu(R.menu.search_menu)

            val searchItem = menu.findItem(R.id.menu_search)
            searchView = searchItem.actionView as SearchView

            searchView.isIconified = false

            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    val searchString = searchView.query.toString()
                    viewModel.searchPostsTitleContains(searchString)
                    searchView.clearFocus()
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    newText?.let {

                        viewModel.searchPostsTitleContains(it)
                    }
                    return true
                }
            })
        }
    }
}