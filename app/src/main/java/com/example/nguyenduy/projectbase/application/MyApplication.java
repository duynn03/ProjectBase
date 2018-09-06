package com.example.nguyenduy.projectbase.application;

import android.content.Context;
import android.support.multidex.MultiDexApplication;

import com.example.nguyenduy.projectbase.base.appCenter.AppCenterUtils;
import com.example.nguyenduy.projectbase.base.architectureComponents.database.AppDatabase;
import com.example.nguyenduy.projectbase.base.firebase.FireBaseUtils;
import com.example.nguyenduy.projectbase.base.notification.channel.NotificationChannelUtils;
import com.example.nguyenduy.projectbase.database.Database;
import com.example.nguyenduy.projectbase.base.sharedPreference.SharedPreferenceUtils;

public class MyApplication extends MultiDexApplication {

    private static Context mContext;
    private static MyApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
        instance = this;
        FireBaseUtils.init();
        AppCenterUtils.init();
        SharedPreferenceUtils.getInstance().init();
        new NotificationChannelUtils(this).init();
        Database.configDefault(this);
        // create Room database
        AppDatabase.getInstance();
    }

    public static MyApplication getInstance() {
        return instance;
    }

    public static Context getAppContext() {
        return MyApplication.mContext;
    }

}