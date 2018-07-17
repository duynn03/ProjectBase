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

    public static void generateCrash() {
        Crashlytics.getInstance().crash();
    }
}
