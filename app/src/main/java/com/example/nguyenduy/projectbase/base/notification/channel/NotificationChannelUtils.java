package com.example.nguyenduy.projectbase.base.notification.channel;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationChannelGroup;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.RequiresApi;

import com.example.nguyenduy.projectbase.utils.LogUtils;
import com.example.nguyenduy.projectbase.utils.method.MethodUtils;
import com.example.nguyenduy.projectbase.utils.method.SDKUtils;

import java.util.List;

public class NotificationChannelUtils {

    private static final String TAG = MethodUtils.getTagClass(NotificationChannelUtils.class);

    public static final String CHANNEL_ID = SDKUtils.getApplicationID() + "_channel_01_ID";
    /*channel name: User có thể nhìn thấy*/
    private static final String CHANNEL_NAME = "channel_01_NAME";
    private static final String CHANNEL_DESCRIPTION = "channel_01_DESCRIPTION";

    public static final String GROUP_CHANNEL_ID = SDKUtils.getApplicationID() + "_Groưp_channel_01_ID";
    private static final String GROUP_CHANNEL_NAME = "Groưp_channel_01_NAME";
    private static final String GROUP_CHANNEL_DESCRIPTION = "Groưp_channel_01_DESCRIPTION";

    private NotificationManager notificationManager;

    public NotificationChannelUtils(Context context) {
        notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
    }

    @SuppressLint("NewApi")
    public void init() {
        // register channel
        if (!SDKUtils.isVersionSdkCurrentGreaterOrEqualVersion0()) {
            return;
        }
        createChannel();
        createGroupChannel();
    }

    @SuppressLint("NewApi")
    public List<NotificationChannel> getAllChannels() {
        if (!SDKUtils.isVersionSdkCurrentGreaterOrEqualVersion0()) {
            LogUtils.e(TAG + "getAllChannels(): API must >= 26");
            return null;
        }
        return notificationManager.getNotificationChannels();
    }

    @SuppressLint("NewApi")
    public List<NotificationChannelGroup> getAllGroupChannels() {
        if (!SDKUtils.isVersionSdkCurrentGreaterOrEqualVersion0()) {
            LogUtils.e(TAG + "getAllGroupChannels(): API must >= 26");
            return null;
        }
        return notificationManager.getNotificationChannelGroups();
    }

    @SuppressLint("NewApi")
    public NotificationChannel getChannelById(String idNotificationChannel) {
        if (!SDKUtils.isVersionSdkCurrentGreaterOrEqualVersion0()) {
            LogUtils.e(TAG + "getChannelById(): API must >= 26");
            return null;
        }
        return notificationManager.getNotificationChannel(idNotificationChannel);
    }

    @SuppressLint("NewApi")
    public NotificationChannelGroup getGroupChannelById(String idNotificationGroupChannel) {
        if (!SDKUtils.isVersionSdkCurrentGreaterOrEqualVersion0()) {
            LogUtils.e(TAG + "getGroupChannelById(): API must >= 26");
            return null;
        }
        return notificationManager.getNotificationChannelGroup(idNotificationGroupChannel);
    }

    // Create the NotificationChannel, but only on API 26+ because the NotificationChannel class is new and not in the support library
    // Xem thêm https://medium.com/exploring-android/exploring-android-o-notification-channels-94cd274f604c
    @RequiresApi(api = Build.VERSION_CODES.O)
    private void createChannel() {
        // Register the channel with the system; you can't change the importance or other notification behaviors after this
        // NotificationManager.IMPORTANCE_DEFAULT giống với setPriority trên api 24 trở xuống
        // https://developer.android.com/training/notify-user/channels#importance
        NotificationChannel channel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_HIGH);
        channel.setDescription(CHANNEL_DESCRIPTION);
        // set light
        channel.enableLights(true);
        channel.setLightColor(Color.RED);
        // set rung
        channel.enableVibration(true);
        channel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
        // set lock screen
        // https://developer.android.com/training/notify-user/build-notification?hl=pt-br#lockscreenNotification
        channel.setLockscreenVisibility(Notification.VISIBILITY_PUBLIC);
        // sound
        //channel.setSound();
        // set badge notification
        channel.setShowBadge(true);
        // có thể set multi channel bằng createNotificationChannels()
        notificationManager.createNotificationChannel(channel);
    }

    /*Mặc dù đã xáo nhưng trong setting vẫn hiển thị lên channel (để tránh spam)*/
    @SuppressLint("NewApi")
    public void deleteChannel(String idNotificationChannel) {
        if (!SDKUtils.isVersionSdkCurrentGreaterOrEqualVersion0()) {
            LogUtils.e(TAG + "deleteChannel(): API must >= 26");
            return;
        }
        notificationManager.deleteNotificationChannel(idNotificationChannel);
    }

    /*TODO - chưa test được*/
    // https://developer.android.com/training/notify-user/channels?hl=pt-br#CreateChannelGroup
    // sau khi create xong thì có thể gọi setGroup()
    @SuppressLint("NewApi")
    private void createGroupChannel() {
        if (!SDKUtils.isVersionSdkCurrentGreaterOrEqualVersion0()) {
            LogUtils.e(TAG + "createGroupChannel(): API must >= 26");
            return;
        }
        NotificationChannelGroup groupChannel = new NotificationChannelGroup(GROUP_CHANNEL_ID, GROUP_CHANNEL_NAME);
        if (SDKUtils.isVersionSdkCurrentGreaterOrEqualVersionP()) {
            groupChannel.setDescription(GROUP_CHANNEL_DESCRIPTION);
        }
        // có thể set multi channel bằng createNotificationChannels()
        notificationManager.createNotificationChannelGroup(groupChannel);
    }

    @SuppressLint("InlinedApi")
    public void openSettingNotificationChannel(String idNotificationChannel, final Activity activity) {
        if (!SDKUtils.isVersionSdkCurrentGreaterOrEqualVersion0()) {
            LogUtils.e(TAG + "openSettingNotificationChannel(): API must >= 26");
            return;
        }

        Intent intent = new Intent(Settings.ACTION_CHANNEL_NOTIFICATION_SETTINGS);
        intent.putExtra(Settings.EXTRA_APP_PACKAGE, activity.getPackageName());
        intent.putExtra(Settings.EXTRA_CHANNEL_ID, idNotificationChannel);
        activity.startActivity(intent);
    }
}
