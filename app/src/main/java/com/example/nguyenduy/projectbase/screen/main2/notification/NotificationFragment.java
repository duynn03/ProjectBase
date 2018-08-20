package com.example.nguyenduy.projectbase.screen.main2.notification;

import android.annotation.SuppressLint;
import android.app.NotificationChannel;

import com.example.nguyenduy.projectbase.R;
import com.example.nguyenduy.projectbase.base.BaseFragment;
import com.example.nguyenduy.projectbase.base.IBasePresenter;
import com.example.nguyenduy.projectbase.base.notification.NotificationUtils;
import com.example.nguyenduy.projectbase.base.notification.channel.NotificationChannelUtils;
import com.example.nguyenduy.projectbase.utils.LogUtils;
import com.example.nguyenduy.projectbase.utils.method.MethodUtils;

import java.util.List;

import butterknife.OnClick;

public class NotificationFragment extends BaseFragment<INotificationPresenter> implements INotificationView {

    private static final String TAG = MethodUtils.getTagClass(NotificationFragment.class);

    private NotificationUtils notificationUtils;
    private NotificationChannelUtils notificationChannelUtils;

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
        notificationChannelUtils = new NotificationChannelUtils(getRootActivity());
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

    @OnClick(R.id.btn_show_notification_group)
    public void onClickButtonShowNotificationGroup() {
        notificationUtils.showNotificationGroup();
    }

    @OnClick(R.id.btn_show_notification_badge)
    public void onClickButtonShowNotificationBadge() {
        notificationUtils.showNotificationBadge();
    }

    @OnClick(R.id.btn_show_notification_action)
    public void onClickButtonShowNotificationAction() {
        notificationUtils.showNotificationAction();
    }

    @OnClick(R.id.btn_show_notification_reply_action)
    public void onClickButtonShowNotificationReplyAction() {
        notificationUtils.showNotificationReplyAction();
    }

    @OnClick(R.id.btn_show_notification_progressbar)
    public void onClickButtonShowNotificationProgressbar() {
        notificationUtils.showNotificationProgressbar();
    }

    @OnClick(R.id.btn_show_notification_progressbar_increase)
    public void onClickButtonShowNotificationProgressbarIncrease() {
        notificationUtils.increaseNotificationProgressbar();
    }

    @OnClick(R.id.btn_show_notification_lock_screen_hidden_content)
    public void onClickButtonShowNotificationLockScreenHiddenContent() {
        notificationUtils.showNotificationLockScreenHiddenContent();
    }

    @OnClick(R.id.btn_show_notification_setting_other)
    public void onClickButtonShowNotificationSettingOther() {
        notificationUtils.showNotificationSettingOther();
    }

    @OnClick(R.id.btn_show_notification_open_regular_activity)
    public void onClickButtonShowNotificationOpenRegularActivity() {
        notificationUtils.showNotificationOpenRegularActivity();
    }

    @OnClick(R.id.btn_show_notification_open_special_activity)
    public void onClickButtonShowNotificationOpenSpecialActivity() {
        notificationUtils.showNotificationOpenSpecialActivity();
    }

    @OnClick(R.id.btn_update_notification)
    public void onClickButtonUpdateNotification() {
        notificationUtils.updateNotification();
    }

    @OnClick(R.id.btn_remove_notification)
    public void onClickButtonRemoveNotification() {
        notificationUtils.removeNotification();
    }

    @OnClick(R.id.btn_remove_notification_timeout)
    public void onClickButtonRemoveNotificationTimeout() {
        notificationUtils.removeNotificationTimeout();
    }


    @OnClick(R.id.btn_remove_all_notification)
    public void onClickButtonRemoveAllNotification() {
        notificationUtils.removeAllNotification();
    }

    @OnClick(R.id.btn_open_setting_notification_channel)
    public void onClickButtonOpenSettingNotificationChannel() {
        notificationChannelUtils.openSettingNotificationChannel(NotificationChannelUtils.CHANNEL_ID, getRootActivity());
    }

    @SuppressLint("NewApi")
    @OnClick(R.id.btn_get_all_channel)
    public void onClickButtonGetAllNotificationChannel() {
        List<NotificationChannel> channels = notificationChannelUtils.getAllChannels();
        if (!MethodUtils.isEmpty(channels)) {
            StringBuilder nameChannels = new StringBuilder();
            for (NotificationChannel channel : channels) {
                nameChannels.append(channel.getName());
            }
            LogUtils.i(TAG + "onClickButtonGetAllNotificationChannel(): " + nameChannels.toString());
            showToast(nameChannels.toString());
        }
    }

    @SuppressLint("NewApi")
    @OnClick(R.id.btn_get_channel_by_id)
    public void onClickButtonGetNotificationChannelById() {
        NotificationChannel channel = notificationChannelUtils.getChannelById(NotificationChannelUtils.CHANNEL_ID);
        if (null != channel) {
            String nameChannel = channel.getName().toString();
            LogUtils.i(TAG + "onClickButtonGetNotificationChannelById(): " + nameChannel);
            showToast(nameChannel);
        }
    }

    @OnClick(R.id.btn_delete_channel)
    public void onClickButtonDeleteNotificationChannel() {
        notificationChannelUtils.deleteChannel(NotificationChannelUtils.CHANNEL_ID);
    }

    @OnClick(R.id.btn_custom_notification)
    public void onClickButtonCustomNotification() {
        notificationUtils.showCustomNotification();
    }

}
