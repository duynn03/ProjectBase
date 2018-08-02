package com.example.nguyenduy.projectbase.base.location;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.os.Looper;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;

import java.util.List;

public class ReceiveLocationUpdate {

    private Activity mActivity;
    private IReceiveLocationUpdateListener mListener;
    private boolean isRegistering;

    private FusedLocationProviderClient mFusedLocationClient;
    private LocationCallback mLocationCallback;

    public ReceiveLocationUpdate(Context context) {
        mLocationCallback = initCallBack();
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(context);
        isRegistering = false;
    }

    private LocationCallback initCallBack() {
        return new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                if (locationResult == null) {
                    return;
                }
                // update result
                mListener.onResult(locationResult.getLocations());
            }
        };
    }

    public interface IReceiveLocationUpdateListener {
        void onResult(List<Location> locations);
    }

    public void registerLocationUpdates(Activity activity, IReceiveLocationUpdateListener listener) {
        mActivity = activity;
        mListener = listener;

        // open setting location
        LocationUtils.isOpenSettingLocation(mActivity, new LocationSetting.ILocationSettingListener() {
            @SuppressLint("MissingPermission")
            @Override
            public void onResult(boolean isSuccess) {
                if (isSuccess) {
                    isRegistering = true;
                    mFusedLocationClient.requestLocationUpdates(LocationUtils.createLocationRequest(), mLocationCallback, Looper.myLooper());
                }

            }
        });
    }

    public void unRegisterLocationUpdates() {
        isRegistering = false;
        removeLocationUpdates();
    }

    public void pendingRegisterLocationUpdates() {
        removeLocationUpdates();
    }

    public void reRegisterLocationUpdates() {
        if (isRegistering)
            registerLocationUpdates(mActivity, mListener);
    }

    private void removeLocationUpdates() {
        if (mFusedLocationClient != null) {
            mFusedLocationClient.removeLocationUpdates(mLocationCallback);
        }
    }
}
