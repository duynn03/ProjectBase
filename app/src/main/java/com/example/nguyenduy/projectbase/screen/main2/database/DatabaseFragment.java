package com.example.nguyenduy.projectbase.screen.main2.database;

import com.example.nguyenduy.projectbase.R;
import com.example.nguyenduy.projectbase.base.BaseFragment;
import com.example.nguyenduy.projectbase.base.IBasePresenter;

public class DatabaseFragment extends BaseFragment<IDatabasePresenter> implements IDatabaseView {

    @Override
    public int getIdLayout() {
        return R.layout.fragment_database;
    }

    @Override
    public IBasePresenter initPresenter() {
        return new DatabasePresenterImp(this);
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
