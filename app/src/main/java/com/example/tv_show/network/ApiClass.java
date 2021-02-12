package com.example.tv_show.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClass {
    private static Retrofit retrofit;

    public static Retrofit getRetrofit(){
        if (retrofit ==null){
            retrofit= new Retrofit
                    .Builder()
                    .baseUrl("https://www.episodate.com/api/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

}
