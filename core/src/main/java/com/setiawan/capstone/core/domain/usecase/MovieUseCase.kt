package com.setiawan.capstone.core.domain.usecase

import androidx.lifecycle.LiveData
import com.setiawan.capstone.core.data.Resource
import com.setiawan.capstone.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieUseCase {
    fun getAllMovie(): Flow<Resource<List<Movie>>>
    fun getFavoriteMovie(): Flow<List<Movie>>
    fun setFavoriteMovie(movie: Movie, state: Boolean)
}