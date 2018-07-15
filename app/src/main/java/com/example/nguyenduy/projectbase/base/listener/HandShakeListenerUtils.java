package com.example.nguyenduy.projectbase.base.listener;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;

import com.example.nguyenduy.projectbase.application.MyApplication;

// http://jasonmcreynolds.com/?p=388
public class HandShakeListenerUtils {

    private static HandShakeListenerUtils utils;

    private SensorManager mSensorManager;
    private Sensor mAccelerometer;

    private HandShakeListenerUtils() {
        mSensorManager = (SensorManager) MyApplication.getAppContext().getSystemService(Context.SENSOR_SERVICE);
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
    }

    public static HandShakeListenerUtils getInstance() {
        if (utils == null) {
            utils = new HandShakeListenerUtils();
        }
        return utils;
    }

    public void registerListener(HandShakeListener mHandShake) {
        mSensorManager.registerListener(mHandShake, mAccelerometer, SensorManager.SENSOR_DELAY_UI);
    }

    public void unregisterListener(HandShakeListener mHandShake) {
        mSensorManager.unregisterListener(mHandShake);
    }
}
