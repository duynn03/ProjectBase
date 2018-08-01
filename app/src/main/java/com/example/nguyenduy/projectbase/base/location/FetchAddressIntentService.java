package com.example.nguyenduy.projectbase.base.location;


import android.app.IntentService;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.example.nguyenduy.projectbase.R;
import com.example.nguyenduy.projectbase.utils.LogUtils;
import com.example.nguyenduy.projectbase.utils.method.MethodUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class FetchAddressIntentService extends IntentService {

    private static final String TAG = MethodUtils.getTagClass(FetchAddressIntentService.class);
    private static final int NUMBER_ADDRESS = 1;


    public static final int SUCCESS_RESULT = 0;
    public static final int FAILURE_RESULT = 1;
    public static final String PACKAGE_NAME =
            "com.google.android.gms.location.sample.locationaddress";
    public static final String RECEIVER = PACKAGE_NAME + ".RECEIVER";
    public static final String RESULT_DATA_KEY = PACKAGE_NAME +
            ".RESULT_DATA_KEY";
    public static final String LOCATION_DATA_EXTRA = PACKAGE_NAME +
            ".LOCATION_DATA_EXTRA";

    public FetchAddressIntentService() {
        super(TAG);
    }


    // return geocoder  = null nếu backend geocoding not available
    // return list empty nếu không có address nào mapping với latitude và longitude nhập vào
    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        if (intent == null) {
            return;
        }

    /*    Geocoder geocoder = new Geocoder(this, Locale.getDefault());
        String errorMessage = "";
        List<Address> addresses = null;
        // Get the location passed to this service through an extra.
        Location location = intent.getParcelableExtra(LOCATION_DATA_EXTRA);
        // ...

        try {
            // chỉ get single address
            addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), NUMBER_ADDRESS);
        } catch (IOException exception) {
            // Catch network or other I/O problems.
            errorMessage = getString(R.string.service_not_available);
            LogUtils.e(TAG + errorMessage + exception.getMessage());
        } catch (IllegalArgumentException illegalArgumentException) {
            // invalid latitude or longitude values.
            errorMessage = getString(R.string.invalid_lat_long_used);
            LogUtils.e(TAG + errorMessage + ". " +
                    "Latitude = " + location.getLatitude() + ", Longitude = " + location.getLongitude() +
                    illegalArgumentException.getMessage());
        }

        // Handle case where no address was found.
        if (addresses == null || addresses.size() == 0) {
            if (errorMessage.isEmpty()) {
                errorMessage = getString(R.string.no_address_found);
                LogUtils.e(TAG + errorMessage);
            }
            deliverResultToReceiver(FAILURE_RESULT, errorMessage);
        } else {
            Address address = addresses.get(0);
            ArrayList<String> addressFragments = new ArrayList<>();

            // Fetch the address lines using getAddressLine,
            // join them, and send them to the thread.
            for (int i = 0; i <= address.getMaxAddressLineIndex(); i++) {
                addressFragments.add(address.getAddressLine(i));
            }
            LogUtils.i(TAG + getString(R.string.address_found));
            deliverResultToReceiver(SUCCESS_RESULT, TextUtils.join(System.getProperty("line.separator"), addressFragments));
        }*/
    }

    protected ResultReceiver mReceiver;

    private void deliverResultToReceiver(int resultCode, String message) {
        Bundle bundle = new Bundle();
        bundle.putString(RESULT_DATA_KEY, message);
        mReceiver.send(resultCode, bundle);
    }


}
