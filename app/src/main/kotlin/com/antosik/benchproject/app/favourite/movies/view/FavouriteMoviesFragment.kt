package com.antosik.benchproject.app.favourite.movies.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.antosik.benchproject.app.common.theme.BenchProjectTheme
import com.antosik.benchproject.app.favourite.movies.viewModel.FavouriteMoviesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavouriteMoviesFragment : Fragment() {

    private val favouriteMoviesViewModel: FavouriteMoviesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ) = ComposeView(requireContext()).apply {
        setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
        setContent {
            BenchProjectTheme {
                val count = favouriteMoviesViewModel.stateFlow.collectAsState(initial = 3)

                // We never re-launched this launch effect
                // it is for collect value from SharedFlow. We can't use collectAsState
                // because that actually make it as a state
                // launchEffect is safe to do some stuff that's happening outside of the actual composition
                LaunchedEffect(key1 = true) {
                    favouriteMoviesViewModel.sharedFlow.collect { number ->
                        // do something with number
                    }
                }

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    Button(
                        onClick = favouriteMoviesViewModel::incrementCounter
                    ) {
                        Text(text = "Counter: ${count.value}")
                    }
                }
            }
        }
    }
}
