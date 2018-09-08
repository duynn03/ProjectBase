package com.example.nguyenduy.projectbase.base.location.geofencing;

import android.app.IntentService;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;

import com.example.nguyenduy.projectbase.R;
import com.example.nguyenduy.projectbase.screen.main.MainActivity;
import com.example.nguyenduy.projectbase.screen.main.location.geofence.GeofenceFragment;
import com.example.nguyenduy.projectbase.utils.LogUtils;
import com.example.nguyenduy.projectbase.utils.method.MethodUtils;
import com.google.android.gms.location.Geofence;
import com.google.android.gms.location.GeofencingEvent;

import org.greenrobot.eventbus.EventBus;

public class GeofenceTransitionsIntentService extends IntentService {

    private static final String TAG = MethodUtils.getTagClass(GeofenceTransitionsIntentService.class);

    public GeofenceTransitionsIntentService() {
        super(TAG);
    }

    /**
     * Handles incoming intents.
     *
     * @param intent sent by Location Services. This Intent is provided to Location Services (inside a PendingIntent) when addGeofences() is called.
     */
    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        GeofencingEvent geofencingEvent = GeofencingEvent.fromIntent(intent);
        if (geofencingEvent.hasError()) {
            LogUtils.e(TAG + GeofenceErrorMessages.getErrorString(geofencingEvent.getErrorCode()));
            return;
        }

        // Get the transition type.
        int typeTransition = geofencingEvent.getGeofenceTransition();
        // Test that the reported transition was of interest.
        if (typeTransition == Geofence.GEOFENCE_TRANSITION_ENTER || typeTransition == Geofence.GEOFENCE_TRANSITION_EXIT) {
            EventBus.getDefault().post(geofencingEvent);
            // Send notification transition details.
            sendNotification(GeofenceFragment.getGeofenceTransitionDetails(typeTransition, geofencingEvent.getTriggeringGeofences()));
        } else {
            // Log the error.
            LogUtils.e(TAG + "Geofence transition error: invalid transition type: " + typeTransition);
        }
    }

    /**
     * Posts a notification in the notification bar when a transition is detected.
     * If the user clicks the notification, control goes to the MainActivity.
     */
    private void sendNotification(String notificationDetails) {
        // Get an instance of the Notification manager
        NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        // Android O requires a Notification Channel.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = getString(R.string.app_name_production);
            // Create the channel for the notification
            NotificationChannel mChannel = new NotificationChannel("channel_01", name, NotificationManager.IMPORTANCE_DEFAULT);

            // Set the Notification Channel for the Notification Manager.
            mNotificationManager.createNotificationChannel(mChannel);
        }

        // Create an explicit content Intent that starts the main Activity.
        Intent notificationIntent = new Intent(getApplicationContext(), MainActivity.class);

        // Construct a task stack.
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);

        // Add the main Activity to the task stack as the parent.
        stackBuilder.addParentStack(MainActivity.class);

        // Push the content Intent onto the stack.
        stackBuilder.addNextIntent(notificationIntent);

        // Get a PendingIntent containing the entire back stack.
        PendingIntent notificationPendingIntent =
                stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);

        // Get a notification builder that's compatible with platform versions >= 4
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);

        // Define the notification settings.
        builder.setSmallIcon(R.drawable.ic_menu_camera)
                // In a real app, you may want to use a library like Volley
                // to decode the Bitmap.
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.ic_menu_camera))
                .setColor(Color.RED)
                .setContentTitle(notificationDetails)
                .setContentText("Click notification to return to app")
                .setContentIntent(notificationPendingIntent);

        // Set the Channel ID for Android O.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            builder.setChannelId("channel_01"); // Channel ID
        }

        // Dismiss notification once the user touches it.
        builder.setAutoCancel(true);

        // Issue the notification
        mNotificationManager.notify(0, builder.build());
    }

}
