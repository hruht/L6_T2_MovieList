package com.example.movielist.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.movielist.data.MovieRepository
import com.example.movielist.model.Movie
import com.example.movielist.model.MovieAll
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivityViewModel(application: Application) : AndroidViewModel(application) {

    private val movieRepository = MovieRepository()
    val movies = MutableLiveData<Array<Movie>>()
    val error = MutableLiveData<String>()
    var isLoading = MutableLiveData<Boolean>(false)

    fun getMovies(releaseYear: Int) {
        isLoading.value = true
        movieRepository.getMovies(releaseYear)
            .enqueue(object : Callback<MovieAll> {
                override fun onResponse(
                    call: Call<MovieAll>,
                    response: Response<MovieAll>
                ) {
                    isLoading.value = false
                    if (response.isSuccessful) movies.value = response.body()?.movies
                    else error.value = "An error occurred: ${response.errorBody().toString()}"
                }

                override fun onFailure(call: Call<MovieAll>, t: Throwable) {
                    error.value = t.message
                    isLoading.value = false
                }
            })
    }
}