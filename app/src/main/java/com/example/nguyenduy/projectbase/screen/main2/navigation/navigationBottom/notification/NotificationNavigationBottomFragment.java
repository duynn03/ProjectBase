package com.example.nguyenduy.projectbase.screen.main2.navigation.navigationBottom.notification;

import com.example.nguyenduy.projectbase.R;
import com.example.nguyenduy.projectbase.base.BaseFragment;
import com.example.nguyenduy.projectbase.base.IBasePresenter;

public class NotificationNavigationBottomFragment extends BaseFragment<INotificationNavigationBottomPresenter> implements INotificationNavigationBottomView {

    @Override
    public int getIdLayout() {
        return R.layout.fragment_navigation_bottom_notification;
    }

    @Override
    public IBasePresenter initPresenter() {
        return new NotificationNavigationBottomPresenterImp(this);
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
