package com.example.nguyenduy.projectbase.base.broadcast.system.network;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.provider.Settings;

import com.example.nguyenduy.projectbase.R;
import com.example.nguyenduy.projectbase.base.BaseActivity;
import com.example.nguyenduy.projectbase.utils.method.ResourceUtils;
import com.example.nguyenduy.projectbase.utils.method.SystemUtils;

import static com.example.nguyenduy.projectbase.utils.Constants.IntentCommon.REQUEST_CHECK_SETTINGS_MOBILE;
import static com.example.nguyenduy.projectbase.utils.Constants.IntentCommon.REQUEST_CHECK_SETTINGS_WIFI;
import static com.example.nguyenduy.projectbase.utils.Constants.IntentCommon.REQUEST_CHECK_SETTINGS_WIRELESS;

public class ConnectionInternetUtils {

    public static CallbackConnectionInternetListener mListener;

    public interface CallbackConnectionInternetListener {
        void onResult(boolean isConnectInternet);
    }

    public static void isConnectInternet(BaseActivity activity, CallbackConnectionInternetListener listener) {
        mListener = listener;
        if (SystemUtils.isNetworkOnline()) {
            mListener.onResult(true);
        } else {
            showDialogChooseConnectInternet(activity);
        }
    }

    private static void showDialogChooseConnectInternet(BaseActivity activity) {
        new AlertDialog.Builder(activity)
                .setCancelable(true)
                .setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialog) {
                        mListener.onResult(false);
                    }
                })
                .setMessage(ResourceUtils.getString(R.string.msg_user_choose_connection_internet))
                .setPositiveButton(R.string.change_setting_mobile, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        openSettingMobile(activity);
                    }
                })
                .setNegativeButton(R.string.change_setting_wifi, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        openSettingWifi(activity);
                    }
                })
                .setNeutralButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        mListener.onResult(false);
                    }
                })
                .show();
    }

    private static void openSettingNetwork(BaseActivity activity) {
        Intent intent = new Intent(Settings.ACTION_WIRELESS_SETTINGS);
        activity.startActivityForResult(intent, REQUEST_CHECK_SETTINGS_WIRELESS);
    }

    private static void openSettingWifi(BaseActivity activity) {
        Intent intent = new Intent(Settings.ACTION_WIFI_SETTINGS);
        activity.startActivityForResult(intent, REQUEST_CHECK_SETTINGS_WIFI);
    }

    private static void openSettingMobile(BaseActivity activity) {
        Intent intent = new Intent(Settings.ACTION_NETWORK_OPERATOR_SETTINGS);
        activity.startActivityForResult(intent, REQUEST_CHECK_SETTINGS_MOBILE);
    }

    public static void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case REQUEST_CHECK_SETTINGS_WIRELESS:
                mListener.onResult(SystemUtils.isNetworkOnline());
                break;
            case REQUEST_CHECK_SETTINGS_WIFI:
                mListener.onResult(SystemUtils.isWifiConnected());
                break;
            case REQUEST_CHECK_SETTINGS_MOBILE:
                mListener.onResult(SystemUtils.isMobileConnected());
                break;
        }
    }
}
