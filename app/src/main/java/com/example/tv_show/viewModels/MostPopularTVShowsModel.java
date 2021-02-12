package com.example.tv_show.viewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.tv_show.repositories.MostPopularTVShowsRepositories;
import com.example.tv_show.response.TVShowsResponses;

public class MostPopularTVShowsModel extends ViewModel {

    private MostPopularTVShowsRepositories mostPopularTVShowsRepositories;

    public MostPopularTVShowsModel(){
        mostPopularTVShowsRepositories= new MostPopularTVShowsRepositories();
    }


    public LiveData<TVShowsResponses> getMostPopularTvShows(int page){
       return mostPopularTVShowsRepositories.getMostPopularShows(page);
    }

}
