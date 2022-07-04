package com.example.benchproject.app.common.binding.recyclerView

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.benchproject.app.popular.movies.entity.Movie
import com.example.benchproject.app.popular.movies.view.PopularMoviesRecyclerAdapter

@BindingAdapter("updateAdapterData")
fun RecyclerView.updateAdapter(data: List<Movie>) {
    getRecyclerAdapter(this).uploadMovieData(data)
}

// TODO create generic solution for this
private fun getRecyclerAdapter(recyclerView: RecyclerView): PopularMoviesRecyclerAdapter =
    recyclerView.adapter as PopularMoviesRecyclerAdapter
