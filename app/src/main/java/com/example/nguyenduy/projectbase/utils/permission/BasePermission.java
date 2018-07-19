package com.example.nguyenduy.projectbase.utils.permission;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.text.TextUtils;

import com.example.nguyenduy.projectbase.R;

import java.util.ArrayList;
import java.util.List;

public class BasePermission {

    private static List<PermissionBuilder> permissions = new ArrayList<>();

    // Permission for fragment
    //https://stackoverflow.com/questions/32714787/android-m-permissions-onrequestpermissionsresult-not-being-called
    public static void checkPermission(Activity activity,
                                       String permission,
                                       int requestCode,
                                       CharSequence messageReasonNeedPermission,
                                       CharSequence messageReject,
                                       final CallbackPermissionListener listener) {
        if (hasPermission(activity, permission)) {
            listener.onResult(true);
            return;
        }
        // shouldShowRequestPermissionRationale return về true nếu user đã denied request trước đó
        boolean shouldShowReasonNeedPermission = ActivityCompat.shouldShowRequestPermissionRationale(activity, permission);
        PermissionBuilder builder = new PermissionBuilder(activity, permission, requestCode, messageReasonNeedPermission, messageReject, listener);
        if (shouldShowReasonNeedPermission && !TextUtils.isEmpty(messageReasonNeedPermission)) {
            showReasonNeedPermission(builder);
        } else {
            requestPermission(builder);
        }
    }

    private static boolean hasPermission(Context context, String permission) {
        return ActivityCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED;
    }

    private static void showReasonNeedPermission(final PermissionBuilder permission) {
        new AlertDialog.Builder(permission.activity)
                .setCancelable(false)
                .setMessage(permission.messageReasonNeedPermission)
                .setPositiveButton(R.string.OK, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        requestPermission(permission);
                    }
                })
                .show();
    }

    private static void requestPermission(PermissionBuilder builder) {
        permissions.add(builder);
        ActivityCompat.requestPermissions(builder.activity, new String[]{builder.permission}, builder.requestCode);
    }

    private static void showMessageRejected(final PermissionBuilder permission) {
        new AlertDialog.Builder(permission.activity)
                .setCancelable(false)
                .setMessage(permission.messageReject)
                .setPositiveButton(R.string.OK, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        permission.listener.onResult(false);
                        permissions.remove(permission);
                    }
                })
                .setNegativeButton(R.string.change_setting, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        openAppDetailSetting(permission);
                    }
                })
                .show();
    }

    private static void openAppDetailSetting(PermissionBuilder permission) {
        Intent intent = new Intent();
        intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package", permission.activity.getPackageName(), null);
        intent.setData(uri);
        permission.activity.startActivityForResult(intent, permission.requestCode);
    }

    // Because this lib only supports request one permission at one time,
    // so this method called 'onRequestPermissionResult',
    // not 'onRequestPermissionsResult'
    public static void onRequestPermissionResult(Activity activity,
                                                 int requestCode,
                                                 String[] permissions,
                                                 int[] grantResults) {
        PermissionBuilder permission = null;
        for (PermissionBuilder itemPermission : BasePermission.permissions) {
            if (itemPermission.activity.equals(activity)
                    && itemPermission.requestCode == requestCode
                    && itemPermission.permission.equals(permissions[0])) {
                permission = itemPermission;
                break;
            }
        }
        if (permission != null) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                permission.listener.onResult(true);
                BasePermission.permissions.remove(permission);
            } else {
                if (TextUtils.isEmpty(permission.messageReject)) {
                    permission.listener.onResult(false);
                    BasePermission.permissions.remove(permission);
                } else {
                    showMessageRejected(permission);
                }
            }
        }
    }

    public static void onActivityResult(Activity activity, int requestCode) {
        PermissionBuilder permission = null;
        for (PermissionBuilder itemPermission : permissions) {
            if (itemPermission.activity.equals(activity)
                    && itemPermission.requestCode == requestCode) {
                permission = itemPermission;
                break;
            }
        }
        if (permission != null) {
            permission.listener.onResult(hasPermission(permission.activity, permission.permission) ? true : false);
            permissions.remove(permission);
        }
    }

    public interface CallbackPermissionListener {
        void onResult(boolean success);
    }

    private static class PermissionBuilder {
        private final Activity activity;
        private final String permission;
        private final int requestCode;
        private final CharSequence messageReasonNeedPermission;
        private final CharSequence messageReject;
        private final CallbackPermissionListener listener;

        PermissionBuilder(Activity activity,
                          String permission,
                          int requestCode,
                          CharSequence messageReasonNeedPermission,
                          CharSequence messageReject,
                          CallbackPermissionListener listener) {
            this.activity = activity;
            this.permission = permission;
            this.requestCode = requestCode;
            this.messageReasonNeedPermission = messageReasonNeedPermission;
            this.messageReject = messageReject;
            this.listener = listener;
        }
    }
}
