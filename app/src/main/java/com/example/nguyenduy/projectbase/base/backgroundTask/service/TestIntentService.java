package com.example.nguyenduy.projectbase.base.backgroundTask.service;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;

import com.example.nguyenduy.projectbase.utils.method.MethodUtils;


public class TestIntentService extends IntentService {

    private static final String TAG = MethodUtils.getTagClass(TestIntentService.class);

    public TestIntentService() {
        super(TAG);
    }

    // When this method returns, IntentService sẽ stops the service mà không cần phải gọi stopSelf()
    // có thể download file, ...
    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            // Restore interrupt status.
            Thread.currentThread().interrupt();
        }
    }


}
