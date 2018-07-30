package com.example.nguyenduy.projectbase.screen.main2.location;

import com.example.nguyenduy.projectbase.R;
import com.example.nguyenduy.projectbase.base.BaseFragment;
import com.example.nguyenduy.projectbase.base.IBasePresenter;

public class LocationFragment extends BaseFragment<ILocationPresenter> implements ILocationView {



    @Override
    public int getIdLayout() {
        return R.layout.fragment_realm;
    }

    @Override
    public IBasePresenter initPresenter() {
        return new LocationPresenterImp(this);
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
