package com.example.nguyenduy.projectbase.base.firebase;

import android.app.Activity;
import android.content.Context;

import com.crashlytics.android.Crashlytics;
import com.example.nguyenduy.projectbase.utils.permission.BasePermission;
import com.example.nguyenduy.projectbase.utils.permission.PermissionUtils;

import java.util.List;

public class FireBaseUtils {

    private AnalyticUtils mAnalytic;
    private CrashUtils mCrash;

    public FireBaseUtils(final Activity activity) {
        PermissionUtils.checkPermissionFireBaseAnalytic(activity, new BasePermission.CallbackPermissionListener() {
            @Override
            public void onResult(boolean success, List<String> permissionDenieds) {
                if (success) {
                    mAnalytic = new AnalyticUtils(activity);
                }
            }
        });

        mCrash = new CrashUtils(activity);
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
        if (null != mAnalytic) {
            mAnalytic.onDestroy();
        }
        mCrash.onDestroy();
    }
}
