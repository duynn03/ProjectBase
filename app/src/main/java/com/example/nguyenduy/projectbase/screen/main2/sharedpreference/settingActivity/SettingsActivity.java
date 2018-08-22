package com.example.nguyenduy.projectbase.screen.main2.sharedpreference.settingActivity;

import android.os.Bundle;
import android.preference.PreferenceActivity;

import com.example.nguyenduy.projectbase.R;

public class SettingsActivity extends PreferenceActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Load the preference_activity from an XML resource
        addPreferencesFromResource(R.xml.preference_activity);
    }
}
