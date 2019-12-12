package com.example.movielist.main

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.movielist.R
import com.example.movielist.data.MovieApi
import com.example.movielist.model.Movie
import kotlinx.android.synthetic.main.activity_movieinfo.*
import java.lang.Exception

class MovieInfo : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movieinfo)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = ""
        initViews()
    }


    private fun initViews() {
        val bundle = intent.extras
        if (bundle != null) {
            val movie: Movie = bundle.get("Movie") as Movie
            Glide.with(this).load(MovieApi.imgUrl + movie.backdrop).into(iv_backdrop)
            Glide.with(this).load(MovieApi.imgUrl + movie.poster).into(iv_poster)
            tv_title.text = movie.title
            tv_releasedate.text = getString(R.string.releasedate, movie.releasedate)
            tv_rating.text = movie.rating.toString()
            tv_overview.text = movie.overview
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return try {
            finish()
            true
        } catch (e: Exception) {
            false
        }
    }
}
