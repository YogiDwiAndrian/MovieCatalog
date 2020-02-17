package com.yogi.moviecatalog.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.yogi.moviecatalog.R

class DetailActivity : AppCompatActivity() {

    companion object {
        const val KEY = "ScR850plM5"
        const val MOVIE_KEY = "Ab89KmLb43"
        const val TV_SHOW_KEY = "0bmLv85431"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

//        val movies = intent.getParcelableExtra(EXTRA_MOVIES) as Movie
//
//        //change poster
//        val imgPoster: ImageView = img_detail_movie
//        val photo = movies.photo
//        Glide.with(imgPoster)
//            .load(photo)
//            .apply(RequestOptions())
//            .into(imgPoster)
//
//        //change tittle
//        val tvTittle: TextView = tv_detail_tittle
//        val tittle = movies.tittle
//        tvTittle.text = tittle
//
//        //change year
//        val tvyear: TextView = tv_detail_year
//        val year = movies.year.toString()
//        tvyear.text = year
//
//        //change ratingBar
//        val tvRateBar: RatingBar = ratingBar_detail
//        val rateBar = movies.rating
//        tvRateBar.rating = rateBar / 2
//
//        //change rateNum
//        val tvRateNum: TextView = tv_detail_rate
//        val rateNum = movies.rating.toString()
//        tvRateNum.text = rateNum
//
//        //change overview
//        val tvOverview: TextView = tv_detail_overview
//        val overview = movies.overview
//        tvOverview.text = overview
//
//        //change genres
//        val tvGenres: TextView = tv_detail_genres
//        val genres = movies.genre
//        tvGenres.text = genres
//
//        //change preview
//        val vidPreview: WebView = webview
//        val preview = movies.preview
//        vidPreview.loadUrl(preview)
//
//        rvMovieCol = rv_movie_col
//        rvMovieCol.setHasFixedSize(true)
//
//        list.addAll(MovieData.listDataMovies)
//        showRecyclerCardView()
    }

//     private fun showRecyclerCardView() {
//        rvMovieCol.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
//        val cardViewColAdapter = MovieColAdapter(list)
//        rvMovieCol.adapter = cardViewColAdapter
//
//
//    }


}