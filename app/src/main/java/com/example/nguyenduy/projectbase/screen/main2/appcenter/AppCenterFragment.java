package com.example.nguyenduy.projectbase.screen.main2.appcenter;

import com.example.nguyenduy.projectbase.R;
import com.example.nguyenduy.projectbase.base.BaseFragment;
import com.example.nguyenduy.projectbase.base.IBasePresenter;

public class AppCenterFragment extends BaseFragment<IAppCenterPresenter> implements IAppCenterView {

    @Override
    public int getIdLayout() {
        return R.layout.fragment_appcenter;
    }

    @Override
    public IBasePresenter initPresenter() {
        return new AppCenterPresenterImp(this);
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
