package com.example.nguyenduy.projectbase.base.location;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.location.Location;

import com.example.nguyenduy.projectbase.utils.permission.BasePermission;
import com.example.nguyenduy.projectbase.utils.permission.PermissionUtils;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.List;

public class LocationUtils {

    public interface IGetLastKnownLocationListener {
        void onResult(Location location);
    }

    @SuppressLint("MissingPermission")
    // location = null khi user tắt location
    public static void getLastKnownLocation(final Activity activity, final IGetLastKnownLocationListener listener) {
        PermissionUtils.checkPermissionLocation(activity, new BasePermission.CallbackPermissionListener() {
            @Override
            public void onResult(boolean isSuccess, List<String> permissionDenieds) {
                if (isSuccess) // isOpen setting location
                    LocationServices.getFusedLocationProviderClient(activity).getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() {
                        @Override
                        public void onSuccess(Location location) {
                            listener.onResult(location);
                        }
                    });
            }
        });
    }

    public static void isOpenSettingLocation(final Activity activity, final LocationSetting.ILocationSettingListener listener) {
        LocationSetting.isOpen(activity, listener, createLocationRequest());
    }

    public static LocationRequest createLocationRequest() {
        return new LocationRequest()
                // 10s
                .setInterval(5000)
                // 5s
                .setFastestInterval(2000)
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
    }
}
