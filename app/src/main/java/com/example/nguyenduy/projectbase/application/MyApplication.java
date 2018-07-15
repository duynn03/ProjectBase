package com.example.nguyenduy.projectbase.application;

import android.app.Application;
import android.content.Context;

import com.example.nguyenduy.projectbase.database.Database;

public class MyApplication extends Application {

    private static Context context;
    private static MyApplication instance;

    public void onCreate() {
        super.onCreate();
        MyApplication.context = getApplicationContext();
        instance = this;
        Database.configDefault(this);
    }

    public static MyApplication getInstance() {
        return instance;
    }

    public static Context getAppContext() {
        return MyApplication.context;
    }

}