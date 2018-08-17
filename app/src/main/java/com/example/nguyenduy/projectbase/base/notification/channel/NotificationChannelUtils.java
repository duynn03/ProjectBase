package com.example.nguyenduy.projectbase.base.notification.channel;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.RequiresApi;

import com.example.nguyenduy.projectbase.application.MyApplication;
import com.example.nguyenduy.projectbase.utils.LogUtils;
import com.example.nguyenduy.projectbase.utils.method.MethodUtils;
import com.example.nguyenduy.projectbase.utils.method.SDKUtils;

public class NotificationChannelUtils {

    private static final String TAG = MethodUtils.getTagClass(NotificationChannelUtils.class);

    public static final String CHANNEL_ID = "channel_01_ID";
    /*channel name: User có thể nhìn thấy*/
    private static final String CHANNEL_NAME = "channel_01_NAME";
    private static final String CHANNEL_DESCRIPTION = "channel_01_DESCRIPTION";

    @SuppressLint("NewApi")
    public static void init() {
        // register channel
        if (SDKUtils.isVersionSdkCurrentGreaterOrEqualVersion0())
            createChannel();
    }

    // Create the NotificationChannel, but only on API 26+ because the NotificationChannel class is new and not in the support library
    // Xem thêm https://medium.com/exploring-android/exploring-android-o-notification-channels-94cd274f604c
    @RequiresApi(api = Build.VERSION_CODES.O)
    private static void createChannel() {
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
        // https://developer.android.com/reference/android/app/Notification#VISIBILITY_SECRET
        channel.setLockscreenVisibility(Notification.VISIBILITY_PUBLIC);
        // sound
        //channel.setSound();
        // có thể set multi channel bằng createNotificationChannels()
        MyApplication.getAppContext().getSystemService(NotificationManager.class).createNotificationChannel(channel);
    }

    @SuppressLint("InlinedApi")
    public static void openSettingNotificationChannel(int idNotificationChannel, final Activity activity) {
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
