package com.antosik.benchproject.app.favourite.movies.view

import android.content.res.Configuration
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.antosik.benchproject.R
import com.antosik.benchproject.app.common.compose.loader.BenchLoader
import com.antosik.benchproject.app.common.compose.placeholder.EmptyDatabase
import com.antosik.benchproject.app.common.state.UIState
import com.antosik.benchproject.app.common.state.UIState.Empty
import com.antosik.benchproject.app.common.state.UIState.Loading
import com.antosik.benchproject.app.common.state.UIState.Success
import com.antosik.benchproject.app.favourite.movies.viewModel.FavouriteMoviesViewModel
import com.antosik.benchproject.app.popular.movies.entity.Movie

@Composable
fun FavouriteMoviesScreen(favouriteMoviesViewModel: FavouriteMoviesViewModel) {
    val state by favouriteMoviesViewModel.state.collectAsState(initial = Loading)
    Screen(state = state)
}

@Composable
private fun Screen(state: UIState) {
    val configuration = LocalConfiguration.current
    when (state) {
        is Loading -> {
            BenchLoader(modifier = Modifier.fillMaxSize())
        }

        is Success<*> -> {
            if (configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
                FavouriteMoviesPager(
                    movies = state.data<List<Movie>>()!!,
                    imageHeight = 200,
                    imageWidth = 300,
                    pagerHorizontalPadding = 150,
                )
            } else {
                FavouriteMoviesPager(
                    movies = state.data<List<Movie>>()!!,
                )
            }
        }

        is Empty -> {
            if (configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
                EmptyDatabase(
                    modifier = Modifier.fillMaxSize(),
                    textResourceId = R.string.emptyDatabaseFavouriteMoviesText,
                    imageHeight = 110,
                )
            } else {
                EmptyDatabase(
                    modifier = Modifier.fillMaxSize(),
                    textResourceId = R.string.emptyDatabaseFavouriteMoviesText,
                )
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun FavouriteMoviesPager(
    movies: List<Movie>,
    imageHeight: Int = 400,
    imageWidth: Int = 300,
    pagerHorizontalPadding: Int = 32,
) {
    val pagerState = rememberPagerState(pageCount = {
        movies.size
    })
    HorizontalPager(
        state = pagerState,
        contentPadding = PaddingValues(horizontal = pagerHorizontalPadding.dp),
    ) { page ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 58.dp),
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                AsyncImage(
                    model = movies[page].imageUrl,
                    contentDescription = null,
                    modifier = Modifier
                        .height(imageHeight.dp)
                        .width(imageWidth.dp)
                        .clip(RoundedCornerShape(10.dp)),
                    contentScale = ContentScale.Crop
                )
            }
            Text(
                text = movies[page].name,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp),
                color = MaterialTheme.colors.onPrimary,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FavouriteMoviesLoadingPreview() {
    Screen(Loading)
}

@Preview(showBackground = true)
@Composable
fun FavouriteMoviesSuccessPreview() {
    Screen(
        Success(
            listOf(
                Movie(
                    id = 10001,
                    name = "Test1",
                    rating = 5.4,
                    releaseDate = "2022-06-23",
                    imageUrl = "https://image.tmdb.org/t/p/w500/something1.com",
                    isFavourite = false,
                    colorFavourite = 2131034145,
                )
            )
        )
    )
}

@Preview(showBackground = true)
@Composable
fun FavouriteMoviesEmptyPreview() {
    Screen(Empty)
}
