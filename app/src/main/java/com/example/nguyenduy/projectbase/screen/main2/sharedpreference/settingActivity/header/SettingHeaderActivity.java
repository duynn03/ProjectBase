package com.example.nguyenduy.projectbase.screen.main2.sharedpreference.settingActivity.header;

import android.annotation.TargetApi;
import android.os.Build;
import android.preference.PreferenceFragment;

import com.example.nguyenduy.projectbase.R;
import com.example.nguyenduy.projectbase.screen.main2.sharedpreference.settingActivity.BasePreferenceActivity;
import com.example.nguyenduy.projectbase.utils.method.ResourceUtils;

import java.util.List;

public class SettingHeaderActivity extends BasePreferenceActivity {

    @Override
    public String getTitleActionBar() {
        return ResourceUtils.getString(R.string.pref_header_activity_title);
    }

    @Override
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public void onBuildHeaders(List<Header> target) {
        super.onBuildHeaders(target);
        loadHeadersFromResource(R.xml.preference_activity_header, target);
    }

    @Override
    protected boolean isValidFragment(String fragmentName) {
        return PreferenceFragment.class.getName().equals(fragmentName) || SettingHeaderFragment.class.getName().equals(fragmentName);
    }

}
