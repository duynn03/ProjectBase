package com.example.nguyenduy.projectbase.base.appCenter;

import com.microsoft.appcenter.push.Push;

public class PushUtils {

    private final String SERVER_KEY = "AAAASfmsTAA:APA91bGbrx_1r12DjmiMLDkOrfidXH3PItOJ7MAJ1bz2Y1h8JRDSDN4tv75VEHebNo7JmekBH_j-KED7yQnefQLjlucltT_BoU47h8MgOGsyIwq60NaKYldfXBY_xOl5FwxIAQlM2Nc2fi-JURV3y-ybnH0Mg7uF7Q";
    private final String SENDER_ID = "317721431040";

    public PushUtils() {
        Push.setSenderId(SENDER_ID);
        Push.setListener(new CustomPushListener());
        Push.setEnabled(true);
        // Push.enableFirebaseAnalytics(MyApplication.getInstance());   // giữ  Analytic Firebase
    }
}
