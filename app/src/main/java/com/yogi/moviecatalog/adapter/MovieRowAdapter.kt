package com.yogi.moviecatalog.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.yogi.moviecatalog.models.Movie
import com.yogi.moviecatalog.R
import com.yogi.moviecatalog.ui.DetailActivity

class MovieRowAdapter (private val context: Context, private val movies: List<Movie>) :
    RecyclerView.Adapter<MovieRowAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_row_movies, parent, false))

    override fun getItemCount(): Int = movies.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(movies[position]) {
            val dataIntent = Intent(context, DetailActivity::class.java)
            dataIntent.putExtra(DetailActivity.KEY, DetailActivity.MOVIE_KEY)
            dataIntent.putExtra(DetailActivity.MOVIE_KEY, movies[position])
            context.startActivity(dataIntent)
        }
    }


    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var imgPhoto: ImageView = view.findViewById(R.id.img_movie)
        var tvYear: TextView = view.findViewById(R.id.tv_year)
        var tvTittle: TextView = view.findViewById(R.id.tv_tittle)
        var tvRateBar: RatingBar = view.findViewById(R.id.ratingBar)
        var tvRateNum: TextView = view.findViewById(R.id.tv_rate)
        var tvGenres: TextView = view.findViewById(R.id.tv_genres)

        fun bindItem(movie: Movie, listener: (Movie) -> Unit) {
            Picasso.get().load("https://image.tmdb.org/t/p/w185${movie.posterPath}").into(imgPhoto)
            tvYear.text = movie.releaseDate
            tvTittle.text = movie.title
            tvGenres.text = movie.title
            tvRateBar.rating = movie.voteAverage!!.toFloat() / 2
            tvRateNum.text = movie.voteAverage.toString()

            itemView.setOnClickListener { listener(movie) }
        }

    }
}