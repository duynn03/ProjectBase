package com.example.nguyenduy.projectbase.screen.main2.backgroundTask.service;

import com.example.nguyenduy.projectbase.R;
import com.example.nguyenduy.projectbase.base.BaseFragment;
import com.example.nguyenduy.projectbase.base.IBasePresenter;

public class ServiceFragment extends BaseFragment<IServicePresenter> implements IServiceView {

    @Override
    public int getIdLayout() {
        return R.layout.fragment_service;
    }

    @Override
    public IBasePresenter initPresenter() {
        return new ServicePresenterImp(this);
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
