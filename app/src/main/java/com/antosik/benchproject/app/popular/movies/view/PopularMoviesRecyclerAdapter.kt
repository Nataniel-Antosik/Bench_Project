package com.antosik.benchproject.app.popular.movies.view

import android.view.LayoutInflater
import android.view.ViewGroup
import com.antosik.benchproject.app.common.recyclerView.BaseViewHolder
import com.antosik.benchproject.app.common.recyclerView.RecyclerBaseAdapter
import com.antosik.benchproject.app.popular.movies.entity.Movie
import com.antosik.benchproject.app.popular.movies.viewModel.PopularMoviesViewModel
import com.antosik.benchproject.databinding.PopularMovieRecyclerItemLayoutBinding

class PopularMoviesRecyclerAdapter(private val moviesViewModel: PopularMoviesViewModel) : RecyclerBaseAdapter<Movie>() {

    inner class PopularMoviesHolder(private val binding: PopularMovieRecyclerItemLayoutBinding) : BaseViewHolder<Movie>(binding.root) {
        override fun bind(data: Movie) {
            binding.apply {
                popularMoviesViewModel = moviesViewModel
                movie = data
            }
        }
    }

    override fun setViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<Movie> = PopularMoviesHolder(
        PopularMovieRecyclerItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )
}
