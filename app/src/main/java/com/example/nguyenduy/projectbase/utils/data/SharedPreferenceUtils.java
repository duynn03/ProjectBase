package com.example.nguyenduy.projectbase.utils.data;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.example.nguyenduy.projectbase.application.MyApplication;
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
    private Context mContext;
    private SharedPreferences mPreferences;
    private SharedPreferences.Editor mEditor;
    private Gson gson = new Gson();

    private SharedPreferenceUtils() {
        mContext = MyApplication.getAppContext();
        mPreferences = mContext.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
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
    public <T> void setValue(String key, T value) {
        /*convert object to json*/
        setValue(key, gson.toJson(value));
    }

    public <T> void setValue(String key, List<T> value) {
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

    public <T> T getValue(String key, T defaultValue, Class<T> clazz) {
        String value = getValue(key);
        return TextUtils.isEmpty(value) ? defaultValue : gson.fromJson(value, clazz);
    }

    // https://stackoverflow.com/questions/28107647/how-to-save-listobject-to-sharedpreferences/28107838
    public <T> List<T> getValue(String key, List<T> defaultValue, Class<T> clazz) {
        String value = getValue(key);
        if (TextUtils.isEmpty(value)) {
            return defaultValue;
        }
        return gson.fromJson(value, new TypeToken<List<T>>() {
        }.getType());
    }

    public void clear(String key) {
        if (containKey(key)) {
            mEditor.remove(key);
            mEditor.commit();
        }
    }

    public boolean containKey(String key) {
        return mPreferences.contains(key);
    }

    public void clearAll() {
        mEditor.clear().commit();
    }
}
