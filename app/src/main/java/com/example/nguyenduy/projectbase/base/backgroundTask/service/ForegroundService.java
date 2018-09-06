package com.example.nguyenduy.projectbase.base.backgroundTask.service;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;

import com.example.nguyenduy.projectbase.R;
import com.example.nguyenduy.projectbase.base.notification.channel.NotificationChannelUtils;
import com.example.nguyenduy.projectbase.screen.main2.MainActivity;
import com.example.nguyenduy.projectbase.utils.LogUtils;
import com.example.nguyenduy.projectbase.utils.method.MethodUtils;

/*khi service start thì lifecycle sẽ độc lập với component gọi nó*/
public class ForegroundService extends Service {

    private static final String TAG = MethodUtils.getTagClass(ForegroundService.class);

    /*call method này để setup service (trước khi call onStartCommand() và onBind())
     * Nếu service đang running thì sẽ không vào method này*/
    @Override
    public void onCreate() {
        LogUtils.i(TAG + "onCreate");
        super.onCreate();
    }

    private static final int ONGOING_NOTIFICATION_ID = 1;

    /*bất kì component nào call startService() thì khi vào method này thì service đã start và run nó trên background vô thời hạn
    ==> khi nào không dùng thì phải stopService()*/
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (null == intent) return START_STICKY;

        LogUtils.i(TAG + "onStartCommand(): Action: " + intent.getAction());

        /*note: cần thêm perrmission <uses-permission android:name="android.permission.FOREGROUND_SERVICE" /> */
        // Id of startForeground phải khác 0
        startForeground(ONGOING_NOTIFICATION_ID, createNotificationForegroundService());
        // có thể remove notification (nhưng không stop service)
        //stopForeground(true);

        // Do work here, based on the contents of dataString

        return START_STICKY;
    }

    private Notification createNotificationForegroundService() {
        return new NotificationCompat.Builder(this, NotificationChannelUtils.CHANNEL_ID)
                .setContentTitle("Foreground Service")
                .setContentText("Foreground Service Foreground Service Foreground Service Foreground Service Foreground Service")
                .setSmallIcon(R.drawable.ic_splash)
                .setContentIntent(createIntentToSpecialActivity())
                .build();
    }

    private PendingIntent createIntentToSpecialActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        return PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
    }


    // không phải bindService thì return null
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    /*call method khi service không được sử dụng nữa. bằng cách gọi stopSelf() hoặc stopService()
     * clear all resource. Ex: threads, registered listeners, or receivers */
    @Override
    public void onDestroy() {
        LogUtils.i(TAG + "onDestroy");
        super.onDestroy();
    }
}
