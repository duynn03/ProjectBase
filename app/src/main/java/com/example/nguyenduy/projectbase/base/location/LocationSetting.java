package com.example.nguyenduy.projectbase.base.location;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentSender;

import com.example.nguyenduy.projectbase.utils.LogUtils;
import com.example.nguyenduy.projectbase.utils.method.MethodUtils;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.LocationSettingsStates;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import static com.example.nguyenduy.projectbase.utils.Constants.IntentCommon.REQUEST_CHECK_SETTINGS_LOCATION;

// https://developer.android.com/training/location/change-location-settings
// https://developers.google.com/android/reference/com/google/android/gms/location/SettingsClient
public class LocationSetting {

    private static final String TAG = MethodUtils.getTagClass(LocationSetting.class);

    private static ILocationSettingListener mListener;

    public interface ILocationSettingListener {
        void onResult(boolean isSuccess);
    }

    // yêu cầu phải connected tới Google Play services and the location services API
    public static void isOpen(final Activity activity, final ILocationSettingListener listener, final LocationRequest... locationRequests) {

        mListener = listener;

        // create locationSettingRequest
        LocationSettingsRequest.Builder locationRequestBuilder = new LocationSettingsRequest.Builder();
        if (!MethodUtils.isEmpty(locationRequests)) {
            for (LocationRequest request : locationRequests) {
                locationRequestBuilder.addLocationRequest(request);
            }
        }

        // use BLE scans to derive location
        // https://developers.google.com/android/reference/com/google/android/gms/location/LocationSettingsRequest.Builder#setNeedBle(boolean)
        locationRequestBuilder.setNeedBle(true);

        LocationServices.getSettingsClient(activity).checkLocationSettings(locationRequestBuilder.build())
                .addOnCompleteListener(new OnCompleteListener<LocationSettingsResponse>() {
                    @Override
                    public void onComplete(Task<LocationSettingsResponse> task) {
                        try {
                            LocationSettingsResponse response = task.getResult(ApiException.class);
                            // All location settings are satisfied. The client can initialize location requests here.
                            mListener.onResult(true);
                        } catch (ApiException exception) {
                            switch (exception.getStatusCode()) {
                                case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                                    // Location settings are not satisfied. But could be fixed by showing the user a dialog.
                                    try {
                                        // Cast to a resolvable exception.
                                        ResolvableApiException resolvable = (ResolvableApiException) exception;
                                        // Show the dialog by calling startResolutionForResult(), and check the result in onActivityResult().
                                        resolvable.startResolutionForResult(activity, REQUEST_CHECK_SETTINGS_LOCATION);
                                    } catch (IntentSender.SendIntentException e) {
                                        // Ignore the error.
                                    } catch (ClassCastException e) {
                                        // Ignore, should be an impossible error.
                                    }
                                    break;
                                case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                                    // Location settings are not satisfied. However, we have no way to fix the settings so we won't show the dialog.
                                    LogUtils.e(TAG + "we have no way to fix the settings so we won't show the dialog");
                                    break;
                            }
                        }
                    }
                });
    }

    public static void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CHECK_SETTINGS_LOCATION) {
            // get location provider available
            final LocationSettingsStates states = LocationSettingsStates.fromIntent(data);
            if (resultCode == Activity.RESULT_OK) {
                // All required changes were successfully made
                mListener.onResult(true);
            } else {
                // resultCode == Activity.RESULT_CANCELED:
                // The user was asked to change settings, but chose not to
                mListener.onResult(false);
            }
        }
    }

}
