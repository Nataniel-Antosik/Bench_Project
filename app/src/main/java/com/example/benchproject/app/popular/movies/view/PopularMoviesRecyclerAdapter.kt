package com.example.benchproject.app.popular.movies.view

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.benchproject.app.common.binding.imageView.loadMovieImage
import com.example.benchproject.app.popular.movies.entity.Movie
import com.example.benchproject.app.popular.movies.viewModel.PopularMoviesViewModel
import com.example.benchproject.databinding.PopularMovieRecyclerItemLayoutBinding

class PopularMoviesRecyclerAdapter(private val moviesViewModel: PopularMoviesViewModel) : RecyclerView.Adapter<PopularMoviesRecyclerAdapter.PopularMoviesHolder>() {

    private var movies = emptyList<Movie>()

    inner class PopularMoviesHolder(private val binding: PopularMovieRecyclerItemLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindItem(movie: Movie) {
            binding.movieNameTextView.text = movie.name
            binding.movieReleaseDateTextView.text = movie.releaseDate
            binding.movieRatingTextView.text = movie.rating.toString()
            binding.moviePosterImageView.loadMovieImage(movie.imageUrl)
            binding.popularMoviesViewModel = moviesViewModel
            binding.movie = movie
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularMoviesRecyclerAdapter.PopularMoviesHolder =
        PopularMoviesHolder(PopularMovieRecyclerItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: PopularMoviesHolder, position: Int) {
        val movie = movies[position]
        holder.bindItem(movie)
    }

    override fun getItemCount() = movies.size

    @SuppressLint("NotifyDataSetChanged")
    fun uploadMovieData(newMovies: List<Movie>) {
        movies = newMovies
        notifyDataSetChanged()
    }
}
