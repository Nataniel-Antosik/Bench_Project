package com.example.benchproject.data.common.binding

import android.net.Uri
import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.benchproject.app.popular.movies.entity.Movie
import com.example.benchproject.app.popular.movies.view.PopularMoviesRecyclerAdapter

@BindingAdapter("imageUrl")
fun ImageView.loadMovieImage(imageUrl: String?) {
    Glide.with(this)
        .load(Uri.parse(imageUrl))
        .into(this)
}

@BindingAdapter("updateAdapterData")
fun RecyclerView.updateAdapter(data: List<Movie>) {
    getRecyclerAdapter(this).uploadMovieData(data)
}

@BindingAdapter("isVisible")
fun View.setVisibility(flag: Boolean) {
    if (flag) this.visibility = View.GONE else this.visibility = View.VISIBLE
}

// TODO create generic solution for this
private fun getRecyclerAdapter(recyclerView: RecyclerView): PopularMoviesRecyclerAdapter =
    recyclerView.adapter as PopularMoviesRecyclerAdapter