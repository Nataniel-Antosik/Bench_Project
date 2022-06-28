package com.example.benchproject.app.movie.details.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.benchproject.databinding.MovieDetailsFragmentBinding

class MovieDetailsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = MovieDetailsFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }
}
