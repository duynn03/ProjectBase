package com.example.nguyenduy.projectbase.screen.main2.notification;

import com.example.nguyenduy.projectbase.R;
import com.example.nguyenduy.projectbase.base.BaseFragment;
import com.example.nguyenduy.projectbase.base.IBasePresenter;
import com.example.nguyenduy.projectbase.base.notification.NotificationUtils;

import butterknife.OnClick;

public class NotificationFragment extends BaseFragment<INotificationPresenter> implements INotificationView {

    private NotificationUtils notificationUtils;

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
        notificationUtils = new NotificationUtils(getRootActivity());
    }

    @Override
    public void setEvents() {

    }

    @Override
    public void prepareComplete() {

    }

    @OnClick(R.id.btn_show_notification)
    public void onClickButtonShowNotification() {
        notificationUtils.showNotification();
    }

}
