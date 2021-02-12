package com.example.tv_show.repositories;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.tv_show.network.APISERVICE;
import com.example.tv_show.network.ApiClass;
import com.example.tv_show.response.TVShowDetailsResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TvShowDetailsRepository {

    private APISERVICE apiservice;

public TvShowDetailsRepository(){
    apiservice= ApiClass.getRetrofit().create(APISERVICE.class);
}

public LiveData<TVShowDetailsResponse> getTvshowDetails(String tvShowId){
    MutableLiveData<TVShowDetailsResponse> data= new MutableLiveData<>();
    apiservice.getShowDetailsResponse(tvShowId).enqueue(new Callback<TVShowDetailsResponse>() {
        @Override
        public void onResponse(@NonNull  Call<TVShowDetailsResponse> call,@NonNull Response<TVShowDetailsResponse> response) {
            data.setValue(response.body());
        }

        @Override
        public void onFailure(@NonNull Call<TVShowDetailsResponse> call,@NonNull Throwable t) {
            Log.e("soso",t.toString());
        }
    });
    return data;
}

}
