package com.example.nguyenduy.projectbase.application;

import android.content.Context;
import android.support.multidex.MultiDexApplication;

import com.example.nguyenduy.projectbase.base.appCenter.AppCenterUtils;
import com.example.nguyenduy.projectbase.base.firebase.FireBaseUtils;
import com.example.nguyenduy.projectbase.database.Database;
import com.example.nguyenduy.projectbase.utils.data.SharedPreference.SharedPreferenceUtils;

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
        Database.configDefault(this);
    }

    public static MyApplication getInstance() {
        return instance;
    }

    public static Context getAppContext() {
        return MyApplication.mContext;
    }

}