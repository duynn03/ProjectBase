package com.example.nguyenduy.projectbase.screen.main2.sharedpreference.settingActivity.header;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.nguyenduy.projectbase.R;
import com.example.nguyenduy.projectbase.screen.main2.sharedpreference.settingActivity.SettingsActivity;
import com.example.nguyenduy.projectbase.utils.method.ResourceUtils;

public class SettingHeaderFragment extends BasePreferenceFragment {

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

    @Override
    public String getTitleActionBar() {
        String key = getArguments().getString(ResourceUtils.getString(R.string.pref_extra_key));
        if (ResourceUtils.getString(R.string.pref_extra_value_header_1).equals(key)) {
            return ResourceUtils.getString(R.string.pref_header_fragment_1_title);
        } else if (ResourceUtils.getString(R.string.pref_extra_value_header_2).equals(key)) {
            return ResourceUtils.getString(R.string.pref_header_fragment_2_title);
        }
        return null;
    }
}
