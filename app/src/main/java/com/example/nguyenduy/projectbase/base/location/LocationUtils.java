package com.example.nguyenduy.projectbase.base.location;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.location.Location;

import com.example.nguyenduy.projectbase.utils.permission.BasePermission;
import com.example.nguyenduy.projectbase.utils.permission.PermissionUtils;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.SettingsClient;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import java.util.List;

public class LocationUtils {

    private static LocationUtils mLocationUtils;


    private LocationUtils() {

    }

    public static synchronized LocationUtils getInstance() {
        if (mLocationUtils == null) {
            mLocationUtils = new LocationUtils();
        }
        return mLocationUtils;
    }


    public interface IGetLastKnownLocationListener {
        void onResult(Location location);
    }

    @SuppressLint("MissingPermission")
    // location = null khi user tắt location
    public void getLastKnownLocation(final Activity activity, final IGetLastKnownLocationListener listener) {
        PermissionUtils.checkPermissionLocation(activity, new BasePermission.CallbackPermissionListener() {
            @Override
            public void onResult(boolean success, List<String> permissionDenieds) {
                if (success) // open setting location
                    LocationServices.getFusedLocationProviderClient(activity).getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() {
                        @Override
                        public void onSuccess(Location location) {
                            listener.onResult(location);
                        }
                    });
            }
        });
    }

    // https://developer.android.com/training/location/change-location-settings
    private boolean checkLocationSetting(Context context) {
        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder();
        SettingsClient client = LocationServices.getSettingsClient(context);
        Task<LocationSettingsResponse> task = client.checkLocationSettings(builder.build());
        task.
    }

    public void openLocationSetting() {
//        https://developers.google.com/android/reference/com/google/android/gms/location/SettingsClient
        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder()
                .addLocationRequest(createLocationRequest());
    }

    private LocationRequest createLocationRequest() {
        return new LocationRequest()
                .setInterval(10000)
                .setFastestInterval(5000)
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
    }
}
