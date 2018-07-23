package com.example.nguyenduy.projectbase.base.drawerlayout;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.example.nguyenduy.projectbase.base.BaseActivity;
import com.example.nguyenduy.projectbase.base.IBasePresenter;

public abstract class BaseActivityWithDrawerLayout<P extends IBasePresenter> extends BaseActivity<P> implements DrawerLayoutUtils.IDrawerLayoutListener {

    private DrawerLayoutUtils drawerLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen()) drawerLayout.closeDrawer();
        else super.onBackPressed();
    }

    @Override
    protected void initBaseView() {
        drawerLayout = new DrawerLayoutUtils(this, this);
        super.initBaseView();
    }

    protected View getHeader() {
        return drawerLayout.getHeader();
    }

}
