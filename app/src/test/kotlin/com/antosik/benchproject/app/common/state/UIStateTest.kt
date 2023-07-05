package com.antosik.benchproject.app.common.state

import com.antosik.benchproject.app.movie.details.entity.Genres
import com.antosik.benchproject.app.movie.details.entity.MovieDetails
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestFactory

internal class UIStateTest {

    val data = mockk<Any>()
    val movieDetails = MovieDetails(
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

    @TestFactory
    fun `SHOULD isLoading AND isEmpty AND isRefreshed AND isSuccess AND data return value based on UIState`() =
        listOf(
            Param(
                state = UIState.Loading,
                isLoading = true,
                isEmpty = false,
                isRefreshed = false,
                isSuccess = false,
                data = null
            ),
            Param(
                state = UIState.Empty,
                isLoading = false,
                isEmpty = true,
                isRefreshed = true,
                isSuccess = false,
                data = null
            ),
            Param(
                state = UIState.Success(data),
                isLoading = false,
                isEmpty = false,
                isRefreshed = true,
                isSuccess = true,
                data = data
            )
        ).map { param ->
            DynamicTest.dynamicTest(
                "SHOULD isLoading be " + param.isLoading +
                    " AND isEmpty be " + param.isEmpty +
                    " AND isRefreshed be " + param.isRefreshed +
                    " AND isSuccess be " + param.isSuccess +
                    " AND data return value " + param.data +
                    " WHEN UIState is" + param.state
            ) {
                param.state.let { uiState ->
                    uiState.isLoading() shouldBeEqualTo param.isLoading
                    uiState.isEmpty() shouldBeEqualTo param.isEmpty
                    uiState.isRefreshed() shouldBeEqualTo param.isRefreshed
                    uiState.isSuccess() shouldBeEqualTo param.isSuccess
                    uiState.data<Any>() shouldBeEqualTo param.data
                }
            }
        }

    @Test
    fun `SHOULD return data with specific type WHEN, data method was called`() {
        val uiState = UIState.Success(movieDetails)

        val tested = uiState.data<MovieDetails>()

        tested shouldBeEqualTo movieDetails
    }
}

data class Param(
    val state: UIState,
    val isLoading: Boolean,
    val isEmpty: Boolean,
    val isRefreshed: Boolean,
    val isSuccess: Boolean,
    val data: Any?,
)
