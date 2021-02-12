package com.example.tv_show.repositories;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.tv_show.network.APISERVICE;
import com.example.tv_show.network.ApiClass;
import com.example.tv_show.response.TVShowsResponses;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MostPopularTVShowsRepositories {

    private APISERVICE apiservice;

   public MostPopularTVShowsRepositories(){
        apiservice = ApiClass.getRetrofit().create(APISERVICE.class);
    }


    public LiveData<TVShowsResponses> getMostPopularShows(int page){
        MutableLiveData<TVShowsResponses> data = new MutableLiveData<>();
        apiservice.getMostPopularShowsResponse(page).enqueue(new Callback<TVShowsResponses>() {
            @Override
            public void onResponse(@NonNull Call<TVShowsResponses> call,@NonNull Response<TVShowsResponses> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<TVShowsResponses> call,@NonNull Throwable t) {
                data.setValue(null);
            }
        });
        return data;
    }

}
