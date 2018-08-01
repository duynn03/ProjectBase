package com.example.nguyenduy.projectbase.base.firebase;

import android.app.Activity;

import com.crashlytics.android.Crashlytics;
import com.example.nguyenduy.projectbase.base.firebase.FCM.FCMUtils;
import com.example.nguyenduy.projectbase.utils.permission.BasePermission;
import com.example.nguyenduy.projectbase.utils.permission.PermissionUtils;

import java.util.List;

public class FireBaseUtils {

    private AnalyticUtils mAnalytic;
    private CrashUtils mCrash;

    public FireBaseUtils(final Activity activity) {
        PermissionUtils.checkPermissionFireBaseAnalytic(activity, new BasePermission.CallbackPermissionListener() {
            @Override
            public void onResult(boolean isSuccess, List<String> permissionDenieds) {
                if (isSuccess) {
                    mAnalytic = new AnalyticUtils(activity);
                }
            }
        });

        mCrash = new CrashUtils(activity);
    }

    public static void init() {
        CrashUtils.init();
        FCMUtils.init();
    }

    public static void generateCrash() {
        Crashlytics.getInstance().crash();
    }

    /*Test*/
    public static void testCrash() {
        CrashUtils.addKey("age: ", 23);
        CrashUtils.addKey("Address1: ", "DU1");
        CrashUtils.addKey("working", true);
        int a = 0;
        try {
            int b = 5 / a;
        } catch (ArithmeticException e) {
            CrashUtils.log(e);
        }
        CrashUtils.log(CrashUtils.LogPriority.LOW, "TAG_testCrash", "Test Crash Log with priority low");
        CrashUtils.log(CrashUtils.LogPriority.NORMAL, "TAG_testCrash", "Test Crash Log with priority normal");
        CrashUtils.log(CrashUtils.LogPriority.HIGH, "TAG_testCrash", "Test Crash Log with priority high");
        CrashUtils.log("Test Crash Log only Message");
        FireBaseUtils.generateCrash();
    }

    public void onDestroy() {
        if (null != mAnalytic) {
            mAnalytic.onDestroy();
        }
        mCrash.onDestroy();
    }
}
