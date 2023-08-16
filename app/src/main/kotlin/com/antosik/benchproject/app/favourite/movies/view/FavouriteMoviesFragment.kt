package com.antosik.benchproject.app.favourite.movies.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.antosik.benchproject.app.common.state.UIState.Empty
import com.antosik.benchproject.app.common.state.UIState.Loading
import com.antosik.benchproject.app.common.state.UIState.Success
import com.antosik.benchproject.app.common.theme.BenchProjectTheme
import com.antosik.benchproject.app.favourite.movies.viewModel.FavouriteMoviesViewModel
import com.antosik.benchproject.app.popular.movies.entity.Movie
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
                Column(
                    modifier = Modifier
                        .verticalScroll(rememberScrollState())
                        .padding(bottom = 58.dp),
                ) {
                    val favouriteMoviesState = favouriteMoviesViewModel.favouriteMoviesViewState.collectAsState(initial = Loading)
                    when (favouriteMoviesState.value) {
                        is Loading -> {
                            // TODO
                        }

                        is Success<*> -> {
                            Text(
                                text = favouriteMoviesState.value.data<List<Movie>>().toString(),
                                modifier = Modifier
                                    .fillMaxWidth()
                            )
                        }

                        is Empty -> {
                            // TODO
                        }
                    }
                }
            }
        }
    }
}
