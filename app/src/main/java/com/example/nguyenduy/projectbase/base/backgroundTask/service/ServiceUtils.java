package com.example.nguyenduy.projectbase.base.backgroundTask.service;

import android.content.Context;
import android.content.Intent;

public class ServiceUtils {

    private Context mContext;

    public ServiceUtils(Context context) {
        mContext = context;
    }

    public void startService() {
        Intent intent = new Intent(mContext, StartedService.class);
        mContext.startService(intent);
    }

    public void startForegroundService() {
        Intent intent = new Intent(mContext, StartedService.class);
        mContext.startService(intent);
    }

}
