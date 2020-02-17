package com.yogi.moviecatalog.ui.Fragment.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.yogi.moviecatalog.BuildConfig
import com.yogi.moviecatalog.R
import com.yogi.moviecatalog.api.ApiRepository
import com.yogi.moviecatalog.api.TheMovieDBApi
import com.yogi.moviecatalog.models.Movie
import com.yogi.moviecatalog.models.MovieResponse
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class MovieViewModel: ViewModel() {

    private var mutableLiveDataMovies: MutableLiveData<List<Movie>> = MutableLiveData()
    private var isFetching = MutableLiveData<Boolean>()
    private val apiKey = BuildConfig.TMDB_API_KEY
    private val language = R.string.language.toString()

    fun getMovies(): LiveData<List<Movie>> {
        return mutableLiveDataMovies
    }

    fun getIsFetching(): LiveData<Boolean> {
        return isFetching
    }

    private fun setIsFetching(isFetching: Boolean) {
        this.isFetching.postValue(isFetching)
    }

    fun open() {
        setIsFetching(true)
        doAsync {
            val apiRepository = ApiRepository()
            val gson = Gson()
            val data = gson.fromJson(
                apiRepository.doRequest(
                    TheMovieDBApi.getMovie(apiKey, language)
                ), MovieResponse::class.java
            )
            uiThread {
                mutableLiveDataMovies.value = data.results
            }
        }
    }

    fun close() {
        if (mutableLiveDataMovies.value != null) {
            setIsFetching(false)
        }
    }
}