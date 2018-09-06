package com.example.nguyenduy.projectbase.base.firebase;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.Keep;
import android.support.annotation.RequiresPermission;

import com.example.nguyenduy.projectbase.utils.Constants;
import com.example.nguyenduy.projectbase.utils.LogUtils;
import com.example.nguyenduy.projectbase.base.sharedPreference.SharedPreferenceUtils;
import com.example.nguyenduy.projectbase.base.sharedPreference.UserInformation;
import com.example.nguyenduy.projectbase.utils.method.MethodUtils;
import com.google.firebase.analytics.FirebaseAnalytics;

public class AnalyticUtils implements SharedPreferences.OnSharedPreferenceChangeListener {

    private static final String TAG = MethodUtils.getTagClass(AnalyticUtils.class);

    private FirebaseAnalytics mAnalytics;

    @RequiresPermission(
            allOf = {"android.permission.INTERNET", "android.permission.ACCESS_NETWORK_STATE", "android.permission.WAKE_LOCK"}
    )
    @Keep
    public AnalyticUtils(Context context) {
        mAnalytics = FirebaseAnalytics.getInstance(context);
        enableAnalyticsCollection();
        SharedPreferenceUtils.getInstance().registerOnSharedPreferenceChangeListener(this);
        updateUserInformation();
    }

    private void enableAnalyticsCollection() {
        mAnalytics.setAnalyticsCollectionEnabled(true);
    }

    /*public void log(String information) {
        Bundle params = new Bundle();
        params.putString("full_text", information);
        mAnalytics.logEvent("log_infor", params);
    }*/

    private void updateUserInformation() {
        setUserProperty(FireBaseConstants.Analytic.TOKEN, SharedPreferenceUtils.getInstance().getToken());
        setUserProperty(FireBaseConstants.Analytic.APPLICATION_INSTANCE, InstanceIdUtils.getIdAppInstance());
        UserInformation user = SharedPreferenceUtils.getInstance().getUserInformation();
        if (null == user) {
            clearUserInformation();
            return;
        }
        setUserId(user.getId());
        setUserName(user.getName());
        setUserEmail(user.getEmail());
        LogUtils.i(TAG + "updateUserInformation(): " + user.toString());
    }

    private void setUserId(String id) {
        mAnalytics.setUserId(id);
    }

    private void setUserName(String userName) {
        setUserProperty(FireBaseConstants.Analytic.USER_NAME, userName);
    }

    private void setUserEmail(String email) {
        setUserProperty(FireBaseConstants.Analytic.EMAIL, email);
    }

    /**
     * key chỉ bao gồm chữ, số, và dấu _
     *
     * @param key
     * @param value
     */
    private void setUserProperty(String key, String value) {
        mAnalytics.setUserProperty(key, value);
    }

    /**
     * clear User information
     */
    private void clearUserInformation() {
        setUserId("");
        setUserName("");
        setUserEmail("");
        LogUtils.i(TAG + "clearUserInformation()");
    }

    private void resetAnalyticsData() {
        mAnalytics.resetAnalyticsData();
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        if (!Constants.SharedPreference.USER_INFORMATION.equals(key) && !Constants.SharedPreference.TOKEN.equals(key)) {
            return;
        }
        updateUserInformation();
    }

    public void onDestroy() {
        SharedPreferenceUtils.getInstance().unregisterOnSharedPreferenceChangeListener(this);
    }
}
