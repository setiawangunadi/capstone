package com.setiawan.capstone.core.domain.usecase

import androidx.lifecycle.LiveData
import com.setiawan.capstone.core.data.Resource
import com.setiawan.capstone.core.domain.model.Movie
import com.setiawan.capstone.core.domain.repository.IMovieRepository

class MovieInteractor(private val movieRepository: IMovieRepository) : MovieUseCase {
    override fun getAllMovie() = movieRepository.getAllMovie()

    override fun getFavoriteMovie() = movieRepository.getFavoriteMovie()

    override fun setFavoriteMovie(
        movie: Movie,
        state: Boolean
    ) = movieRepository.setFavoriteMovie(movie, state)

}