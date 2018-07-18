package com.example.nguyenduy.projectbase.base.firebase.FCM;

import android.support.annotation.NonNull;
import android.widget.Toast;

import com.example.nguyenduy.projectbase.application.MyApplication;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;

public class TopicUtils {

    /**
     * Có thể subscribe bất kì topic nào đã tồn tại
     * Nếu chưa tồn tại thì FCM sẽ tạo ra topic đó và client nào cũng subscribe được
     */
    private Task<Void> subscribe() {
        return FirebaseMessaging.getInstance().subscribeToTopic("news")
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(MyApplication.getAppContext(), task.isSuccessful() ? "subscribed success" : "subscribed fail", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private Task<Void> unsubscribe() {
        return FirebaseMessaging.getInstance().unsubscribeFromTopic("news")
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(MyApplication.getAppContext(), task.isSuccessful() ? "unsubscribed success" : "unsubscribed fail", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
