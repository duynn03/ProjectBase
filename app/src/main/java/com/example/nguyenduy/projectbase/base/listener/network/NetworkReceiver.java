package com.example.nguyenduy.projectbase.base.listener.network;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.example.nguyenduy.projectbase.utils.Constants;
import com.example.nguyenduy.projectbase.utils.data.SharedPreference.SharedPreferenceUtils;
import com.example.nguyenduy.projectbase.utils.method.SystemUtils;

// https://developer.android.com/training/basics/network-ops/managing
public class NetworkReceiver extends BroadcastReceiver implements SharedPreferences.OnSharedPreferenceChangeListener {

    private INetworkChangeListener mListenerNetworkChange;
    private IChangeWifiListener mListenerWifiChange;
    private IChangeMobileDataListener mListenerMobileDataChange;

    public NetworkReceiver() {
        updateStatusNetwork();
        SharedPreferenceUtils.getInstance().registerOnSharedPreferenceChangeListener(this);
    }

    public interface IChangeWifiListener {
        void changeWifiConnected(boolean isWifiConnected);
    }

    public interface IChangeMobileDataListener {
        void changeMobileDataConnected(boolean isMobileConnected);
    }

    public interface INetworkChangeListener {
        void changeNetworkConnected(boolean isConnected);
    }

    public void setListenerNetworkChange(INetworkChangeListener listener) {
        mListenerNetworkChange = listener;
    }

    public void setListenerWifiChange(IChangeWifiListener listener) {
        mListenerWifiChange = listener;
    }

    public void setListenerMobileDataChange(IChangeMobileDataListener listener) {
        mListenerMobileDataChange = listener;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        updateStatusNetwork();
    }

    private void updateStatusNetwork() {
        SharedPreferenceUtils.getInstance().setStatusWifi(SystemUtils.isWifiConnected());
        SharedPreferenceUtils.getInstance().setStatusMobileData(SystemUtils.isMobileConnected());
        SharedPreferenceUtils.getInstance().setStatusNetwork(SystemUtils.isNetworkOnline());
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        if (Constants.SharedPreference.NETWORK.equals(key) && null != mListenerNetworkChange)
            mListenerNetworkChange.changeNetworkConnected(SharedPreferenceUtils.getInstance().isConnectedNetwork());
        else if (Constants.SharedPreference.WIFI.equals(key) && null != mListenerWifiChange)
            mListenerWifiChange.changeWifiConnected(SharedPreferenceUtils.getInstance().isConnectedWifi());
        else if (Constants.SharedPreference.MOBILE_DATA.equals(key) && null != mListenerMobileDataChange)
            mListenerMobileDataChange.changeMobileDataConnected(SharedPreferenceUtils.getInstance().isConnectedMobileData());
    }

    public void onDestroy() {
        SharedPreferenceUtils.getInstance().unregisterOnSharedPreferenceChangeListener(this);
    }

}