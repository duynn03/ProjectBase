package com.example.nguyenduy.projectbase.base.location.geofencing;

import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.location.GeofenceStatusCodes;

/**
 * Geofence error codes mapped to error messages.
 */
class GeofenceErrorMessages {
    /**
     * Returns the error string for a geofencing exception.
     */
    public static String getErrorString(Exception e) {
        if (e instanceof ApiException) {
            return getErrorString(((ApiException) e).getStatusCode());
        } else {
            return "Unknown error: the Geofence service is not available now";
        }
    }

    /**
     * Returns the error string for a geofencing error code.
     */
    public static String getErrorString(int errorCode) {
        switch (errorCode) {
            case GeofenceStatusCodes.GEOFENCE_NOT_AVAILABLE:
                return "Geofence service is not available now";
            case GeofenceStatusCodes.GEOFENCE_TOO_MANY_GEOFENCES:
                return "Your app has registered too many geofences";
            case GeofenceStatusCodes.GEOFENCE_TOO_MANY_PENDING_INTENTS:
                return "You have provided too many PendingIntents to the addGeofences() call";
            default:
                return "Unknown error: the Geofence service is not available now";
        }
    }
}