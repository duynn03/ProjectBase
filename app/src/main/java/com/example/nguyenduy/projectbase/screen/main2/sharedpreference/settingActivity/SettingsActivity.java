package com.example.nguyenduy.projectbase.screen.main2.sharedpreference.settingActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;

import com.example.nguyenduy.projectbase.R;
import com.example.nguyenduy.projectbase.utils.LogUtils;
import com.example.nguyenduy.projectbase.utils.method.MethodUtils;
import com.example.nguyenduy.projectbase.utils.method.ResourceUtils;

public class SettingsActivity extends PreferenceActivity implements SharedPreferences.OnSharedPreferenceChangeListener {

    private static final String TAG = MethodUtils.getTagClass(SettingsActivity.class);
    private SharedPreferences sharedPref;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Load the preference_activity from an XML resource
        addPreferencesFromResource(R.xml.preference_activity);
        sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        sharedPref.registerOnSharedPreferenceChangeListener(this);
    }

    /*example*/
    private boolean readValuePreference() {

        return sharedPref.getBoolean(ResourceUtils.getString(R.string.pref_key_sync_server), false);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        LogUtils.i(TAG + key + " changed");
        // có thể set lại summary
        /*Preference connectionPref = findPreference(key);
        // Set summary to be the user-description for the selected value
        connectionPref.setSummary(sharedPreferences.getString(key, ""));*/
    }

    @Override
    protected void onDestroy() {
        sharedPref.unregisterOnSharedPreferenceChangeListener(this);
        super.onDestroy();
    }
}
