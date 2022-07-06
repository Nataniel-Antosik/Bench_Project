package com.example.benchproject.app.movie.details.viewModel

import com.example.benchproject.app.movie.details.entity.Genres
import com.example.benchproject.app.movie.details.entity.MovieDetails
import com.example.benchproject.domain.movie.details.entity.GenresModel
import com.example.benchproject.domain.movie.details.entity.MovieDetailsModel
import com.example.benchproject.domain.movie.details.usecase.GetMovieDetailsUseCase
import com.example.benchproject.test.common.LiveDataTest
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.amshove.kluent.any
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@OptIn(ExperimentalCoroutinesApi::class)
@ExtendWith(LiveDataTest::class)
internal class MovieDetailsViewModelTest {

    private val movieDetailsModel = MovieDetailsModel(
        54431,
        "https://image.tmdb.org/t/p/w500/something1Background.com",
        "Test1",
        listOf(
            GenresModel(1, "name1"),
            GenresModel(2, "name2"),
            GenresModel(3, "name3")
        ),
        "Example description",
        2000000,
        5.4,
        "2022-02-10",
        "https://image.tmdb.org/t/p/w500/something1.com"
    )
    private val movieDetails = MovieDetails(
        54431,
        "https://image.tmdb.org/t/p/w500/something1Background.com",
        "Test1",
        listOf(
            Genres(1, "name1"),
            Genres(2, "name2"),
            Genres(3, "name3")
        ),
        "Example description",
        2000000,
        5.4,
        "2022-02-10",
        "https://image.tmdb.org/t/p/w500/something1.com"
    )
    private val getMovieDetailsUseCase: GetMovieDetailsUseCase = mockk()

    @Test
    fun `when method get data from use case, it should be mapped to the movie details type`() =
        runTest {
            coEvery { getMovieDetailsUseCase(any()) } returns Result.success(movieDetailsModel)
            val tested = MovieDetailsViewModel(getMovieDetailsUseCase)

            tested.getMovieDetails(any())

            tested.responseMovieDetails.value shouldBeEqualTo movieDetails
        }
}
