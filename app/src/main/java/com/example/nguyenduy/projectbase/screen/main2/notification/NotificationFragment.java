package com.example.nguyenduy.projectbase.screen.main2.notification;

import com.example.nguyenduy.projectbase.R;
import com.example.nguyenduy.projectbase.base.BaseFragment;
import com.example.nguyenduy.projectbase.base.IBasePresenter;

public class NotificationFragment extends BaseFragment<INotificationPresenter> implements INotificationView {

    @Override
    public int getIdLayout() {
        return R.layout.fragment_notification;
    }

    @Override
    public IBasePresenter initPresenter() {
        return new NotificationPresenterImp(this);
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
