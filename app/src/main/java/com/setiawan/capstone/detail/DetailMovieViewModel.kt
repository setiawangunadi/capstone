package com.setiawan.capstone.detail

import androidx.lifecycle.ViewModel
import com.setiawan.capstone.core.domain.model.Movie
import com.setiawan.capstone.core.domain.usecase.MovieUseCase

class DetailMovieViewModel(private val movieUseCase: MovieUseCase): ViewModel() {
    fun setFavoriteMovie(movie: Movie, newStatus: Boolean) =
        movieUseCase.setFavoriteMovie(movie, newStatus)
}