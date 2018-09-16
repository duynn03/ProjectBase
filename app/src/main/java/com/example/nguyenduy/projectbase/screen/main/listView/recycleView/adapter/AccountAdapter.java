package com.example.nguyenduy.projectbase.screen.main.listView.recycleView.adapter;

import android.app.Activity;
import android.widget.Toast;

import com.example.nguyenduy.projectbase.R;
import com.example.nguyenduy.projectbase.application.MyApplication;
import com.example.nguyenduy.projectbase.base.listView.recycleView.adapter2.BaseSingleTypeAdapterRecycleView;
import com.example.nguyenduy.projectbase.base.listView.recycleView.adapter2.IBaseAdapterListener;
import com.example.nguyenduy.projectbase.base.retrofit.model.Account;

public class AccountAdapter extends BaseSingleTypeAdapterRecycleView<Account> {

    public AccountAdapter(Activity activity) {
        super(activity, new HeaderListener(), new AccountListener());
    }

    @Override
    protected int getLayoutIdHeader() {
        return R.layout.fragment_recycle_view_type_account_header;
    }

    @Override
    protected int getLayoutIdItem() {
        return R.layout.fragment_recycle_view_type_account;
    }

    @Override
    protected Object getObjectHeader() {
        return "Header Account";
    }

    public static class HeaderListener implements IBaseAdapterListener {
        public void onClickHeader(String value) {
            Toast.makeText(MyApplication.getAppContext(), "onClickHeader: " + value, Toast.LENGTH_SHORT).show();
        }
    }

    public static class AccountListener implements IBaseAdapterListener {
        public void onItemClick(String value) {
            Toast.makeText(MyApplication.getAppContext(), "onItemClick: " + value, Toast.LENGTH_SHORT).show();
        }
    }

}
