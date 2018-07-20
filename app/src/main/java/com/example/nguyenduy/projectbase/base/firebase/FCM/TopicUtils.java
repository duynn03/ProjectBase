package com.example.nguyenduy.projectbase.base.firebase.FCM;

import android.support.annotation.NonNull;
import android.widget.Toast;

import com.example.nguyenduy.projectbase.application.MyApplication;
import com.example.nguyenduy.projectbase.utils.LogUtils;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;

public class TopicUtils {

    /**
     * Có thể subscribe bất kì topic nào đã tồn tại
     * Nếu chưa tồn tại thì FCM sẽ tạo ra topic đó và client nào cũng subscribe được
     * Unlimit topics and subscriptions for each app
     */
    public static Task<Void> subscribe(final String topic) {
        return FirebaseMessaging.getInstance().subscribeToTopic(topic)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        String msg = task.isSuccessful() ? "Subscribed " + topic + " success" : "subscribed fail";
                        LogUtils.e("FCMService: " + msg);
                        Toast.makeText(MyApplication.getAppContext(), msg, Toast.LENGTH_SHORT).show();
                    }
                });
    }

    public static Task<Void> unsubscribe(final String topic) {
        return FirebaseMessaging.getInstance().unsubscribeFromTopic(topic)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        String msg = task.isSuccessful() ? "Unsubscribed " + topic + " success" : "unsubscribed fail";
                        LogUtils.e("FCMService: " + msg);
                        Toast.makeText(MyApplication.getAppContext(), msg, Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
