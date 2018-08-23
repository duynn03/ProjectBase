package com.example.nguyenduy.projectbase.screen.main2.sharedpreference.settingActivity.customPreference;

import android.os.Build;
import android.preference.PreferenceDataStore;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;

import com.example.nguyenduy.projectbase.utils.LogUtils;
import com.example.nguyenduy.projectbase.utils.method.MethodUtils;

//https://developer.android.com/guide/topics/ui/settings#providing-data-store-to-preferences
// default thì Preference sẽ lưu value bằng persist nhưng ta có thể custom được: VD: có thể lưu thêm vào database hoặc đẩy lên cloud
@RequiresApi(api = Build.VERSION_CODES.O)
public class CustomPreferenceDataStore implements PreferenceDataStore {

    private static final String TAG = MethodUtils.getTagClass(CustomPreferenceDataStore.class);

    @Override
    public void putString(String key, @Nullable String value) {
        // Write the value somewhere ...
        LogUtils.i(TAG + "putString(): Key: " + key + ", value: " + value);
    }

   /* @Override
    @Nullable
    public String getString(String key, @Nullable String defValue) {
        // Read the value from somewhere and return ...
        LogUtils.i(TAG + "getString(): Key: " + key + ", defValue: " + defValue);
    }*/
}
