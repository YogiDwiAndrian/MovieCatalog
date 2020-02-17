package com.yogi.moviecatalog.ui.Fragment.movie


import android.os.Bundle
import android.view.*
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.yogi.moviecatalog.adapter.MovieRowAdapter
import com.yogi.moviecatalog.models.Movie
import com.yogi.moviecatalog.R
import kotlinx.android.synthetic.main.fragment_movies.*


/**
 * A simple [Fragment] subclass.
 */
class MovieFragment : Fragment() {

    private lateinit var movieViewModel: MovieViewModel
    private var movies: MutableList<Movie> = mutableListOf()
    private lateinit var root: View
    private lateinit var progressBar: ProgressBar

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        movieViewModel =
            ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(MovieViewModel::class.java)
        root = inflater.inflate(R.layout.fragment_movies, container, false)
        initView()
        movieViewModel.open()
        movieViewModel.getMovies().observe(this, Observer {
            movies.addAll(it)
            movieViewModel.close()
        })

        movieViewModel.getIsFetching().observe(this, Observer {
            if (it) progressBar.visibility = View.VISIBLE else progressBar.visibility = View.GONE
        })
        setRecyclerList(root)
        return root
    }


    private fun setRecyclerList(root: View) {
        val rvMovies: RecyclerView = root.findViewById(R.id.rv_movie)
        rvMovies.layoutManager = LinearLayoutManager(root.context)
        rvMovies.adapter = MovieRowAdapter(root.context, movies)
    }

    private fun initView() {
        progressBar = root.findViewById(R.id.progress_bar)
    }

}
