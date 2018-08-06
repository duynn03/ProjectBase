package com.example.nguyenduy.projectbase.base.notification;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;

import com.example.nguyenduy.projectbase.R;
import com.example.nguyenduy.projectbase.application.MyApplication;
import com.example.nguyenduy.projectbase.screen.main2.MainActivity;
import com.example.nguyenduy.projectbase.utils.method.SDKUtils;

public class NotificationUtils {

    private static final String CHANNEL_ID = "channel_01_ID";
    private static final String CHANNEL_NAME = "channel_01_NAME";
    private static final String CHANNEL_DESCRIPTION = "channel_01_DESCRIPTION";

    private Context mContext;

    public NotificationUtils(Context context) {
        mContext = context;
    }

    private Notification createNotification() {
        String contentSmall = "Content Small Notification Content Small Notification Content Small Notification Content Small Notification Content Small Notification Content Small Notification Content Small Notification Content Small Notification Content Small Notification Content Small Notification Content Small Notification Content Small Notification Content Small Notification Content Small Notification Content Small Notification";
        String contentBig = "Content Big Notification Content Big Notification Content Big Notification Content Big Notification Content Big Notification Content Big Notification Content Big Notification Content Big Notification Content Big Notification Content Big Notification Content Big Notification Content Big Notification Content Big Notification Content Big Notification Content Big Notification Content Big Notification Content Big Notification Content Big Notification Content Big Notification";
        return new NotificationCompat.Builder(mContext, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_menu_camera)
                .setContentTitle("Title Notification")
                // default thì content sẽ chỉ hiển thị trên 1 line
                .setContentText(contentSmall)
                // set content hiển thị full trên expanable notification thì set style
                // https://developer.android.com/training/notify-user/expanded
                .setStyle(new NotificationCompat.BigTextStyle().bigText(contentBig))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                // set intent to activity
                // https://developer.android.com/training/notify-user/navigation
                .setContentIntent(createIntentActivity())
                // auto remove notification khi user taps vào notification
                .setAutoCancel(true)
                .addAction(R.drawable.ic_menu_gallery, "Action Button", createIntentAction())
                .build();
    }

    public void showNotification() {
        // lưu lại id notification để update notification hoặc remove notification
        int idNotification = 1;
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(mContext);
        notificationManager.notify(idNotification, createNotification());
    }

    private PendingIntent createIntentActivity() {
        Intent intent = new Intent(mContext, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        return PendingIntent.getActivity(mContext, 0, intent, 0);
    }

    String EXTRA_NOTIFICATION_ID = "EXTRA_NOTIFICATION_ID";

    private PendingIntent createIntentAction() {
        Intent actionIntent = new Intent(this, NotificationBroadcastReceiver.class);
        actionIntent.setAction("Intent Action Button");
        actionIntent.putExtra(EXTRA_NOTIFICATION_ID, 1);
        return PendingIntent.getBroadcast(mContext, 0, actionIntent, 0);
    }

    @SuppressLint("NewApi")
    public static void init() {
        if (SDKUtils.isVersionSdkCurrentGreaterOrEqualVersion0())
            createChannel();
    }

    // Create the NotificationChannel, but only on API 26+ because the NotificationChannel class is new and not in the support library
    @RequiresApi(api = Build.VERSION_CODES.O)
    private static void createChannel() {
        // Register the channel with the system; you can't change the importance or other notification behaviors after this
        // NotificationManager.IMPORTANCE_DEFAULT giống với setPriority trên api 24 trở xuống
        // https://developer.android.com/training/notify-user/channels#importance
        NotificationChannel channel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT);
        channel.setDescription(CHANNEL_DESCRIPTION);
        MyApplication.getAppContext().getSystemService(NotificationManager.class).createNotificationChannel(channel);
    }
}