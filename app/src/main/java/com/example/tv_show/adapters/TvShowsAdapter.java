package com.example.tv_show.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;
import com.example.tv_show.R;
import com.example.tv_show.databinding.ItemCounterTvShowBinding;
import com.example.tv_show.listner.TvShowsListner;
import com.example.tv_show.models.TvShows;
import java.util.List;

public class TvShowsAdapter extends RecyclerView.Adapter<TvShowsAdapter.TvShowsViewHolder> {
    private List<TvShows> tvShows ;
    private LayoutInflater layoutInflater;
    private TvShowsListner tvShowsListner;

    public TvShowsAdapter(List<TvShows> tvShows, TvShowsListner tvShowsListner) {
        this.tvShows = tvShows;
        this.tvShowsListner= tvShowsListner;
    }

    @NonNull
    @Override
    public TvShowsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.getContext());
        }
        ItemCounterTvShowBinding binding = DataBindingUtil
                .inflate(layoutInflater, R.layout.item_counter_tv_show, parent, false);
        return new TvShowsViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull TvShowsViewHolder holder, int position) {
        holder.bindingTvShow(tvShows.get(position));
    }

    @Override
    public int getItemCount() {

        return tvShows.size();
    }

     class TvShowsViewHolder extends RecyclerView.ViewHolder {
        private ItemCounterTvShowBinding itemCounterTvShowBinding;

        public TvShowsViewHolder(ItemCounterTvShowBinding itemCounterTvShowBinding) {
            super(itemCounterTvShowBinding.getRoot());
            this.itemCounterTvShowBinding = itemCounterTvShowBinding;
        }

        public void bindingTvShow(TvShows tvShows) {
            itemCounterTvShowBinding.setTvShow(tvShows);
            itemCounterTvShowBinding.executePendingBindings();
            itemCounterTvShowBinding.getRoot().setOnClickListener(view -> tvShowsListner.ontvShowClicked(tvShows));
        }


    }

}
