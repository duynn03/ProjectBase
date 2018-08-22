package com.example.nguyenduy.projectbase.screen.main2.sharedpreference.settingActivity.header;

import android.preference.PreferenceActivity;

import com.example.nguyenduy.projectbase.R;

import java.util.List;

public class SettingHeaderActivity extends PreferenceActivity {

    @Override
    public void onBuildHeaders(List<Header> target) {
        super.onBuildHeaders(target);
        loadHeadersFromResource(R.xml.preference_activity_header, target);
    }
}
