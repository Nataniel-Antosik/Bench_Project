package com.example.benchproject.domain.main.usecase

import com.example.benchproject.domain.main.repo.MainRepository
import javax.inject.Inject

class GetMovieUseCase @Inject constructor(private val repo: MainRepository) {

    operator fun invoke() = repo.getMovieList()
}
