package com.example.nguyenduy.projectbase.base.retrofit.api;

import com.example.nguyenduy.projectbase.base.BaseActivity;
import com.example.nguyenduy.projectbase.base.listView.recycleView.adapter2.BaseSingleTypeAdapterRecycleView;
import com.example.nguyenduy.projectbase.base.retrofit.BaseRetrofit;
import com.example.nguyenduy.projectbase.base.retrofit.model.Account;

public class AccountApi extends BaseRetrofit<IAccountApi> {

    public AccountApi() {
        super(IAccountApi.class);
    }

    public void getAccounts(BaseSingleTypeAdapterRecycleView<Account> adapter, BaseActivity activity, IGetListener<Account> listener) {
        get(adapter, activity, getApi().getAccounts(adapter.getPageCurrent() + 1), listener);
    }

}
