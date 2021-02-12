package com.example.tv_show.response;

import com.example.tv_show.models.TvShowsDetails;
import com.google.gson.annotations.SerializedName;

public class TVShowDetailsResponse {

    @SerializedName("tvShow")
    private TvShowsDetails tvShowsDetails;

    public TvShowsDetails getTvShowsDetails() {
        return tvShowsDetails;
    }
}
