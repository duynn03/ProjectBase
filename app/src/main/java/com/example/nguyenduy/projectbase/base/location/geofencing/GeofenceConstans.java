package com.example.nguyenduy.projectbase.base.location.geofencing;

public class GeofenceConstans {
    // 12h
    public static final long GEOFENCE_EXPIRATION_IN_MILLISECONDS = 12 * 60 * 60 * 1000;
    // tối ưu 3G: khoảng 100m --> 150m, Wifi: 20-50m, home: 5m
    public static final float GEOFENCE_RADIUS_IN_METERS = 150;
    // Notification Responsive 1p
    public static final int GEOFENCE_NOTIFICATION_RESPONSIVENESS_IN_MILLISECONDS = 60 * 1000;
    // enterd 1p
    public static final int GEOFENCE_LOITERING_DELAY_IN_MILLISECONDS = 60 * 1000;
}
