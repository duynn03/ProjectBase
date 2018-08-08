package com.example.nguyenduy.projectbase.base.broadcast.custom.permission;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import com.example.nguyenduy.projectbase.utils.permission.BasePermission;
import com.example.nguyenduy.projectbase.utils.permission.PermissionUtils;

import java.util.List;

public class PermissionBroadcastUtils {

    private PermissionReceiver mReceiver;
    private Context mContext;
    private Activity mActivity;

    public PermissionBroadcastUtils(Activity activity) {
        mActivity = activity;
        mContext = activity;
    }

    public void sendBroadcast(final String nameBroadcast, final String data) {
        PermissionUtils.checkPermissionSMS(mActivity, new BasePermission.CallbackPermissionListener() {
            @Override
            public void onResult(boolean isSuccess, List<String> permissionDenieds) {
                if (isSuccess) {
                    mContext.sendBroadcast(createIntentSender(nameBroadcast, data), Manifest.permission.SEND_SMS);
                }
            }
        });
    }

    private Intent createIntentSender(String nameBroadcast, String data) {
        Intent intentSender = new Intent(nameBroadcast);
        intentSender.putExtra("data", data);
        return intentSender;
    }

    public void register(final String nameBroadcast, final PermissionReceiver.IChangePermissionListener listener) {
        PermissionUtils.checkPermissionSMS(mActivity, new BasePermission.CallbackPermissionListener() {
            @Override
            public void onResult(boolean isSuccess, List<String> permissionDenieds) {
                if (isSuccess) {
                    mReceiver = new PermissionReceiver();
                    mReceiver.setListenerPermissionChange(listener);
                    mContext.registerReceiver(mReceiver, createIntentReceiver(nameBroadcast), Manifest.permission.SEND_SMS, null);
                }
            }
        });
    }

    private IntentFilter createIntentReceiver(String nameBroadcast) {
        return new IntentFilter(nameBroadcast);
    }

    public void unregister() {
        mContext.unregisterReceiver(mReceiver);
    }

}
