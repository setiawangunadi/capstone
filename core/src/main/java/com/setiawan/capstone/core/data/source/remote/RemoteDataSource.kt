package com.setiawan.capstone.core.data.source.remote

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.setiawan.capstone.core.data.source.remote.network.ApiResponse
import com.setiawan.capstone.core.data.source.remote.network.ApiService
import com.setiawan.capstone.core.data.source.remote.response.ListMoviesResponse
import com.setiawan.capstone.core.data.source.remote.response.MoviesResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import okhttp3.Dispatcher
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSource(private val apiService: ApiService) {
    companion object {
        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(service: ApiService): RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource(service)
            }
    }

    fun getAllMovie(): Flow<ApiResponse<List<MoviesResponse>>> {
        val apiKey = "2174d146bb9c0eab47529b2e77d6b526"
        val resultData = MutableLiveData<ApiResponse<List<MoviesResponse>>>()

        return flow {
            try {
                val response = apiService.getMovies(apiKey)
                val dataArray = response.results
                if (dataArray?.isNotEmpty() ?: false){
                    emit(ApiResponse.Success(response.results))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e : Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }
}