package com.example.nguyenduy.projectbase.base.appCenter;

import android.app.Activity;
import android.util.Log;

import com.example.nguyenduy.projectbase.application.MyApplication;
import com.example.nguyenduy.projectbase.utils.LogUtils;
import com.microsoft.appcenter.AppCenter;
import com.microsoft.appcenter.analytics.Analytics;
import com.microsoft.appcenter.crashes.Crashes;
import com.microsoft.appcenter.distribute.Distribute;
import com.microsoft.appcenter.push.Push;
import com.microsoft.appcenter.utils.async.AppCenterConsumer;

import java.util.UUID;

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
    private PushUtils mPush;

    public AppCenterUtils(Activity activity, CrashUtils.ICrashListener listenerCrash) {
        mActivity = activity;
        mDistribute = new DistributeUtils();
        AppCenter.start(MyApplication.getInstance(), APP_SCRET, Analytics.class, Crashes.class, Distribute.class, Push.class);
        AppCenter.setEnabled(true);
        AppCenter.setLogLevel(Log.VERBOSE);
        mCrash = new CrashUtils(mActivity, listenerCrash);
        mAnalytic = new AnalyticUtils();

        mPush = new PushUtils();


        AppCenter.getInstallId().thenAccept(new AppCenterConsumer<UUID>() {
            @Override
            public void accept(UUID uuid) {
                LogUtils.e("getInstallId(): " + uuid);
            }
        });
    }

    public void onActivityResult(int requestCode) {
        mCrash.onActivityResult(requestCode);
    }

    public static void generateCrash(){
        Crashes.generateTestCrash();
    }

}
