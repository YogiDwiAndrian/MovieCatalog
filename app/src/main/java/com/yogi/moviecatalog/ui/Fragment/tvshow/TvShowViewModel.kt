package com.yogi.moviecatalog.ui.Fragment.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.yogi.moviecatalog.BuildConfig
import com.yogi.moviecatalog.R
import com.yogi.moviecatalog.api.ApiRepository
import com.yogi.moviecatalog.api.TheMovieDBApi
import com.yogi.moviecatalog.models.TvShow
import com.yogi.moviecatalog.models.TvShowResponse
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class TvShowViewModel : ViewModel() {

    private var mutableLiveDataTvShows: MutableLiveData<List<TvShow>> = MutableLiveData()
    private var isFetching = MutableLiveData<Boolean>()
    private val apiKey = BuildConfig.TMDB_API_KEY
    private val language =  R.string.language.toString()

    fun getTvShows(): LiveData<List<TvShow>> {
        return mutableLiveDataTvShows
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
                    TheMovieDBApi.getTvShow(apiKey, language)
                ), TvShowResponse::class.java
            )
            uiThread {
                mutableLiveDataTvShows.value = data.results
            }
        }
    }

    fun close() {
        if (mutableLiveDataTvShows.value != null) {
            setIsFetching(false)
        }
    }
}