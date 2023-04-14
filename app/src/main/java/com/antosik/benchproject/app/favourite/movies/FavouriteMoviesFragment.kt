package com.antosik.benchproject.app.favourite.movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.antosik.benchproject.databinding.FavouriteMoviesBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavouriteMoviesFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ) = FavouriteMoviesBinding.inflate(inflater, container, false).root
}
