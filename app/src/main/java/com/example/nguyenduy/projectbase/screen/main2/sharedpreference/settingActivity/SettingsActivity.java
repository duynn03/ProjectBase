package com.example.nguyenduy.projectbase.screen.main2.sharedpreference.settingActivity;

import android.content.SharedPreferences;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.preference.RingtonePreference;
import android.text.TextUtils;

import com.example.nguyenduy.projectbase.R;
import com.example.nguyenduy.projectbase.utils.LogUtils;
import com.example.nguyenduy.projectbase.utils.method.MethodUtils;
import com.example.nguyenduy.projectbase.utils.method.ResourceUtils;

public class SettingsActivity extends BasePreferenceActivity implements SharedPreferences.OnSharedPreferenceChangeListener {

    private static final String TAG = MethodUtils.getTagClass(SettingsActivity.class);

    private SharedPreferences sharedPref;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Load the preference_activity from an XML resource
        addPreferencesFromResource(R.xml.preference_activity);
        // cách 1: set dựa trên thay đổi value trong SharedPreference
        sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        sharedPref.registerOnSharedPreferenceChangeListener(this);
        // Cách 2: set dựa trên Object Preference
        setOnChangeDataPreferenceListener(ResourceUtils.getString(R.string.pref_key_sync_server), bindListenerPreferenceChange);
        setOnChangeDataPreferenceListener(ResourceUtils.getString(R.string.pref_key_title_checkbox), bindListenerPreferenceChange);
    }

    @Override
    public String getTitleActionBar() {
        return ResourceUtils.getString(R.string.pref_toolbar_title);
    }
    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        LogUtils.i(TAG + "onSharedPreferenceChanged(): key: " + key + " changed");
        // có thể set lại summary
        /*Preference connectionPref = findPreference(key);
        // Set summary to be the user-description for the selected value
        connectionPref.setSummary(sharedPreferences.getString(key, ""));*/
    }

    /*set summary of Preference theo value*/
    public static Preference.OnPreferenceChangeListener bindListenerPreferenceChange = new Preference.OnPreferenceChangeListener() {
        @Override
        public boolean onPreferenceChange(Preference preference, Object o) {
            String value = o.toString();

            // For list preferences, look up the correct display value in the preference's 'entries' list.
            if (preference instanceof ListPreference) {
                ListPreference listPreference = (ListPreference) preference;
                int index = listPreference.findIndexOfValue(value);

                // Set the summary to reflect the new value.
                preference.setSummary(index >= 0 ? listPreference.getEntries()[index] : null);

            } else if (preference instanceof RingtonePreference) {
                // For ringtone preferences, look up the correct display value using RingtoneManager.
                if (TextUtils.isEmpty(value)) {
                    // Empty values correspond to 'silent' (no ringtone).
                    preference.setSummary("Silent");
                } else {
                    Ringtone ringtone = RingtoneManager.getRingtone(preference.getContext(), Uri.parse(value));
                    if (ringtone == null) {
                        // Clear the summary if there was a lookup error.
                        preference.setSummary(null);
                    } else {
                        // Set the summary to reflect the new ringtone display name.
                        String name = ringtone.getTitle(preference.getContext());
                        preference.setSummary(name);
                    }
                }

            } else {
                // For all other preferences, set the summary to the value's simple string representation.
                preference.setSummary(value);
            }
            return true;
        }
    };

    /*example*/
    private boolean readValuePreference() {
        return sharedPref.getBoolean(ResourceUtils.getString(R.string.pref_key_sync_server), false);
    }

    @Override
    protected void onDestroy() {
        sharedPref.unregisterOnSharedPreferenceChangeListener(this);
        super.onDestroy();
    }


}
