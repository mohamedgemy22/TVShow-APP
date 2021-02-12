package com.example.tv_show.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.tv_show.R;
import com.example.tv_show.adapters.TvShowsAdapter;
import com.example.tv_show.databinding.ActivityMainBinding;
import com.example.tv_show.listner.TvShowsListner;
import com.example.tv_show.models.TvShows;
import com.example.tv_show.viewModels.MostPopularTVShowsModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements TvShowsListner {
    private MostPopularTVShowsModel model;
    private ActivityMainBinding activityMainBinding;
    private List<TvShows> list;
    private TvShowsAdapter adapter;
    private int currentPage = 1;
    private int totalAvailbalePages = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        list = new ArrayList<>();
        doInitialiazation();
    }

    private void doInitialiazation() {
        activityMainBinding.tvShowREcyclerViews.setHasFixedSize(true);
        model = new ViewModelProvider(this).get(MostPopularTVShowsModel.class);
        adapter = new TvShowsAdapter(list, this);
        activityMainBinding.tvShowREcyclerViews.setAdapter(adapter);
        activityMainBinding.tvShowREcyclerViews.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (!activityMainBinding.tvShowREcyclerViews.canScrollVertically(1)) {
                    if (currentPage <= totalAvailbalePages) {
                        currentPage += 1;
                        Toast.makeText(getApplicationContext(), "pageNumber:" + String.valueOf(currentPage), Toast.LENGTH_LONG).show();
                        getMostPopularTvshows();
                    }
                }
            }
        });
        getMostPopularTvshows();
    }

    private void getMostPopularTvshows() {
        toggleLoading();
        model.getMostPopularTvShows(currentPage).observe(this, mostpopular -> {
            toggleLoading();
            if (mostpopular != null) {
                totalAvailbalePages = mostpopular.getTotalPages();
                if (mostpopular.getTvShows() != null) {
                    int oldCount = list.size();
                    list.addAll(mostpopular.getTvShows());
                    Log.e("gemmy", mostpopular.getTvShows().toString());

                    adapter.notifyItemRangeInserted(oldCount, list.size());
                }
            }
        });
    }

    private void toggleLoading() {
        if (currentPage == 1) {
            if (activityMainBinding.getIsLoading() != null && activityMainBinding.getIsLoading()) {
                activityMainBinding.setIsLoading(false);
            } else {
                activityMainBinding.setIsLoading(true);
            }
        } else {
            if (activityMainBinding.getIsLoadingMore() != null && activityMainBinding.getIsLoadingMore()) {
                activityMainBinding.setIsLoadingMore(false);
            } else {
                activityMainBinding.setIsLoadingMore(true);
            }
        }
    }

    @Override
    public void ontvShowClicked(TvShows tvShows) {
        //Toast.makeText(this, "clicking  is ready", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getApplicationContext(), TVShowDetailsActivity.class);
        intent.putExtra("id", tvShows.getId());
        intent.putExtra("name", tvShows.getName());
        intent.putExtra("startDate", tvShows.getStart_date());
        intent.putExtra("country", tvShows.getCountry());
        intent.putExtra("network", tvShows.getNetwork());
        intent.putExtra("status", tvShows.getStatus());
        startActivity(intent);

    }
}