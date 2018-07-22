package com.example.nguyenduy.projectbase.base.firebase.FCM;

import com.example.nguyenduy.projectbase.utils.method.MethodUtils;

public class GooglePlayService {

    private static final String TAG = MethodUtils.getTagClass(GooglePlayService.class);

    // trước khi sử dụng Google Play service feature thì phải luôn check device có tương thích với Google Play service APK hay không
    // check ở onCreate() và onResume()
    public void is() {
        // GoogleApiAvailability.getInstance().checkApiAvailability();
        // if không có thì gọi GoogleApiAvailability.makeGooglePlayServicesAvailable() để user download Google Play services from the Play Store
    }

}
