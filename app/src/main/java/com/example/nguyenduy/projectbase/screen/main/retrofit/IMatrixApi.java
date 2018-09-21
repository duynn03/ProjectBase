package com.example.nguyenduy.projectbase.screen.main.retrofit;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface IMatrixApi {

    @GET("/maps/api/distancematrix/json")
    Call<Object> getAccounts(
            @Query("origins") String origin,
            @Query("destinations") String destinations,
            @Query("mode") String mode,
            @Query("language") String language,
            @Query("sensor") String sensor,
            @Query("key") String key);
}
