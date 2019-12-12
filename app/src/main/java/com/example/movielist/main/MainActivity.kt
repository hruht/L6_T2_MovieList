package com.example.movielist.main

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.movielist.R
import com.example.movielist.model.Movie
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private val movies = arrayListOf<Movie>()
    private val movieAdapter = MovieAdapter(movies, onClick = {seeMovieInfo(it)})
    private lateinit var viewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()
        initViewModel()
    }

    private fun initViews() {
        rv_movies.layoutManager =
            StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
        rv_movies.adapter = movieAdapter

        btn_submit.setOnClickListener { submit() }
    }

    private fun initViewModel() {
        viewModel = ViewModelProviders.of(this).get(MainActivityViewModel::class.java)
        viewModel.movies.observe(this, Observer {
            movies.clear()
            movies.addAll(it)
            movieAdapter.notifyDataSetChanged()
        })
    }

    private fun submit() {
        if (!ti_year.text.isNullOrEmpty()) {
            viewModel.getMovies(ti_year.text.toString().toInt())

            //for closing the keyboard when submit is pressed
            val imm = applicationContext.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(window.decorView.rootView.windowToken, 0)
        }
    }

    private fun seeMovieInfo(movie: Movie) {
        val intent = Intent(this, MovieInfo::class.java)
        val bundle = Bundle()
        bundle.putParcelable("Movie", movie)
        intent.putExtras(bundle)
        startActivity(intent)
    }
}
