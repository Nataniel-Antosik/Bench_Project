package com.antosik.benchproject.app.common.binding.imageView

import android.widget.ImageView
import androidx.annotation.ColorRes
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("imageUrl")
fun ImageView.loadMovieImage(imageUrl: String?) {
    Glide.with(this)
        .load(imageUrl)
        .into(this)
}

@BindingAdapter("app:tint")
fun ImageView.setImageTint(@ColorRes color: Int) {
    setColorFilter(context.getColor(color))
}
