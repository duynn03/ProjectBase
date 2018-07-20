package com.example.nguyenduy.projectbase.base.firebase;

import android.content.Context;

import com.crashlytics.android.Crashlytics;

public class FireBaseUtils {

    private AnalyticUtils mAnalytic;
    private CrashUtils mCrash;

    public FireBaseUtils(Context context) {
        mAnalytic = new AnalyticUtils(context);
        mCrash = new CrashUtils(context);
    }

    public static void init() {
        CrashUtils.init();
    }

    public static void generateCrash() {
        Crashlytics.getInstance().crash();
    }

    /*Test*/
    public static void testCrash() {
        CrashUtils.addKey("age: ", 23);
        CrashUtils.addKey("Address1: ", "DU1");
        CrashUtils.addKey("working", false);
        int a = 0;
        try {
            int b = 5 / a;
        } catch (ArithmeticException e) {
            CrashUtils.log(e);
        }
        CrashUtils.log(CrashUtils.LogPriority.NORMAL, "TAG_testCrash", "Test Crash Log with Tag");
        CrashUtils.log("Test Crash Log only Message");
        FireBaseUtils.generateCrash();
    }

    public void onDestroy() {
        mAnalytic.onDestroy();
        mCrash.onDestroy();
    }
}
