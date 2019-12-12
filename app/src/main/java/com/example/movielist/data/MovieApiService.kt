package com.example.movielist.data

import com.example.movielist.BuildConfig
import com.example.movielist.model.MovieAll
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApiService {
    @GET("movie?api_key=${BuildConfig.CONSUMER_KEY}&language=en-US&sort_by=popularity.desc&include_adult=false&include_video=false&page=1")
    fun getMovies(@Query(value = "primary_release_year", encoded = true) year: Int): Call<MovieAll>
}