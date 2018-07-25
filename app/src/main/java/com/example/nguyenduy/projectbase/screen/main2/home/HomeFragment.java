package com.example.nguyenduy.projectbase.screen.main2.home;

import com.example.nguyenduy.projectbase.R;
import com.example.nguyenduy.projectbase.base.BaseFragment;
import com.example.nguyenduy.projectbase.base.IBasePresenter;

public class HomeFragment extends BaseFragment<IHomePresenter> implements IHomeView {

    @Override
    public int getIdLayout() {
        return R.layout.fragment_home;
    }

    @Override
    public IBasePresenter initPresenter() {
        return new HomePresenterImp(this);
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
