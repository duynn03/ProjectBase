package com.example.nguyenduy.projectbase.base.architectureComponents.service;

import com.example.nguyenduy.projectbase.base.architectureComponents.database.entity.User;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface Webservice {
    @GET("/users/{user}")
    Call<User> getUser(@Path("user") String userId);
}
