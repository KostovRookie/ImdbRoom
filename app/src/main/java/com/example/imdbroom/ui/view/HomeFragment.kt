package com.example.imdbroom.ui.view

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.imdbroom.R
import com.example.imdbroom.utils.Constants
import com.example.imdbroom.data.paging.LoaderAdapter
import com.example.imdbroom.databinding.FragmentHomeBinding
import com.example.imdbroom.ui.recyclerViewSetting.movieRecyclerView.MovieAdapter
import com.example.imdbroom.ui.viewmodel.MainViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val viewModel: MainViewModel by viewModel()
    private var adapter = MovieAdapter { movieId ->
        onItemClicked(movieId)
    }

    private lateinit var searchView: SearchView

    private var movieFilter = ""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        movieFilter = requireArguments().getString(Constants.BUNDLE.MOVIEFILTER, "popular")

        initSearchBar()
        observe()
    }

    override fun onResume() {
        super.onResume()
        viewModel.listMovie(movieFilter)
    }

    private fun observe() {
        viewModel.movies.observe(viewLifecycleOwner) { listResult ->
            lifecycleScope.launch {
                binding.recyclerView.layoutManager =
                    GridLayoutManager(activity, 2, LinearLayoutManager.VERTICAL, false) // in grid layout i can show more than one
                                                                                                             // item on a single line of RV
                binding.recyclerView.adapter = adapter.withLoadStateHeaderAndFooter(
                    header = LoaderAdapter(),
                    footer = LoaderAdapter()
                )
                listResult.collect {
                    adapter.submitData(it)
                }
            }
        }

        viewModel.search.observe(viewLifecycleOwner) { listResult ->
            lifecycleScope.launch {
                binding.recyclerView.layoutManager =
                    GridLayoutManager(activity, 2, LinearLayoutManager.VERTICAL, false)
                binding.recyclerView.adapter = adapter
                listResult.collect {
                    adapter.submitData(it)
                }
            }
        }

        viewModel.errorMessage.observe(viewLifecycleOwner) {
            Toast.makeText(activity, it, Toast.LENGTH_LONG).show()
        }
    }

    private fun onItemClicked(movieId: Int) {
        val action = HomeFragmentDirections.navDetails(movieId)

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