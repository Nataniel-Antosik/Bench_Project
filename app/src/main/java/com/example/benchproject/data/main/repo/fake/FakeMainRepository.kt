package com.example.benchproject.data.main.repo.fake

import com.example.benchproject.data.main.api.fake.FakeApi
import com.example.benchproject.domain.main.entity.MovieModel
import com.example.benchproject.domain.main.repo.MainRepository
import javax.inject.Inject

class FakeMainRepository @Inject constructor(private val api: FakeApi) : MainRepository {

    override fun getMovieList() = api.fakeMovieList().map {
        MovieModel(it.name, it.rating)
    }
}
