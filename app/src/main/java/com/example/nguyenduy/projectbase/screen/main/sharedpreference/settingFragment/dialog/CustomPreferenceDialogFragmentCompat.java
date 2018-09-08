package com.example.nguyenduy.projectbase.screen.main.sharedpreference.settingFragment.dialog;

import android.os.Bundle;
import android.support.v7.preference.PreferenceDialogFragmentCompat;

import com.example.nguyenduy.projectbase.utils.LogUtils;
import com.example.nguyenduy.projectbase.utils.method.MethodUtils;

public class CustomPreferenceDialogFragmentCompat extends PreferenceDialogFragmentCompat {

    private static final String TAG = MethodUtils.getTagClass(CustomPreferenceDialogFragmentCompat.class);

    public static CustomPreferenceDialogFragmentCompat newInstance(String key) {
        final CustomPreferenceDialogFragmentCompat fragment = new CustomPreferenceDialogFragmentCompat();
        final Bundle bundle = new Bundle(1);
        bundle.putString(ARG_KEY, key);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onDialogClosed(boolean positiveResult) {
        if (positiveResult) {
            // do things
            // phải lưu ở
            LogUtils.i(TAG + "onDialogClosed(): " + positiveResult);
        }
    }
}
