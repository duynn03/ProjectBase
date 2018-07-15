package com.example.nguyenduy.projectbase.utils.permission;

import android.Manifest;
import android.app.Activity;

import com.example.nguyenduy.projectbase.R;
import com.example.nguyenduy.projectbase.utils.method.ResourceUtils;
import com.example.nguyenduy.projectbase.utils.permission.BasePermission.CallbackPermissionListener;

public class PermissionUtils {

    private static final int REQUEST_CODE_PERMISSION_INTERNET_ = 200;
    private static final int REQUEST_CODE_PERMISSION_LOCATION = 201;
    private static final int REQUEST_CODE_PERMISSION_WRITE_EXTERNAL_STORAGE = 202;
    private static final int REQUEST_CODE_PERMISSION_READ_EXTERNAL_STORAGE = 203;

    public static void checkPermissionInternet(Activity activity, CallbackPermissionListener listener) {
        BasePermission.checkPermission(activity,
                Manifest.permission.INTERNET,
                REQUEST_CODE_PERMISSION_INTERNET_,
                null,
                null,
                listener);
    }

    public static void checkPermissionLocation(Activity activity, CallbackPermissionListener listener) {
        BasePermission.checkPermission(activity,
                Manifest.permission.ACCESS_FINE_LOCATION,
                REQUEST_CODE_PERMISSION_LOCATION,
                ResourceUtils.getString(R.string.permission_msg_reason_location),
                null,
                listener);
    }

    public static void checkPermissionWriteExternalStorage(Activity activity, CallbackPermissionListener listener) {
        BasePermission.checkPermission(activity,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                REQUEST_CODE_PERMISSION_WRITE_EXTERNAL_STORAGE,
                null,
                ResourceUtils.getString(R.string.permission_msg_reject_write_external_storage),
                listener);
    }

    public static void checkPermissionReadExternalStorage(Activity activity, CallbackPermissionListener listener) {
        BasePermission.checkPermission(activity,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                REQUEST_CODE_PERMISSION_READ_EXTERNAL_STORAGE,
                ResourceUtils.getString(R.string.permission_msg_reason_read_external_storage),
                ResourceUtils.getString(R.string.permission_msg_reject_read_external_storage),
                listener);
    }

}
