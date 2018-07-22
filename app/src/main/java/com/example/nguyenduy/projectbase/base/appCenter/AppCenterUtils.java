package com.example.nguyenduy.projectbase.base.appCenter;

import android.app.Activity;
import android.util.Log;

import com.example.nguyenduy.projectbase.application.MyApplication;
import com.example.nguyenduy.projectbase.utils.LogUtils;
import com.example.nguyenduy.projectbase.utils.method.MethodUtils;
import com.microsoft.appcenter.AppCenter;
import com.microsoft.appcenter.analytics.Analytics;
import com.microsoft.appcenter.crashes.Crashes;
import com.microsoft.appcenter.distribute.Distribute;
import com.microsoft.appcenter.push.Push;

public class AppCenterUtils {

    private static final String TAG = MethodUtils.getTagClass(AppCenterUtils.class);

    private Activity mActivity;
    private CrashUtils mCrash;

    public AppCenterUtils(Activity activity, CrashUtils.ICrashListener listenerCrash) {
        mActivity = activity;
        mCrash = new CrashUtils(mActivity, listenerCrash);
    }

    public static void init() {
        DistributeUtils.init();
        AppCenter.start(MyApplication.getInstance(), AppCenterConstants.APP_SECRET, Analytics.class, Crashes.class, Distribute.class, Push.class);
        AppCenter.setEnabled(true);
        AppCenter.setLogLevel(Log.VERBOSE);
        CrashUtils.init();
        AnalyticUtils.init();
        PushUtils.init();
        LogUtils.i(TAG + "getInstallId(): " + getInstallId());
    }

    public void onActivityResult(int requestCode) {
        mCrash.onActivityResult(requestCode);
    }

    public static String getInstallId() {
        return AppCenter.getInstallId().get().toString();
    }

    public static void generateCrash() {
        Crashes.generateTestCrash();
    }

}
