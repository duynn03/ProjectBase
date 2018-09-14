package com.example.nguyenduy.projectbase.base.retrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface IAccountApi {

    @GET("/api/v1/accounts")
    Call<List<Account>> getAccounts();
}
