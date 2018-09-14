package com.example.nguyenduy.projectbase.base.retrofit;

import com.example.nguyenduy.projectbase.BuildConfig;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitUtils {

    private static RetrofitUtils retrofitUtils;

    private static Retrofit retrofit;

    private RetrofitUtils() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    private static synchronized Retrofit getInstance() {
        if (retrofitUtils == null) {
            retrofitUtils = new RetrofitUtils();
        }
        return retrofit;
    }

    public static synchronized <T> T create(Class<T> clazz) {
        return getInstance().create(clazz);
    }

}
