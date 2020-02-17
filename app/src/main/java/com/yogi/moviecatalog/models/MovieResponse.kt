package com.yogi.moviecatalog.models

import com.google.gson.annotations.SerializedName

data class MovieResponse (
    @SerializedName("results")
    val results: List<Movie>?
)