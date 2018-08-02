package com.example.nguyenduy.projectbase.base.location;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.location.Location;

import com.example.nguyenduy.projectbase.utils.LogUtils;
import com.example.nguyenduy.projectbase.utils.method.SystemUtils;
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
                // not permission
                if (!isSuccess) {
                    return;
                }
                // has permission
                LocationServices.getFusedLocationProviderClient(activity).getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        listener.onResult(location);
                    }
                });
            }
        });
    }

    @SuppressLint("MissingPermission")
    // location = null khi user mới bật location
    public static void getCurrentLocation(final Activity activity, final IGetLastKnownLocationListener listener) {
        // open setting location
        isOpenSettingLocation(activity, new LocationSetting.ILocationSettingListener() {
            @Override
            public void onResult(boolean isSuccess) {
                // not open setting location
                if (!isSuccess) {
                    return;
                }
                // has open setting location
                LocationServices.getFusedLocationProviderClient(activity).getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        // get success location
                        if (null != location) {
                            listener.onResult(location);
                        } else {
                            // not success location
                            // nếu network không bật thì hỏi xem user có muốn bật không
                            if (!SystemUtils.isNetworkOnline()) {

                            } else {
                                // không biết lỗi gì
                                LogUtils.e("Not Get Current Location");
                            }
                        }
                    }
                });
            }
        });
    }

    public static void isOpenSettingLocation(final Activity activity, final LocationSetting.ILocationSettingListener listener) {
//        PermissionUtils.checkPermissionLocation(activity, new BasePermission.CallbackPermissionListener() {
//            @Override
//            public void onResult(boolean isSuccess, List<String> permissionDenieds) {
//                if (isSuccess)
        LocationSetting.isOpen(activity, listener, createLocationRequest());
//            }
//        });
    }

    public static LocationRequest createLocationRequest() {
        return new LocationRequest()
                // 5s
                .setInterval(5000)
                // 1s
                .setFastestInterval(1000)
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
    }
}
