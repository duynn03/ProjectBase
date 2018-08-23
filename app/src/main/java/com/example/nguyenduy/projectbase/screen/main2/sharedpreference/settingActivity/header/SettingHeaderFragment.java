package com.example.nguyenduy.projectbase.screen.main2.sharedpreference.settingActivity.header;

import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.support.annotation.Nullable;

import com.example.nguyenduy.projectbase.R;
import com.example.nguyenduy.projectbase.utils.method.ResourceUtils;

public class SettingHeaderFragment extends PreferenceFragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String key = getArguments().getString(ResourceUtils.getString(R.string.pref_extra_key));
        if (ResourceUtils.getString(R.string.pref_extra_value_header_1).equals(key)) {
            addPreferencesFromResource(R.xml.preference_activity_header_fragment_1);
        } else if (ResourceUtils.getString(R.string.pref_extra_value_header_2).equals(key)) {
            addPreferencesFromResource(R.xml.preference_activity_header_fragment_2);
        }
    }
}
