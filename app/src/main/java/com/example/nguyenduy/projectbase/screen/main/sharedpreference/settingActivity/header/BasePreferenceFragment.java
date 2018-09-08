package com.example.nguyenduy.projectbase.screen.main.sharedpreference.settingActivity.header;

import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.support.annotation.Nullable;
import android.view.MenuItem;

import com.example.nguyenduy.projectbase.screen.main.sharedpreference.settingActivity.BasePreferenceActivity;

public abstract class BasePreferenceFragment extends PreferenceFragment {

    private BasePreferenceActivity basePreferenceActivity;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        basePreferenceActivity = (BasePreferenceActivity) getActivity();
        setupActionBar();
    }

    private void setupActionBar() {
        setHasOptionsMenu(true);
        setTitleActionBar();
    }

    private void setTitleActionBar() {
        basePreferenceActivity.setTitleActionBar(getTitleActionBar());
    }

    /*nếu title actionBar không đổi thì return null hoặc ""*/
    public abstract String getTitleActionBar();

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        // event ấn nút back ở actionBar
        if (id == android.R.id.home) {
            getActivity().onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void setOnChangeDataPreferenceListener(String key, Preference.OnPreferenceChangeListener onPreferenceChangeListener) {
        basePreferenceActivity.setOnChangeDataPreferenceListener(key, onPreferenceChangeListener);
    }
}
