package com.example.nguyenduy.projectbase.base.broadcast.system;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import com.example.nguyenduy.projectbase.utils.LogUtils;
import com.example.nguyenduy.projectbase.utils.method.MethodUtils;

public class BootReceiver extends BroadcastReceiver {

    private static final String TAG = MethodUtils.getTagClass(BootReceiver.class);

    @Override
    public void onReceive(final Context context, final Intent intent) {
        // demo goAsync
        // https://developer.android.com/reference/android/content/BroadcastReceiver#goAsync()
        final PendingResult pendingResult = goAsync();
        @SuppressLint("StaticFieldLeak") AsyncTask<String, Integer, String> asyncTask = new AsyncTask<String, Integer, String>() {
            @Override
            protected String doInBackground(String... params) {
                StringBuilder sb = new StringBuilder();
                sb.append("Action: " + intent.getAction() + "\n");
                sb.append("URI: " + intent.toUri(Intent.URI_INTENT_SCHEME).toString() + "\n");
                String log = sb.toString();
                LogUtils.i(TAG + log);
                Toast.makeText(context, "BootReceiver: Complete, Start Service...", Toast.LENGTH_LONG).show();
                // Must call finish() so the BroadcastReceiver can be recycled.
                pendingResult.finish();
                // return data
                return log;
            }
        };
        asyncTask.execute();

        /*StringBuilder sb = new StringBuilder();
        sb.append("Action: " + intent.getAction() + "\n");
        sb.append("URI: " + intent.toUri(Intent.URI_INTENT_SCHEME).toString() + "\n");
        String log = sb.toString();
        LogUtils.i(TAG + log);
        Toast.makeText(context, "BootReceiver: Complete, Start Service...", Toast.LENGTH_LONG).show();*/
    }

}
