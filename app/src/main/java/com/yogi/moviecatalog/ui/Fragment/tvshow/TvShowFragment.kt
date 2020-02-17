package com.yogi.moviecatalog.ui.Fragment.tvshow


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.yogi.moviecatalog.adapter.TvShowRowAdapter
import com.yogi.moviecatalog.models.TvShow

import com.yogi.moviecatalog.R

/**
 * A simple [Fragment] subclass.
 */
class TvShowFragment : Fragment() {


    private lateinit var tvShowViewModel: TvShowViewModel
    private var tvShows: MutableList<TvShow> = mutableListOf()
    private lateinit var root: View
    private lateinit var progressBar: ProgressBar

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        tvShowViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(TvShowViewModel::class.java)
        root = inflater.inflate(R.layout.fragment_tvshow, container, false)
        initView()
        tvShowViewModel.open()
        tvShowViewModel.getTvShows().observe(this, Observer {
            tvShows.addAll(it)
            tvShowViewModel.close()
        })

        tvShowViewModel.getIsFetching().observe(this, Observer {
            if (it) progressBar.visibility = View.VISIBLE else progressBar.visibility = View.GONE
        })
        setRecyclerList(root)
        return root
    }

    private fun setRecyclerList(root: View) {
        val rvTvShow = root.findViewById<RecyclerView>(R.id.rv_tvshow)
        rvTvShow.layoutManager = LinearLayoutManager(root.context)
        rvTvShow.adapter = TvShowRowAdapter(root.context, tvShows)
    }

    private fun initView() {
        progressBar = root.findViewById(R.id.progress_bar)
    }
}
