package com.example.benchproject.app.common.binding.imageView

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("imageUrl")
fun ImageView.loadMovieImage(imageUrl: String?) {
    Glide.with(this)
        .load(imageUrl)
        .into(this)
}
