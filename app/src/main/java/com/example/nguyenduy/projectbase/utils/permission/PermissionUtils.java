package com.example.nguyenduy.projectbase.utils.permission;

import android.Manifest;
import android.app.Activity;

import com.example.nguyenduy.projectbase.R;
import com.example.nguyenduy.projectbase.utils.method.ResourceUtils;
import com.example.nguyenduy.projectbase.utils.permission.BasePermission.CallbackPermissionListener;
import com.example.nguyenduy.projectbase.utils.permission.BasePermission.PermissionBuilder;

public class PermissionUtils {

    public static void checkPermissionFireBaseAnalytic(Activity activity, CallbackPermissionListener listener) {
        BasePermission.checkPermission(
                new PermissionBuilder(
                        activity,
                        listener,
                        Manifest.permission.INTERNET,
                        Manifest.permission.ACCESS_NETWORK_STATE,
                        Manifest.permission.WAKE_LOCK)
                        .setReason(ResourceUtils.getString(R.string.permission_msg_reason_firebase_analytic)));
    }

    public static void checkPermissionInternet(Activity activity, CallbackPermissionListener listener) {
        BasePermission.checkPermission(
                new PermissionBuilder(
                        activity,
                        listener,
                        Manifest.permission.INTERNET));
    }

    public static void checkPermissionLocation(Activity activity, CallbackPermissionListener listener) {
        BasePermission.checkPermission(
                new PermissionBuilder(
                        activity,
                        listener,
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION)
                        .setReason(ResourceUtils.getString(R.string.permission_msg_reason_location))
                        .setMessageReject(ResourceUtils.getString(R.string.permission_msg_reject_location)));
    }

    public static void checkPermissionNetwork(Activity activity, CallbackPermissionListener listener) {
        BasePermission.checkPermission(
                new PermissionBuilder(
                        activity,
                        listener,
                        Manifest.permission.INTERNET,
                        Manifest.permission.ACCESS_NETWORK_STATE)
                        .setReason(ResourceUtils.getString(R.string.permission_msg_reason_access_network_state))
                        .setMessageReject(ResourceUtils.getString(R.string.permission_msg_reject_access_network_state)));
    }

    public static void checkPermissionWriteExternalStorage(Activity activity, CallbackPermissionListener listener) {
        BasePermission.checkPermission(
                new PermissionBuilder(
                        activity,
                        listener,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        .setMessageReject(ResourceUtils.getString(R.string.permission_msg_reject_write_external_storage)));
    }

    public static void checkPermissionReadExternalStorage(Activity activity, CallbackPermissionListener listener) {
        BasePermission.checkPermission(
                new PermissionBuilder(
                        activity,
                        listener,
                        Manifest.permission.READ_EXTERNAL_STORAGE)
                        .setReason(ResourceUtils.getString(R.string.permission_msg_reason_read_external_storage))
                        .setMessageReject(ResourceUtils.getString(R.string.permission_msg_reject_read_external_storage)));
    }

    public static void checkPermissionWriteExternalStorageAndLocation(Activity activity, CallbackPermissionListener listener) {
        BasePermission.checkPermission(
                new PermissionBuilder(
                        activity,
                        listener,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.ACCESS_FINE_LOCATION)
                        .setReason(ResourceUtils.getString(R.string.permission_msg_reason_read_external_storage_and_location))
                        .setMessageReject(ResourceUtils.getString(R.string.permission_msg_reject_read_external_storage_and_location)));
    }

}
