package com.example.nguyenduy.projectbase.base.firebase;

import android.content.Context;

import com.crashlytics.android.Crashlytics;

import io.fabric.sdk.android.Fabric;

// custom crash: https://firebase.google.com/docs/crashlytics/customize-crash-reports
public class CrashUtils {

    private Context mContext;

    public CrashUtils(Context context) {
        mContext = context;
        enableDebuggerCrashlytics();
    }

    // Enables Crashlytics debugger
    public void enableDebuggerCrashlytics() {
        Fabric.with(new Fabric.Builder(mContext)
                .kits(new Crashlytics())
                .debuggable(true)           // Enables Crashlytics debugger
                .build());
    }

    /**
     * identifier phải là duy nhất để phân biệt được các user với nhau
     * identifier có thể là numberID, token, hoặc hashed value
     *
     * @param identifier
     */
    public static void addUserId(String identifier) {
        Crashlytics.setUserIdentifier(identifier);
    }

    public static void addUserEmail(String email) {
        Crashlytics.setUserEmail(email);
    }

    public static void addUserName(String name) {
        Crashlytics.setUserName(name);
    }

    /**
     * clear UserIdentifier
     */
    public static void clearUserId() {
        Crashlytics.setUserIdentifier("");
    }

    /**
     * File log limit 64KB & File log sẽ xóa các log cũ hơn nếu vượt quá 64 KB
     *
     * @param priority
     * @param tag
     * @param msg
     */
    public static void log(int priority, String tag, String msg) {
        Crashlytics.log(priority, tag, msg);
    }

    public static void log(String msg) {
        Crashlytics.log(msg);
    }

    /**
     * Chỉ lưu max là 8 Exception.
     * Nếu Lớn hơn 8 Exception thì sẽ override lên cái cũ
     *
     * @param exception
     */
    public static void log(Exception exception) {
        Crashlytics.logException(exception);
    }

    /**
     * Có tối đa là 64 key/value. Nếu quá thì không lưu nữa
     * Mỗi cặp key-value có max size là 1KB
     *
     * @param key
     * @param value
     */
    public static void addInformation(String key, String value) {
        Crashlytics.setString(key, value);
    }

    public static void addInformation(String key, boolean value) {
        Crashlytics.setBool(key, value);
    }

    public static void addInformation(String key, double value) {
        Crashlytics.setDouble(key, value);
    }

    public static void addInformation(String key, float value) {
        Crashlytics.setFloat(key, value);
    }

    public static void addInformation(String key, int value) {
        Crashlytics.setInt(key, value);
    }
}
