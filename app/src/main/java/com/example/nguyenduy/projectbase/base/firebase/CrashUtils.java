package com.example.nguyenduy.projectbase.base.firebase;

import android.content.Context;
import android.content.SharedPreferences;

import com.crashlytics.android.Crashlytics;
import com.example.nguyenduy.projectbase.application.MyApplication;
import com.example.nguyenduy.projectbase.utils.Constants;
import com.example.nguyenduy.projectbase.utils.LogUtils;
import com.example.nguyenduy.projectbase.base.sharedPreference.SharedPreferenceUtils;
import com.example.nguyenduy.projectbase.base.sharedPreference.UserInformation;
import com.example.nguyenduy.projectbase.utils.method.MethodUtils;

import io.fabric.sdk.android.Fabric;

// custom crash: https://firebase.google.com/docs/crashlytics/customize-crash-reports
public class CrashUtils implements SharedPreferences.OnSharedPreferenceChangeListener {

    private static final String TAG = MethodUtils.getTagClass(CrashUtils.class);

    public CrashUtils(Context context) {
        enableDebuggerCrashlytics(context);
        SharedPreferenceUtils.getInstance().registerOnSharedPreferenceChangeListener(this);
    }

    // Enables Crashlytics debugger
    private static void enableDebuggerCrashlytics(Context context) {
        Fabric.with(new Fabric.Builder(context)
                .kits(new Crashlytics())
                // Enables Crashlytics debugger
                .debuggable(true)
                .build());
    }

    private String getUserId() {
        return Crashlytics.getInstance().getIdentifier();
    }

    public static void init() {
        enableDebuggerCrashlytics(MyApplication.getAppContext());
        updateUserInformation();
    }

    private static void updateUserInformation() {
        addKey(FireBaseConstants.Crash.TOKEN, SharedPreferenceUtils.getInstance().getToken());
        addKey(FireBaseConstants.Crash.APPLICATION_INSTANCE, InstanceIdUtils.getIdAppInstance());
        UserInformation user = SharedPreferenceUtils.getInstance().getUserInformation();
        if (null == user) {
            clearUserInformation();
            return;
        }
        setUserId(user.getId());
        setUserName(user.getUsername());
        setUserEmail(user.getEmail());
        LogUtils.i(TAG + "updateUserInformation(): " + user.toString());
    }

    /**
     * identifier phải là duy nhất để phân biệt được các user với nhau
     * identifier có thể là numberID, token, hoặc hashed value
     *
     * @param id)
     */
    private static void setUserId(String id) {
        Crashlytics.setUserIdentifier(id);
    }

    private static void setUserName(String userName) {
        Crashlytics.setUserName(userName);
    }

    private static void setUserEmail(String email) {
        Crashlytics.setUserEmail(email);
    }

    /**
     * clear User information
     */
    private static void clearUserInformation() {
        setUserId("");
        setUserName("");
        setUserEmail("");
        LogUtils.i(TAG + "clearUserInformation()");
    }

    public enum LogPriority {
        LOW(1), NORMAL(2), HIGH(3);
        private final int value;

        LogPriority(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    /**
     * File log limit 64KB & File log sẽ xóa các log cũ hơn nếu vượt quá 64 KB
     *
     * @param priority
     * @param tag
     * @param message
     */
    public static void log(LogPriority priority, String tag, String message) {
        Crashlytics.log(priority.getValue(), tag, message);
    }

    public static void log(String message) {
        Crashlytics.log(message);
    }

    /**
     * Chỉ lưu max là 8 Exception.
     * Nếu Lớn hơn 8 Exception thì sẽ override lên cái cũ
     *
     * @param throwable
     */
    public static void log(Throwable throwable) {
        Crashlytics.logException(throwable);
    }

    /**
     * Có tối đa là 64 key/value. Nếu quá thì không lưu nữa
     * Mỗi cặp key-value có max size là 1KB
     * Có thể dùng add key để track last UI action.
     *
     * @param key
     * @param value
     */
    public static void addKey(String key, String value) {
        Crashlytics.setString(key, value);
    }

    public static void addKey(String key, boolean value) {
        Crashlytics.setBool(key, value);
    }

    public static void addKey(String key, double value) {
        Crashlytics.setDouble(key, value);
    }

    public static void addKey(String key, float value) {
        Crashlytics.setFloat(key, value);
    }

    public static void addKey(String key, int value) {
        Crashlytics.setInt(key, value);
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
