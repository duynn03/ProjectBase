package com.example.nguyenduy.projectbase.base.location;

import android.app.IntentService;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.support.annotation.Nullable;

import com.example.nguyenduy.projectbase.base.location.convertLocation.ConvertLocationConstants;
import com.example.nguyenduy.projectbase.utils.LogUtils;
import com.example.nguyenduy.projectbase.utils.method.MethodUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static com.example.nguyenduy.projectbase.base.location.convertLocation.ConvertLocationConstants.DATA_LOCATION;
import static com.example.nguyenduy.projectbase.base.location.convertLocation.ConvertLocationConstants.DATA_NAME_ADDRESS;
import static com.example.nguyenduy.projectbase.base.location.convertLocation.ConvertLocationConstants.NUMBER_ADDRESS;
import static com.example.nguyenduy.projectbase.base.location.convertLocation.ConvertLocationConstants.RESULT_DATA;
import static com.example.nguyenduy.projectbase.base.location.convertLocation.ConvertLocationConstants.RESULT_RECEIVER;
import static com.example.nguyenduy.projectbase.base.location.convertLocation.ConvertLocationConstants.Result.INVALID_LAT_LONG_USED;
import static com.example.nguyenduy.projectbase.base.location.convertLocation.ConvertLocationConstants.Result.NOT_FOUND;
import static com.example.nguyenduy.projectbase.base.location.convertLocation.ConvertLocationConstants.Result.SERVICE_NOT_AVAILABLE;
import static com.example.nguyenduy.projectbase.base.location.convertLocation.ConvertLocationConstants.Result.SUCCESS;
import static com.example.nguyenduy.projectbase.base.location.convertLocation.ConvertLocationConstants.TYPE_CONVERT;
import static com.example.nguyenduy.projectbase.base.location.convertLocation.ConvertLocationConstants.TypeConvert.LOCATION_ADDRESS;
import static com.example.nguyenduy.projectbase.base.location.convertLocation.ConvertLocationConstants.TypeConvert.NAME_ADDRESS;

public class FetchAddressIntentService extends IntentService {

    private static final String TAG = MethodUtils.getTagClass(FetchAddressIntentService.class);

    public FetchAddressIntentService() {
        super(TAG);
    }

    private ResultReceiver mResultReceiver;

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        if (intent == null) {
            return;
        }

        int numberAddress = intent.getIntExtra(NUMBER_ADDRESS, 1);
        int typeConvert = intent.getIntExtra(TYPE_CONVERT, LOCATION_ADDRESS);
        mResultReceiver = intent.getParcelableExtra(RESULT_RECEIVER);
        Location location = intent.getParcelableExtra(DATA_LOCATION);
        String nameAddress = intent.getStringExtra(DATA_NAME_ADDRESS);

        List<Address> addresses = new ArrayList<>();
        Geocoder geocoder = new Geocoder(this, Locale.getDefault());

        try {
            addresses = LOCATION_ADDRESS == typeConvert ?
                    geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), numberAddress) :
                    geocoder.getFromLocationName(nameAddress, numberAddress);
            if (MethodUtils.isEmpty(addresses)) {
                LogUtils.e(TAG + ConvertLocationConstants.MessageErrorLog.NO_ADDRESS_FOUND);
                deliverResultToReceiver(NOT_FOUND, addresses);
            } else {
                LogUtils.i(TAG + ConvertLocationConstants.MessageErrorLog.ADDRESS_FOUND);
                deliverResultToReceiver(SUCCESS, addresses);
            }
        } catch (IOException exception) {
            // Catch network or other I/O problems.
            LogUtils.e(TAG + ConvertLocationConstants.MessageErrorLog.SERVICE_NOT_AVAILABLE + ": " + exception.getMessage());
            deliverResultToReceiver(SERVICE_NOT_AVAILABLE, addresses);
        } catch (IllegalArgumentException illegalArgumentException) {
            // invalid latitude or longitude values.
            LogUtils.e(TAG + ConvertLocationConstants.MessageErrorLog.INVALID_LAT_LONG_USED + ": " +
                    "Latitude = " + location.getLatitude() + ", Longitude = " + location.getLongitude() + ", Exception: " +
                    illegalArgumentException.getMessage());
            deliverResultToReceiver(INVALID_LAT_LONG_USED, addresses);
        }
    }

    /*send result to activity*/
    private void deliverResultToReceiver(int resultCode, List<Address> addresses) {
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList(RESULT_DATA, new ArrayList<>(addresses));
        mResultReceiver.send(resultCode, bundle);
    }

}
