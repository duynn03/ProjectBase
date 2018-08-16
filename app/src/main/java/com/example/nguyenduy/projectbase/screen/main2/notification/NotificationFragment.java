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

    @OnClick(R.id.btn_show_notification_big_text)
    public void onClickButtonShowNotificationBigText() {
        notificationUtils.showNotificationBigText();
    }

    @OnClick(R.id.btn_show_notification_text_multi_line)
    public void onClickButtonShowNotificationTextMultiLine() {
        notificationUtils.showNotificationTextMultiLine();
    }

    @OnClick(R.id.btn_show_notification_text_style_message)
    public void onClickButtonShowNotificationTextStyleMessage() {
        notificationUtils.showNotificationTextStyleMessage();
    }

    @OnClick(R.id.btn_show_notification_big_image_1)
    public void onClickButtonShowNotificationBigImage1() {
        notificationUtils.showNotificationBigImage1();
    }

    @OnClick(R.id.btn_show_notification_big_image_2)
    public void onClickButtonShowNotificationBigImage2() {
        notificationUtils.showNotificationBigImage2();
    }

    @OnClick(R.id.btn_show_notification_big_image_3)
    public void onClickButtonShowNotificationBigImage3() {
        notificationUtils.showNotificationBigImage3();
    }

    @OnClick(R.id.btn_show_notification_control_media)
    public void onClickButtonShowNotificationControlMedia() {
        // TODO
        notificationUtils.showNotificationControlMedia();
    }

}
