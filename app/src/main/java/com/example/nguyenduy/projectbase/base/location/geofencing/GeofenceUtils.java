package com.example.nguyenduy.projectbase.base.location.geofencing;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;

import com.example.nguyenduy.projectbase.utils.LogUtils;
import com.example.nguyenduy.projectbase.utils.method.MethodUtils;
import com.example.nguyenduy.projectbase.utils.permission.BasePermission;
import com.example.nguyenduy.projectbase.utils.permission.PermissionUtils;
import com.google.android.gms.location.Geofence;
import com.google.android.gms.location.GeofencingClient;
import com.google.android.gms.location.GeofencingRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.List;

public class GeofenceUtils {

    private static final String TAG = MethodUtils.getTagClass(GeofenceUtils.class);

    private GeofencingClient mGeofencingClient;
    private Context mContext;

    public GeofenceUtils(Context context) {
        mContext = context;
        mGeofencingClient = LocationServices.getGeofencingClient(mContext);
    }

    /*timeExpire seconds*/
    public Geofence createGeofence(double latitude, double longitude, float radius, String requestId, long timeExpire) {
        return new Geofence.Builder()
                // Set the request ID of the geofence. This is a string to identify this geofence.
                .setRequestId(requestId)
                .setCircularRegion(latitude, longitude, radius)
                .setExpirationDuration(timeExpire * 1000)
                /*  int GEOFENCE_TRANSITION_ENTER = 1;
                    int GEOFENCE_TRANSITION_EXIT = 2;
                    int GEOFENCE_TRANSITION_DWELL = 4;
                    long NEVER_EXPIRE = -1L;*/
                .setTransitionTypes(Geofence.GEOFENCE_TRANSITION_ENTER | Geofence.GEOFENCE_TRANSITION_EXIT)
                .build();
    }

    private GeofencingRequest getGeofencingRequest(Geofence geofence) {
        GeofencingRequest.Builder builder = new GeofencingRequest.Builder();
        /*  int INITIAL_TRIGGER_ENTER = 1;
            int INITIAL_TRIGGER_EXIT = 2;
            int INITIAL_TRIGGER_DWELL = 4; - nghĩa là User phải ở trong location special 1 khoảng time thì mới kích hoạt event này, hoặc có thể đặt radius*/
        builder.setInitialTrigger(GeofencingRequest.INITIAL_TRIGGER_ENTER | GeofencingRequest.INITIAL_TRIGGER_EXIT);
        builder.addGeofence(geofence);
        return builder.build();
    }

    private PendingIntent mGeofencePendingIntent;

    private PendingIntent getGeofencePendingIntent() {
        // Reuse the PendingIntent if we already have it.
        if (mGeofencePendingIntent == null) {
            // We use FLAG_UPDATE_CURRENT so that we get the same pending intent back when calling addGeofences() and removeGeofences().
            mGeofencePendingIntent = PendingIntent.getService(
                    mContext,
                    0,
                    new Intent(mContext, GeofenceTransitionsIntentService.class),
                    PendingIntent.FLAG_UPDATE_CURRENT);
        }
        return mGeofencePendingIntent;
    }

    @SuppressLint("MissingPermission")
    private void addGeofence(final Activity activity, final Geofence geofence) {
        PermissionUtils.checkPermissionLocation(activity, new BasePermission.CallbackPermissionListener() {
            @Override
            public void onResult(boolean success, List<String> permissionDenieds) {
                if (success) {
                    mGeofencingClient.addGeofences(getGeofencingRequest(geofence), getGeofencePendingIntent())
                            .addOnSuccessListener(activity, new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    LogUtils.i(TAG + "addGeofence: onSuccess");
                                }
                            })
                            .addOnFailureListener(activity, new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    LogUtils.i(TAG + "addGeofence: onFailure");
                                }
                            });
                }
            }
        });
    }


}
