package com.example.nguyenduy.projectbase.utils.data.SharedPreference;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.example.nguyenduy.projectbase.application.MyApplication;
import com.example.nguyenduy.projectbase.utils.Constants;
import com.example.nguyenduy.projectbase.utils.method.SystemUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

/*Lưu dưới dạng xml
 * Chỉ Lưu được primitive value
 * Xóa application đi thì sẽ mất data trong sharePreferences*/
/*Phân biệt commit():  Synchronous, nếu thằng nào commit trước thì sẽ thực hiện trước ==> commit sau sẽ được apply , ngoài ra commit() còn return về true or false
apply(): Asynchronous, return về void ==> không biết có apply thành công hay không */
public class SharedPreferenceUtils {

    // sharePreference se được lưu ở data/data/[application package name]/shared_prefs/SHARED_PREFERENCES_NAME.xml
    private static final String SHARED_PREFERENCES_NAME = "SHARED_PREFERENCES_NAME";

    private static SharedPreferenceUtils mSharedPreferenceUtils;
    private SharedPreferences mPreferences;
    private SharedPreferences.Editor mEditor;
    private Gson gson = new Gson();

    private SharedPreferenceUtils() {
        mPreferences = MyApplication.getAppContext().getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
        mEditor = mPreferences.edit();
    }

    public static synchronized SharedPreferenceUtils getInstance() {
        if (mSharedPreferenceUtils == null) {
            mSharedPreferenceUtils = new SharedPreferenceUtils();
        }
        return mSharedPreferenceUtils;
    }

    // Register event khi có thay đổi data thì thông báo cho đối tượng Shared Preference
    //https://gist.github.com/johnkil/db2637f4e92d03beeaf2
    public void registerOnSharedPreferenceChangeListener(SharedPreferences.OnSharedPreferenceChangeListener listener) {
        mPreferences.registerOnSharedPreferenceChangeListener(listener);
    }

    public void unregisterOnSharedPreferenceChangeListener(SharedPreferences.OnSharedPreferenceChangeListener listener) {
        mPreferences.unregisterOnSharedPreferenceChangeListener(listener);
    }

    public void setValue(String key, String value) {
        mEditor.putString(key, value);
        mEditor.commit();
    }

    public void setValue(String key, int value) {
        mEditor.putInt(key, value);
        mEditor.commit();
    }

    public void setValue(String key, double value) {
        setValue(key, Double.toString(value));
    }

    public void setValue(String key, long value) {
        mEditor.putLong(key, value);
        mEditor.commit();
    }

    public void setValue(String key, boolean value) {
        mEditor.putBoolean(key, value);
        mEditor.commit();
    }

    // https://stackoverflow.com/questions/7145606/how-android-sharedpreferences-save-store-object
    public <T> void setObject(String key, T value) {
        /*convert object to json*/
        setValue(key, gson.toJson(value));
    }

    public <T> void setList(String key, List<T> value) {
        /*convert List<object> to json*/
        setValue(key, gson.toJson(value));
    }

    public String getValue(String key, String defaultValue) {
        return mPreferences.getString(key, defaultValue);
    }

    public String getValue(String key) {
        return mPreferences.getString(key, null);
    }

    public int getValue(String key, int defaultValue) {
        return mPreferences.getInt(key, defaultValue);
    }

    public long getValue(String key, long defaultValue) {
        return mPreferences.getLong(key, defaultValue);
    }

    public boolean getValue(String key, boolean defaultValue) {
        return mPreferences.getBoolean(key, defaultValue);
    }

    public <T> T getObject(String key, T defaultValue, Class<T> clazz) {
        String value = getValue(key);
        return TextUtils.isEmpty(value) ? defaultValue : gson.fromJson(value, clazz);
    }

    public <T> T getObject(String key, Class<T> clazz) {
        return getObject(key, null, clazz);
    }

    // https://stackoverflow.com/questions/28107647/how-to-save-listobject-to-sharedpreferences/28107838
    public <T> List<T> getList(String key, List<T> defaultValue, Class<T> clazz) {
        String value = getValue(key);
        if (TextUtils.isEmpty(value)) {
            return defaultValue;
        }
        return gson.fromJson(value, new TypeToken<List<T>>() {
        }.getType());
    }

    public <T> List<T> getList(String key, Class<T> clazz) {
        return getList(key, null, clazz);
    }

    public void clear(String key) {
        if (containKey(key)) {
            mEditor.remove(key);
            mEditor.commit();
        }
    }

    private boolean containKey(String key) {
        return mPreferences.contains(key);
    }

    public void clearAll() {
        mEditor.clear().commit();
    }

    public void setToken(String token) {
        setValue(Constants.SharedPreference.TOKEN, token);
    }

    public String getToken() {
        return getValue(Constants.SharedPreference.TOKEN);
    }

    public void clearToken() {
        clear(Constants.SharedPreference.TOKEN);
    }

    public void setUserInformation(UserInformation user) {
        setObject(Constants.SharedPreference.USER_INFORMATION, user);
    }

    public UserInformation getUserInformation() {
        return getObject(Constants.SharedPreference.USER_INFORMATION, UserInformation.class);
    }

    public void clearUserInformation() {
        clear(Constants.SharedPreference.USER_INFORMATION);
    }

    public void init() {
        setStatusWifi(SystemUtils.isWifiConnected());
        setStatusMobileData(SystemUtils.isMobileConnected());
        setStatusNetwork(SystemUtils.isNetworkOnline());
    }

    public void setStatusWifi(boolean isConnect) {
        setValue(Constants.SharedPreference.WIFI, isConnect);
    }

    public boolean isConnectedWifi() {
        return getValue(Constants.SharedPreference.WIFI, false);
    }

    public void setStatusMobileData(boolean isConnect) {
        setValue(Constants.SharedPreference.MOBILE_DATA, isConnect);
    }

    public boolean isConnectedMobileData() {
        return getValue(Constants.SharedPreference.MOBILE_DATA, false);
    }

    public void setStatusNetwork(boolean isConnect) {
        setValue(Constants.SharedPreference.NETWORK, isConnect);
    }

    public boolean isConnectedNetwork() {
        return getValue(Constants.SharedPreference.NETWORK, false);
    }
}
