package com.example.nguyenduy.projectbase.base.drawerlayout;

import android.view.View;

import com.example.nguyenduy.projectbase.base.BaseActivity;
import com.example.nguyenduy.projectbase.base.IBasePresenter;
import com.example.nguyenduy.projectbase.base.drawerlayout.DrawerLayoutUtils;

public abstract class BaseActivityWithDrawerLayout<P extends IBasePresenter> extends BaseActivity<P> implements DrawerLayoutUtils.IDrawerLayoutListener {

    private DrawerLayoutUtils drawerLayout;

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
