package com.yogi.moviecatalog.models

import com.google.gson.annotations.SerializedName

class TvShowResponse (
    @SerializedName("results")
    val results: List<TvShow>?
)