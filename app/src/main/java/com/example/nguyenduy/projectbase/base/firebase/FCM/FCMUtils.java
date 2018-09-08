package com.example.nguyenduy.projectbase.base.firebase.FCM;

import com.example.nguyenduy.projectbase.utils.method.MethodUtils;

public class FCMUtils {

    private static final String TAG = MethodUtils.getTagClass(FCMUtils.class);

    public static void init() {
        FCMService.init();
    }

}
