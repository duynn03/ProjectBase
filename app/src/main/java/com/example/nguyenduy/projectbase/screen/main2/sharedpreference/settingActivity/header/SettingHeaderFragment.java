package com.example.nguyenduy.projectbase.screen.main2.sharedpreference.settingActivity.header;

import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.support.annotation.Nullable;

import com.example.nguyenduy.projectbase.R;

public class SettingHeaderFragment extends PreferenceFragment {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String key = getArguments().getString("key");
        if ("header_1".equals(key)) {
            addPreferencesFromResource(R.xml.preference_activity_header_fragment_1);
        } else if ("header_2".equals(key)) {
            addPreferencesFromResource(R.xml.preference_activity_header_fragment_2);
        }
    }
}
