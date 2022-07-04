package com.example.benchproject.app.common.binding.imageView

import android.net.Uri
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("imageUrl")
fun ImageView.loadMovieImage(imageUrl: String?) {
    Glide.with(this)
        .load(Uri.parse(imageUrl))
        .into(this)
}
