package com.example.nguyenduy.projectbase.utils.permission;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.text.TextUtils;

import com.example.nguyenduy.projectbase.R;
import com.example.nguyenduy.projectbase.utils.method.MethodUtils;

import java.util.ArrayList;
import java.util.List;

public class BasePermission {

    private static PermissionBuilder mBuilder;
    private static final int REQUEST_CODE_PERMISSION = 12345;

    // Permission for fragment
    //https://stackoverflow.com/questions/32714787/android-m-permissions-onrequestpermissionsresult-not-being-called
    public static void checkPermission(PermissionBuilder permissionBuilder) {
        mBuilder = permissionBuilder;
        if (!hasPermissionNeedCheck()) {
            mBuilder.listener.onResult(true, null);
            return;
        }
        // shouldShowRequestPermissionRationale return về true nếu user đã denied request trước đó
        boolean shouldShowReason = shouldShowReasonNeedPermission();
        if (shouldShowReason && !TextUtils.isEmpty(mBuilder.messageReason)) {
            showReasonNeedPermission();
        } else {
            requestPermission();
        }
    }

    private static boolean hasPermissionNeedCheck() {
        for (String permission : mBuilder.permissions) {
            if (ActivityCompat.checkSelfPermission(mBuilder.activity, permission) == PackageManager.PERMISSION_DENIED)
                return true;
        }
        return false;
    }

    private static boolean shouldShowReasonNeedPermission() {
        for (String permission : mBuilder.permissions) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(mBuilder.activity, permission))
                return true;
        }
        return false;

    }

    private static void showReasonNeedPermission() {
        new AlertDialog.Builder(mBuilder.activity)
                .setCancelable(false)
                .setMessage(mBuilder.messageReason)
                .setPositiveButton(R.string.OK, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        requestPermission();
                    }
                })
                .show();
    }

    private static void requestPermission() {
        ActivityCompat.requestPermissions(mBuilder.activity, mBuilder.permissions, REQUEST_CODE_PERMISSION);
    }

    private static void showMessageRejected(final List<String> permissionDenieds) {
        new AlertDialog.Builder(mBuilder.activity)
                .setCancelable(false)
                .setMessage(mBuilder.messageReject)
                .setPositiveButton(R.string.OK, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mBuilder.listener.onResult(false, permissionDenieds);
                    }
                })
                .setNegativeButton(R.string.change_setting, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        openAppDetailSetting();
                    }
                })
                .show();
    }

    private static void openAppDetailSetting() {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.setData(Uri.fromParts("package", mBuilder.activity.getPackageName(), null));
        mBuilder.activity.startActivityForResult(intent, REQUEST_CODE_PERMISSION);
    }

    public static void onRequestPermissionResult(int requestCode,
                                                 String[] permissions,
                                                 int[] grantResults) {
        if (REQUEST_CODE_PERMISSION != requestCode) return;

        List<String> permissionDenieds = findPermissionDenied(permissions, grantResults);
        // if not permission denied
        if (MethodUtils.isEmpty(permissionDenieds)) {
            mBuilder.listener.onResult(true, null);
        } else {
            if (TextUtils.isEmpty(mBuilder.messageReject)) {
                mBuilder.listener.onResult(false, permissionDenieds);
            } else {
                showMessageRejected(permissionDenieds);
            }
        }
    }

    private static List<String> findPermissionDenied(String[] permissions, int[] grantResults) {
        List<String> permissionDenieds = new ArrayList<>();
        for (int i = 0; i < grantResults.length; i++) {
            if (grantResults[i] == PackageManager.PERMISSION_DENIED)
                permissionDenieds.add(permissions[i]);
        }
        return permissionDenieds;
    }

    public interface CallbackPermissionListener {
        void onResult(boolean success, List<String> permissionDenieds);
    }

    public static class PermissionBuilder {
        private final Activity activity;
        private final String[] permissions;
        private String messageReason;
        private String messageReject;
        private final CallbackPermissionListener listener;

        PermissionBuilder(Activity activity,
                          CallbackPermissionListener listener,
                          String... permissions) {
            this.activity = activity;
            this.listener = listener;
            this.permissions = permissions;
        }

        public PermissionBuilder setReason(String messageReason) {
            this.messageReason = messageReason;
            return this;
        }

        public PermissionBuilder setMessageReject(String messageReject) {
            this.messageReject = messageReject;
            return this;
        }
    }
}
