package com.example.nguyenduy.projectbase.base.location.detectActivityUser;

import android.app.IntentService;
import android.content.Intent;

import com.example.nguyenduy.projectbase.utils.LogUtils;
import com.example.nguyenduy.projectbase.utils.method.MethodUtils;
import com.google.android.gms.location.ActivityRecognitionResult;
import com.google.android.gms.location.DetectedActivity;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

/**
 * BackgroundService for handling incoming intents that are generated as a result of requesting
 * activity updates using
 * {@link com.google.android.gms.location.ActivityRecognitionApi#requestActivityUpdates}.
 */
public class DetectedActivitiesIntentService extends IntentService {

    protected static final String TAG = MethodUtils.getTagClass(DetectedActivitiesIntentService.class);

    public DetectedActivitiesIntentService() {
        super(TAG);
    }

    /**
     * Handles incoming intents.
     *
     * @param intent The Intent is provided (inside a PendingIntent) when requestActivityUpdates()
     *               is called.
     */
    @SuppressWarnings("unchecked")
    @Override
    protected void onHandleIntent(Intent intent) {
        ActivityRecognitionResult result = ActivityRecognitionResult.extractResult(intent);

        // Get the list of the probable activities associated with the current state of the device. Each activity is associated with a confidence level, which is an int between 0 and 100.
        List<DetectedActivity> activities = result.getProbableActivities();
        EventBus.getDefault().post(activities);
    }
}
