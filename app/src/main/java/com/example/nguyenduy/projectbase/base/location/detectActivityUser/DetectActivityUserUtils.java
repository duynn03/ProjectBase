package com.example.nguyenduy.projectbase.base.location.detectActivityUser;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.example.nguyenduy.projectbase.utils.LogUtils;
import com.example.nguyenduy.projectbase.utils.method.MethodUtils;
import com.google.android.gms.location.ActivityRecognitionClient;
import com.google.android.gms.location.ActivityTransition;
import com.google.android.gms.location.DetectedActivity;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

public class DetectActivityUserUtils {

    private static final String TAG = MethodUtils.getTagClass(DetectActivityUserUtils.class);

    private Activity mActivity;
    private IDetectActivityUserListener mListener;
    private ActivityRecognitionClient client;
    private PendingIntent intent;

    public DetectActivityUserUtils(Activity activity) {
        mActivity = activity;
        client = new ActivityRecognitionClient(mActivity);
    }

    // DetectedActivity.IN_VEHICLE
    // ActivityTransition.ACTIVITY_TRANSITION_ENTER
    // https://developers.google.com/android/reference/com/google/android/gms/location/DetectedActivity
    public ActivityTransition createTransition(int typeAcitivity, int typeTransition) {
        return new ActivityTransition.Builder()
                .setActivityType(typeAcitivity)
                .setActivityTransition(typeTransition)
                .build();
    }

    private PendingIntent getIntent() {
        // Reuse the PendingIntent if we already have it.
        if (intent == null) {
            // We use FLAG_UPDATE_CURRENT so that we get the same pending intent back when calling requestActivityUpdates() and removeActivityUpdates().
            intent = PendingIntent.getService(
                    mActivity,
                    0,
                    new Intent(mActivity, DetectedActivitiesIntentService.class),
                    PendingIntent.FLAG_UPDATE_CURRENT);
        }
        return intent;
    }

    public interface IDetectActivityUserListener {
        void onResult(List<DetectedActivity> activities);
    }

    public void register(/*List<ActivityTransition> transitions, */final IDetectActivityUserListener listener) {
        /*client.requestActivityTransitionUpdates(
                new ActivityTransitionRequest(transitions), getIntent())*/
        client.requestActivityUpdates(DetectActivityUserConstants.DETECTION_INTERVAL_IN_MILLISECONDS, getIntent())
                .addOnSuccessListener(
                        new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void result) {
                                mListener = listener;
                                EventBus.getDefault().register(DetectActivityUserUtils.this);
                                LogUtils.i(TAG + "requestActivityTransitionUpdates: success");
                            }
                        }
                )
                .addOnFailureListener(
                        new OnFailureListener() {
                            @Override
                            public void onFailure(Exception e) {
                                Toast.makeText(mActivity, "requestActivityTransitionUpdates: onFailure, Exception: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                                LogUtils.i(TAG + "requestActivityTransitionUpdates: onFailure, Exception: " + e.getMessage());
                            }
                        }
                );
    }

    // event bus từ sevice DetectedActivitiesIntentService.java
    @Subscribe
    public void onResultDetectActivityUserListener(List<DetectedActivity> activities) {
        if (null != mListener)
            mListener.onResult(activities);
    }

    public void unregister() {
        client.removeActivityTransitionUpdates(getIntent())
                .addOnSuccessListener(mActivity, new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        intent.cancel();
                        EventBus.getDefault().unregister(DetectActivityUserUtils.this);
                        LogUtils.i(TAG + "removeActivityTransitionUpdates: onSuccess");
                    }
                })
                .addOnFailureListener(mActivity, new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        LogUtils.e(TAG + "removeActivityTransitionUpdates: onFailure, Exception: " + e.getMessage());
                        Toast.makeText(mActivity, "removeActivityTransitionUpdates: onFailure, Exception: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    public static String getActivityString(int detectedActivityType) {
        switch (detectedActivityType) {
            case DetectedActivity.IN_VEHICLE:
                return "In a vehicle";
            case DetectedActivity.ON_BICYCLE:
                return "On a bicycle";
            case DetectedActivity.ON_FOOT:
                return "On foot";
            case DetectedActivity.RUNNING:
                return "Running";
            case DetectedActivity.STILL:
                return "Still";
            case DetectedActivity.TILTING:
                return "Tilting";
            case DetectedActivity.UNKNOWN:
                return "Unknown activity";
            case DetectedActivity.WALKING:
                return "Walking";
            default:
                return "Unidentifiable activity: " + detectedActivityType;
        }
    }

}
