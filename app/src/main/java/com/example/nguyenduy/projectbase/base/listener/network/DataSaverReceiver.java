package com.example.nguyenduy.projectbase.base.listener.network;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.example.nguyenduy.projectbase.utils.Constants;
import com.example.nguyenduy.projectbase.utils.data.SharedPreference.SharedPreferenceUtils;

public class DataSaverReceiver extends BroadcastReceiver implements SharedPreferences.OnSharedPreferenceChangeListener {

    private IChangeDataSaverListener mListener;

    DataSaverReceiver() {
        updateStatusDataSaver();
        SharedPreferenceUtils.getInstance().registerOnSharedPreferenceChangeListener(this);
    }

    public interface IChangeDataSaverListener {
        void changeStatusDataSaver(int status);
    }

    public void setListenerDataSaverChange(IChangeDataSaverListener listener) {
        mListener = listener;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        updateStatusDataSaver();
    }

    private void updateStatusDataSaver() {
        SharedPreferenceUtils.getInstance().setStatusDataSaver(DataSaverUtils.getStatusDataSaver());
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        if (Constants.SharedPreference.DATA_SAVER.equals(key) && null != mListener)
            mListener.changeStatusDataSaver(SharedPreferenceUtils.getInstance().getStatusDataSaver());
    }

    public void onDestroy() {
        SharedPreferenceUtils.getInstance().unregisterOnSharedPreferenceChangeListener(this);
    }

}