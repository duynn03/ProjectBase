package com.example.nguyenduy.projectbase.screen.main2.navigation.navigationBottom.home;

import com.example.nguyenduy.projectbase.R;
import com.example.nguyenduy.projectbase.base.BaseFragment;
import com.example.nguyenduy.projectbase.base.IBasePresenter;

public class HomeNavigationBottomFragment extends BaseFragment<IHomeNavigationBottomPresenter> implements IHomeNavigationBottomView {

    @Override
    public int getIdLayout() {
        return R.layout.fragment_navigation_bottom_home;
    }

    @Override
    public IBasePresenter initPresenter() {
        return new HomeNavigationBottomPresenterImp(this);
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
