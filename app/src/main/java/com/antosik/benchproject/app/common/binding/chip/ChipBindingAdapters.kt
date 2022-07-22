package com.antosik.benchproject.app.common.binding.chip

import androidx.databinding.BindingAdapter
import com.antosik.benchproject.app.movie.details.entity.Genres
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup

@BindingAdapter("genresChips")
fun ChipGroup.addGenresToChips(genres: List<Genres>?) {
    if (genres == null) return
    genres.forEach {
        Chip(context).apply {
            text = it.name
        }.also {
            addView(it)
        }
    }
}
