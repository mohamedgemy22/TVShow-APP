package com.example.tv_show.bindingAdapters;

import android.util.Log;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

public class BinidingAdapters {

    @BindingAdapter("android:imageURl")
    public static void setImageURl(ImageView imageView, String url) {
        try {
            imageView.setAlpha(0f);
            Picasso.get().load(url).noFade().into(imageView, new Callback() {
                @Override
                public void onSuccess() {
                    imageView.animate().setDuration(3000).alpha(1f).start();
                }

                @Override
                public void onError(Exception e) {

                }
            });

        } catch (Exception e) {
            Log.e("gemmmyErrror",e.getMessage());
        }

    }
}