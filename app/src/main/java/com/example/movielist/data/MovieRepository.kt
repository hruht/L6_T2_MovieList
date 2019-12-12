package com.example.movielist.data

import com.example.movielist.model.MovieAll
import retrofit2.Call

class MovieRepository {
    private val moviesApi: MovieApiService = MovieApi.createApi()

    fun getMovies(year: Int): Call<MovieAll> = moviesApi.getMovies(year)
}
