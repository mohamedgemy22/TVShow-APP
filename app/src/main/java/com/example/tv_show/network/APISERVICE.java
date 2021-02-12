package com.example.tv_show.network;

import com.example.tv_show.response.TVShowDetailsResponse;
import com.example.tv_show.response.TVShowsResponses;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APISERVICE {

    @GET("most-popular")
    Call<TVShowsResponses> getMostPopularShowsResponse(@Query("page") int page);

    @GET("show-details")
    Call<TVShowDetailsResponse> getShowDetailsResponse(@Query("q") String tvShowId);
}
