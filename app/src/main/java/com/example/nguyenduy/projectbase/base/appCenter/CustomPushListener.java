package com.example.nguyenduy.projectbase.base.appCenter;

import android.app.Activity;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

import com.example.nguyenduy.projectbase.utils.LogUtils;
import com.microsoft.appcenter.push.PushListener;
import com.microsoft.appcenter.push.PushNotification;

import java.util.Map;

public class CustomPushListener implements PushListener {

    @Override
    public void onPushNotificationReceived(Activity activity, PushNotification pushNotification) {
        LogUtils.e("Nhận được push notification");
        /* The following notification properties are available. */
        String title = pushNotification.getTitle();
        String message = pushNotification.getMessage();
        Map<String, String> customData = pushNotification.getCustomData();

        /*
         * Message and title cannot be read from a background notification object.
         * Message being a mandatory field, you can use that to check foreground vs background.
         */
        if (!isApplicationForeground(message)) {
            /* Display an alert for foreground push. */
            AlertDialog.Builder dialog = new AlertDialog.Builder(activity);
            if (title != null) {
                dialog.setTitle(title);
            }
            dialog.setMessage(message);
            if (!customData.isEmpty()) {
                dialog.setMessage(message + "\n" + customData);
            }
            dialog.setPositiveButton(android.R.string.ok, null);
            dialog.show();
        } else {

            /* Display a toast when a background push is clicked. */
            Toast.makeText(activity, String.format("Push clicked with data=%1s", customData), Toast.LENGTH_LONG).show();
        }
    }

    private boolean isApplicationForeground(String message) {
        return message == null;
    }
}

