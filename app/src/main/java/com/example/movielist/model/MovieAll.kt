package com.example.movielist.model

import com.google.gson.annotations.SerializedName

data class MovieAll (
    @SerializedName("results") var movies: Array<Movie>
)