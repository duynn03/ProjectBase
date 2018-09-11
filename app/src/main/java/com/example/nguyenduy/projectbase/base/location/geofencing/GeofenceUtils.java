package com.example.nguyenduy.projectbase.base.location.geofencing;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.widget.Toast;

import com.example.nguyenduy.projectbase.base.location.LocationSetting;
import com.example.nguyenduy.projectbase.base.location.LocationUtils;
import com.example.nguyenduy.projectbase.utils.LogUtils;
import com.example.nguyenduy.projectbase.utils.method.MethodUtils;
import com.example.nguyenduy.projectbase.utils.method.SystemUtils;
import com.google.android.gms.location.Geofence;
import com.google.android.gms.location.GeofencingClient;
import com.google.android.gms.location.GeofencingEvent;
import com.google.android.gms.location.GeofencingRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

public class GeofenceUtils {

    private static final String TAG = MethodUtils.getTagClass(GeofenceUtils.class);

    private Activity mActivity;
    private IGeofenceListener mListener;
    private GeofencingClient client;
    private PendingIntent intent;

    public GeofenceUtils(Activity activity) {
        mActivity = activity;
        client = LocationServices.getGeofencingClient(mActivity);
    }

    /*addItem tối đa 100 geofence*/
    public Geofence createGeofence(double latitude, double longitude, String requestId) {
        return new Geofence.Builder()
                // Set the request ID of the geofence. This is a string to identify this geofence.
                .setRequestId(requestId)
                // sử dụng radius lớn khi xác định home, cơ quan,...
                .setCircularRegion(latitude, longitude, GeofenceConstans.GEOFENCE_RADIUS_IN_METERS)
                .setExpirationDuration(GeofenceConstans.GEOFENCE_EXPIRATION_IN_MILLISECONDS)
                /*  int GEOFENCE_TRANSITION_ENTER = 1;
                    int GEOFENCE_TRANSITION_EXIT = 2;
                    int GEOFENCE_TRANSITION_DWELL = 4;
                    long NEVER_EXPIRE = -1L;*/
                .setTransitionTypes(Geofence.GEOFENCE_TRANSITION_ENTER | Geofence.GEOFENCE_TRANSITION_EXIT)
                // for transitionType INITIAL_TRIGGER_DWELL
                //.setLoiteringDelay(GeofenceConstans.GEOFENCE_LOITERING_DELAY_IN_MILLISECONDS)
                // nhận được notification enter, exit sau mỗi 5p
                // default là 0. khi setting thì trong method Android có thể điều chỉnh lại để phù hợp với pin
                //.setNotificationResponsiveness(GeofenceConstans.GEOFENCE_NOTIFICATION_RESPONSIVENESS_IN_MILLISECONDS)
                .build();
    }

    private GeofencingRequest getRequest(List<Geofence> geofences) {
        GeofencingRequest.Builder builder = new GeofencingRequest.Builder();
        /*  int INITIAL_TRIGGER_ENTER = 1;
            int INITIAL_TRIGGER_EXIT = 2;
            int INITIAL_TRIGGER_DWELL = 4; - nghĩa là User phải ở trong location special 1 khoảng time thì mới kích hoạt event này, hoặc có thể đặt radius
            Nếu sử dụng flag INITIAL_TRIGGER_DWELL thì không sử dụng INITIAL_TRIGGER_ENTER nữa
            */
        builder.setInitialTrigger(GeofencingRequest.INITIAL_TRIGGER_ENTER | GeofencingRequest.INITIAL_TRIGGER_EXIT);
        builder.addGeofences(geofences);
        return builder.build();
    }

    private PendingIntent getIntent() {
        // Reuse the PendingIntent if we already have it.
        if (intent == null) {
            // We use FLAG_UPDATE_CURRENT so that we get the same pending intent back when calling addGeofences() and removeGeofences().
            intent = PendingIntent.getService(
                    mActivity,
                    0,
                    new Intent(mActivity, GeofenceTransitionsIntentService.class),
                    PendingIntent.FLAG_UPDATE_CURRENT);
        }
        return intent;
    }

    public interface IGeofenceListener {
        void onResult(int typeTransition, List<Geofence> triggeringGeofences);
    }

    @SuppressLint("MissingPermission")
    public void register(final List<Geofence> geofences, final IGeofenceListener listener) {
        LocationUtils.isOpenSettingLocation(mActivity, new LocationSetting.ILocationSettingListener() {
            @Override
            public void onResult(boolean isSuccess) {
                if (isSuccess) {
                    if (!SystemUtils.isNetworkOnline()) {
                        LogUtils.i(TAG + "register: turn off Wifi when register");
                        Toast.makeText(mActivity, "Hãy bật wifi để tăng độ chính xác cho Geofence", Toast.LENGTH_SHORT).show();
                    }
                    client.addGeofences(getRequest(geofences), getIntent())
                            .addOnSuccessListener(mActivity, new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    mListener = listener;
                                    EventBus.getDefault().register(GeofenceUtils.this);
                                    LogUtils.i(TAG + "addGeofence: onSuccess");
                                }
                            })
                            .addOnFailureListener(mActivity, new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    LogUtils.e(TAG + "addGeofence: onFailure: " + GeofenceErrorMessages.getErrorString(e));
                                    Toast.makeText(mActivity, "addGeofence: onFailure, Exception: " + GeofenceErrorMessages.getErrorString(e), Toast.LENGTH_SHORT).show();
                                }
                            });

                }
            }
        });
    }

    // event bus từ sevice GeofenceTransitionsIntentService.java
    @Subscribe
    public void onResultChangeGeofenceListener(GeofencingEvent geofencingEvent) {
        // Get the transition type (entered or exited).
        // Get the geofences that were triggered. A single event can trigger multiple geofences.
        if (null != mListener)
            mListener.onResult(geofencingEvent.getGeofenceTransition(), geofencingEvent.getTriggeringGeofences());
    }

    public void unregister() {
        client.removeGeofences(getIntent())
                .addOnSuccessListener(mActivity, new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        EventBus.getDefault().unregister(GeofenceUtils.this);
                        LogUtils.i(TAG + "removeGeofence: onSuccess");
                    }
                })
                .addOnFailureListener(mActivity, new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        LogUtils.e(TAG + "removeGeofence: onFailure, Exception: " + GeofenceErrorMessages.getErrorString(e));
                        Toast.makeText(mActivity, "removeGeofences: onFailure, Exception: " + GeofenceErrorMessages.getErrorString(e), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    /**
     * Maps geofence transition types to their human-readable equivalents.
     *
     * @param transitionType A transition type constant defined in Geofence
     * @return A String indicating the type of transition
     */
    public static String getTransitionString(int transitionType) {
        switch (transitionType) {
            case Geofence.GEOFENCE_TRANSITION_ENTER:
                return "Entered";
            case Geofence.GEOFENCE_TRANSITION_EXIT:
                return "Exited";
            default:
                return "Unknown Transition";
        }
    }

}
