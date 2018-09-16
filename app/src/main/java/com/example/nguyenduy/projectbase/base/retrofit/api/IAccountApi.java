package com.example.nguyenduy.projectbase.base.retrofit.api;

import com.example.nguyenduy.projectbase.base.retrofit.model.Account;
import com.example.nguyenduy.projectbase.base.retrofit.model.BaseResponseGet;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface IAccountApi {

    @GET("/api/v1/accounts")
    Call<BaseResponseGet<Account>> getAccounts(@Query("pageNumber") int page);
}
