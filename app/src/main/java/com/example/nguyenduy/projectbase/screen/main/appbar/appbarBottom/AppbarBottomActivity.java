package com.example.nguyenduy.projectbase.screen.main.appbar.appbarBottom;

import com.example.nguyenduy.projectbase.R;
import com.example.nguyenduy.projectbase.base.BaseActivity;

public class AppbarBottomActivity extends BaseActivity<IAppbarBottomPresenter> implements IAppbarBottomView {

   // BottomAppBar bar

    @Override
    public int getIdLayout() {
        return R.layout.activity_appbar_bottom;
    }

    @Override
    public IAppbarBottomPresenter initPresenter() {
        return new AppbarBottomPresenterImp(this);
    }

    @Override
    public void initViews() {

    }

    @Override
    public void initComponents() {
    }

    @Override
    public void setEvents() {

    }

    @Override
    public void prepareComplete() {

    }

}
