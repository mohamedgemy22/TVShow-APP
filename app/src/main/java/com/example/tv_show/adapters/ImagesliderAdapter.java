package com.example.tv_show.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tv_show.R;
import com.example.tv_show.databinding.ItemCounterSliderBinding;

public class ImagesliderAdapter extends RecyclerView.Adapter<ImagesliderAdapter.ImageSliderviewHolder> {

    String [] sliderImageUrl;
    private LayoutInflater layoutInflater;


    public ImagesliderAdapter(String[] sliderImageUrl) {
        this.sliderImageUrl = sliderImageUrl;
    }


    @NonNull
    @Override
    public ImageSliderviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       if (layoutInflater == null) {
           layoutInflater = LayoutInflater.from(parent.getContext());
       }
       ItemCounterSliderBinding sliderBinding = DataBindingUtil.inflate(layoutInflater,R.layout.item_counter_slider,parent,false);
        return new ImageSliderviewHolder(sliderBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageSliderviewHolder holder, int position) {
          holder.bindingAdapterImage(sliderImageUrl[position]);
    }

    @Override
    public int getItemCount() {
        return sliderImageUrl.length;
    }

    static class ImageSliderviewHolder  extends RecyclerView.ViewHolder{
         private ItemCounterSliderBinding itemCounterSliderBinding;
        public ImageSliderviewHolder( ItemCounterSliderBinding itemCounterSliderBinding) {
            super(itemCounterSliderBinding.getRoot());
            this.itemCounterSliderBinding= itemCounterSliderBinding;
        }
        public void bindingAdapterImage(String imagUrl){
            itemCounterSliderBinding.setImageUrl(imagUrl);
        }
    }

}
