package com.example.tv_show.viewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.tv_show.repositories.TvShowDetailsRepository;
import com.example.tv_show.response.TVShowDetailsResponse;

public class TvShowDetailsViewModel extends ViewModel {

    private TvShowDetailsRepository tvShowDetailsRepository;

    public TvShowDetailsViewModel(){
        tvShowDetailsRepository= new TvShowDetailsRepository();
    }

    public LiveData<TVShowDetailsResponse> getTvShowDetails(String tvshowId){
        return tvShowDetailsRepository.getTvshowDetails(tvshowId);
    }
}
