package com.yogi.moviecatalog.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.yogi.moviecatalog.models.TvShow
import com.yogi.moviecatalog.R
import com.yogi.moviecatalog.ui.DetailActivity
import kotlinx.android.synthetic.main.item_row_tvshow.view.*

class TvShowRowAdapter(private val context: Context, private val tvShows: List<TvShow>) :
    RecyclerView.Adapter<TvShowRowAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_row_tvshow, parent, false))

    override fun getItemCount(): Int = tvShows.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(tvShows[position]) {
            val dataIntent = Intent(context, DetailActivity::class.java)
            dataIntent.putExtra(DetailActivity.KEY, DetailActivity.TV_SHOW_KEY)
            dataIntent.putExtra(DetailActivity.TV_SHOW_KEY, tvShows[position])
            context.startActivity(dataIntent)
        }
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var imgPhoto: ImageView = view.img_tvshow
        var tvYear: TextView = view.tv_year_tvshow
        var tvTittle: TextView = view.tv_tittle_tvshow
        var tvRateBar: RatingBar = view.ratingBar_tvshow
        var tvRateNum: TextView = view.tv_rate_tvshow
        var tvGenres: TextView = view.tv_genres_tvshow

        fun bindItem(tvShow: TvShow, listener: (TvShow) -> Unit) {
            Picasso.get().load("https://image.tmdb.org/t/p/w185${tvShow.posterPath}")
                .into(imgPhoto)
            tvYear.text = tvShow.firstAirDate
            tvTittle.text = tvShow.name
            tvGenres.text = tvShow.name
            tvRateBar.rating = tvShow.voteAverage!!.toFloat() / 2
            tvRateNum.text = tvShow.voteAverage.toString()

            itemView.setOnClickListener { listener(tvShow) }
        }
    }
}