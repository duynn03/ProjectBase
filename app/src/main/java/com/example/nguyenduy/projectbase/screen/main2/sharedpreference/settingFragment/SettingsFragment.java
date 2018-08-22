package com.example.nguyenduy.projectbase.screen.main2.sharedpreference.settingFragment;

import android.os.Bundle;
import android.support.v7.preference.PreferenceFragmentCompat;

import com.example.nguyenduy.projectbase.R;

public class SettingsFragment extends PreferenceFragmentCompat {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Load the preference_activity from an XML resource
        addPreferencesFromResource(R.xml.preference_fragment);
    }

    @Override
    public void onCreatePreferences(Bundle bundle, String s) {

    }
}
