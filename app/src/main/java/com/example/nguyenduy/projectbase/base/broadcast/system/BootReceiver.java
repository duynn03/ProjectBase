package com.example.nguyenduy.projectbase.base.broadcast.system;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.example.nguyenduy.projectbase.utils.LogUtils;
import com.example.nguyenduy.projectbase.utils.method.MethodUtils;

public class BootReceiver extends BroadcastReceiver {

    private static final String TAG = MethodUtils.getTagClass(BootReceiver.class);

    @Override
    public void onReceive(Context context, Intent intent) {
        StringBuilder sb = new StringBuilder();
        sb.append("Action: " + intent.getAction() + "\n");
        sb.append("URI: " + intent.toUri(Intent.URI_INTENT_SCHEME).toString() + "\n");
        String log = sb.toString();
        LogUtils.i(TAG + log);
        Toast.makeText(context, "BootReceiver: Complete, Start Service...", Toast.LENGTH_LONG).show();
    }

}
