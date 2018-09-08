package com.example.nguyenduy.projectbase.screen.main.sharedpreference.settingFragment;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceFragmentCompat;

import com.example.nguyenduy.projectbase.R;
import com.example.nguyenduy.projectbase.screen.main.sharedpreference.settingFragment.custom.TimePickerPreference;
import com.example.nguyenduy.projectbase.screen.main.sharedpreference.settingFragment.custom.TimePickerPreferenceDialogFragmentCompat;
import com.example.nguyenduy.projectbase.screen.main.sharedpreference.settingFragment.dialog.CustomDialogPreference;
import com.example.nguyenduy.projectbase.screen.main.sharedpreference.settingFragment.dialog.CustomPreferenceDialogFragmentCompat;

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

    // show dialog
    @Override
    public void onDisplayPreferenceDialog(Preference preference) {
        if (preference instanceof CustomDialogPreference) {
            DialogFragment dialogFragment = CustomPreferenceDialogFragmentCompat.newInstance(preference.getKey());
            dialogFragment.setTargetFragment(this, 0);
            dialogFragment.show(getFragmentManager(), null);
        } else if (preference instanceof TimePickerPreference) {
            DialogFragment dialogFragment = TimePickerPreferenceDialogFragmentCompat.newInstance(preference.getKey());
            dialogFragment.setTargetFragment(this, 0);
            dialogFragment.show(getFragmentManager(), null);
        } else
            super.onDisplayPreferenceDialog(preference);
    }
}
