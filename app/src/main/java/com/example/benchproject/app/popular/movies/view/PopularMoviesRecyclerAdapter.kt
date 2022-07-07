package com.example.benchproject.app.popular.movies.view

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.benchproject.app.common.recyclerView.BaseAdapter
import com.example.benchproject.app.common.recyclerView.BaseViewHolder
import com.example.benchproject.app.popular.movies.entity.Movie
import com.example.benchproject.app.popular.movies.viewModel.PopularMoviesViewModel
import com.example.benchproject.databinding.PopularMovieRecyclerItemLayoutBinding

class PopularMoviesRecyclerAdapter(private val moviesViewModel: PopularMoviesViewModel) : BaseAdapter<Movie>() {

    inner class PopularMoviesHolder(private val binding: PopularMovieRecyclerItemLayoutBinding) : BaseViewHolder<Movie>(binding.root) {
        override fun bind(data: Movie) {
            binding.popularMoviesViewModel = moviesViewModel
            binding.movie = data
        }
    }

    override fun setViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<Movie> {
        return PopularMoviesHolder(PopularMovieRecyclerItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }
}
