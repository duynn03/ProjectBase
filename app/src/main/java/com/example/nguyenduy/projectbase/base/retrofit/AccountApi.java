package com.example.nguyenduy.projectbase.base.retrofit;

import com.example.nguyenduy.projectbase.base.BaseActivity;

public class AccountApi extends BaseRetrofit<IAccountApi> {

    public AccountApi() {
        super(IAccountApi.class);
    }

    public void getAccounts(BaseActivity activity, IGetListener<Account> listener) {
        get(activity, getApi().getAccounts(), listener);
    }

}
