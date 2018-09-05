package com.example.nguyenduy.projectbase.base.backgroundTask.service;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;

import com.example.nguyenduy.projectbase.utils.LogUtils;
import com.example.nguyenduy.projectbase.utils.method.MethodUtils;


public class BackgroundService extends IntentService {

    private static final String TAG = MethodUtils.getTagClass(BackgroundService.class);
    public static final String DATA = "DATA";

    public BackgroundService() {
        super(TAG);
        LogUtils.i(TAG + "Contructor");
    }

    @Override
    public void onCreate() {
        LogUtils.i(TAG + "onCreate");
        super.onCreate();
    }

    @Override
    public int onStartCommand(@Nullable Intent intent, int flags, int startId) {
        LogUtils.i(TAG + "onStartCommand()");
        return super.onStartCommand(intent, flags, startId);
    }

    // When this method returns, IntentService sẽ stops the service mà không cần phải gọi stopSelf()
    // có thể download file, ...
    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

        LogUtils.i(TAG + "onHandleIntent()");
        // Gets data from the incoming Intent
        if (null != intent) {
            String data = intent.getStringExtra(DATA);
            LogUtils.d(TAG + "onHandleIntent(): data:" + data);
            // Do work here, based on the contents of dataString
            // call stopSelf(); để top service

        }
    }

    @Override
    public void onDestroy() {
        LogUtils.i(TAG + "onDestroy");
        super.onDestroy();
    }
}
