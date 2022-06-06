package com.example.benchproject.data.main.api.fake

import com.example.benchproject.data.main.entity.MovieRemote
import javax.inject.Inject

class FakeApi @Inject constructor() {
    fun fakeMovieList() = listOf(
        MovieRemote("Paw Patrol: The Movie", 6.4),
        MovieRemote("The Bad Guys", 2.3),
        MovieRemote("Thinking Of Him", 5.3),
        MovieRemote("Fantastic Beasts The Secrets Of Dumbledore", 1.5)
    )
}
