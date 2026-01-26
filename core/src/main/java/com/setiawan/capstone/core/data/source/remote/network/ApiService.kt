package com.setiawan.capstone.core.data.source.remote.network

import com.setiawan.capstone.core.data.source.remote.response.ListMoviesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("/3/movie/now_playing")
    suspend fun getMovies(
        @Query("api_key") apiKey: String
    ): ListMoviesResponse
}