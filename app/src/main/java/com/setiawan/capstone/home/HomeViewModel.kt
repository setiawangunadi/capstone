package com.setiawan.capstone.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.setiawan.capstone.core.domain.usecase.MovieUseCase

class HomeViewModel(movieUseCase: MovieUseCase) : ViewModel() {
    val movie = movieUseCase.getAllMovie().asLiveData()
}