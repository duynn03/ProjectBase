package com.example.nguyenduy.projectbase.base.appCenter;

import android.app.Activity;
import android.util.Log;

import com.example.nguyenduy.projectbase.application.MyApplication;
import com.microsoft.appcenter.AppCenter;
import com.microsoft.appcenter.analytics.Analytics;
import com.microsoft.appcenter.crashes.Crashes;
import com.microsoft.appcenter.distribute.Distribute;

public class AppCenterUtils {

    // https://appcenter.ms/orgs/CompanyDuyNguyen/apps/BaseProject/
    // Email: duynn03@gmail.com
    // Password: Nguyenduy1
    // branch github
    // duynn03@gmail.com
    // nguyenduy1
    private final String APP_SCRET = "c0bd7330-a45c-4ebf-b60c-08ddaf4bd4bc";

    private Activity mActivity;
    private CrashUtils mCrash;
    private AnalyticUtils mAnalytic;
    private DistributeUtils mDistribute;

    public AppCenterUtils(Activity activity, CrashUtils.ICrashListener listenerCrash) {
        mActivity = activity;
        AppCenter.start(MyApplication.getInstance(), APP_SCRET, Analytics.class, Crashes.class, Distribute.class);
        AppCenter.setLogLevel(Log.VERBOSE);
        mCrash = new CrashUtils(mActivity, listenerCrash);
        mAnalytic = new AnalyticUtils();
        mDistribute = new DistributeUtils();
    }

    public void onActivityResult(int requestCode) {
        mCrash.onActivityResult(requestCode);
    }

}
