package com.example.tv_show.response;

import com.example.tv_show.models.TvShows;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TVShowsResponses {
    @SerializedName("page")
    private int page;

    @SerializedName("pages")
    private int totalPages;

    @SerializedName("tv_shows")
    private List<TvShows> tvShows;


    public int getPage() {
        return page;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public List<TvShows> getTvShows() {
        return tvShows;
    }
}
