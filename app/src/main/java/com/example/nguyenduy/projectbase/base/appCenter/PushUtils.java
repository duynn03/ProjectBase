package com.example.nguyenduy.projectbase.base.appCenter;

import com.example.nguyenduy.projectbase.application.MyApplication;
import com.example.nguyenduy.projectbase.base.firebase.FireBaseConstants;
import com.microsoft.appcenter.push.Push;

public class PushUtils {

    public static void init() {
        Push.setSenderId(FireBaseConstants.SENDER_ID);
        Push.setListener(new CustomPushListener());
        Push.setEnabled(true);
        Push.enableFirebaseAnalytics(MyApplication.getInstance());   // giữ  Analytic Firebase clound message
    }
}