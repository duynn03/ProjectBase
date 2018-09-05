package com.example.nguyenduy.projectbase.base.backgroundTask.service;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;

import com.example.nguyenduy.projectbase.utils.method.SDKUtils;

public class ServiceUtils {

    private Context mContext;

    public ServiceUtils(Context context) {
        mContext = context;
    }

    public void startBackgroundService() {
        Intent intent = new Intent(mContext, BackgroundService.class);
        intent.putExtra(BackgroundService.DATA, "123");
        mContext.startService(intent);
    }

    @SuppressLint("NewApi")
    public void startForegroundService() {
        Intent intent = new Intent(mContext, ForegroundService.class);
        intent.setAction("Action Start Foreground Service");
        // put extra...
        if (SDKUtils.isVersionSdkCurrentGreaterOrEqualVersion0()) {
            mContext.startForegroundService(intent);
        } else {
            mContext.startService(intent);
        }
    }

    public void stopForegroundService() {
        Intent intent = new Intent(mContext, ForegroundService.class);
        mContext.stopService(intent);
    }

    public void startService() {
        Intent intent = new Intent(mContext, StartedService.class);
        mContext.startService(intent);
    }
}
