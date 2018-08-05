package com.example.nguyenduy.projectbase.base.listener.network;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.provider.Settings;

import com.example.nguyenduy.projectbase.utils.LogUtils;
import com.example.nguyenduy.projectbase.utils.method.MethodUtils;
import com.example.nguyenduy.projectbase.utils.method.SDKUtils;
import com.example.nguyenduy.projectbase.utils.method.SystemUtils;

public class DataSaverUtils {

    private static final String TAG = MethodUtils.getTagClass(DataSaverUtils.class);

    public static final int NOT_ACTIVE_NETWORK_METERED = 4;
    private Context mContext;
    private DataSaverReceiver receiver;

    public DataSaverUtils(Context context) {
        mContext = context;
        if (SDKUtils.isVersionSdkCurrentGreaterOrEqualVersionN())
            receiver = new DataSaverReceiver();
    }

    // check xem user có bật data saver không
    @SuppressLint("NewApi")
    private static int getRestrictBackgroundStatus() {
        return SDKUtils.isVersionSdkCurrentGreaterOrEqualVersionN() ? SystemUtils.getConnectivityManager().getRestrictBackgroundStatus() :
                ConnectivityManager.RESTRICT_BACKGROUND_STATUS_DISABLED;
    }

    // Checks if the device is on a metered network
    @SuppressLint("NewApi")
    private static boolean isActiveNetworkMetered() {
        return SDKUtils.isVersionSdkCurrentGreaterOrEqualVersionJellyBean() && SystemUtils.getConnectivityManager().isActiveNetworkMetered();
    }

    /*
        get status data saver
    *   DataSaverUtils.NOT_ACTIVE_NETWORK_METERED   - không bật 3G
            // The device is not on a metered network, Use data as required to perform syncs, downloads, and updates.

        ConnectivityManager.RESTRICT_BACKGROUND_STATUS_DISABLED: tắt data saver hoặc Allow cho phép background data usage(cho phép app sử dụng mobile data khi running background)
            // Data Saver is disabled. Since the device is connected to a
            // metered network, the app should use less data wherever possible.

        ConnectivityManager.RESTRICT_BACKGROUND_STATUS_WHITELISTED: bật data saver nhưng allow app while data savver on (app đã nằm trong mục whiteList)
            // The app is whitelisted. Wherever possible,
            // the app should use less data in the foreground and background.

        ConnectivityManager.RESTRICT_BACKGROUND_STATUS_ENABLED: - bật data saver hoặc not Allow cho phép background data usage(Không cho phép app sử dụng mobile data khi running background)
            // Background data usage is blocked for this app. Wherever possible,
            // the app should also use less data in the foreground.
    * */
    public static int getStatusDataSaver() {
        return isActiveNetworkMetered() ? getRestrictBackgroundStatus() : NOT_ACTIVE_NETWORK_METERED;
    }

    @SuppressLint("InlinedApi")
    public static void openSettingApplicationWhiteListDataSaver(Context context) {
        if (SDKUtils.isVersionSdkCurrentGreaterOrEqualVersionN())
            context.startActivity(new Intent(
                    Settings.ACTION_IGNORE_BACKGROUND_DATA_RESTRICTIONS_SETTINGS,
                    Uri.parse("package:" + context.getPackageName())));
        else
            LogUtils.e(TAG + "openSettingApplicationWhiteListDataSaver(): API must >= 24");
    }

    @SuppressLint("InlinedApi")
    public void registerReceiver(DataSaverReceiver.IChangeDataSaverListener listener) {
        if (null != receiver) {
            receiver.setListenerDataSaverChange(listener);
            mContext.registerReceiver(receiver, new IntentFilter(ConnectivityManager.ACTION_RESTRICT_BACKGROUND_CHANGED));
        } else
            LogUtils.e(TAG + "registerReceiver(): API must >= 24");
    }

    public void unregisterReceiver() {
        if (null != receiver) {
            receiver.onDestroy();
            mContext.unregisterReceiver(receiver);
        } else
            LogUtils.e(TAG + "unregisterReceiver(): API must >= 24");
    }
}
