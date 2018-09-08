package com.example.nguyenduy.projectbase.base.location.convertLocation;

import android.app.Activity;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;
import android.text.TextUtils;
import android.widget.Toast;

import com.example.nguyenduy.projectbase.base.location.LocationSetting;
import com.example.nguyenduy.projectbase.base.location.LocationUtils;
import com.example.nguyenduy.projectbase.utils.method.MethodUtils;
import com.example.nguyenduy.projectbase.utils.method.SystemUtils;

import java.util.ArrayList;
import java.util.List;

import static com.example.nguyenduy.projectbase.base.location.convertLocation.ConvertLocationConstants.DATA_LOCATION;
import static com.example.nguyenduy.projectbase.base.location.convertLocation.ConvertLocationConstants.DATA_NAME_ADDRESS;
import static com.example.nguyenduy.projectbase.base.location.convertLocation.ConvertLocationConstants.NUMBER_ADDRESS;
import static com.example.nguyenduy.projectbase.base.location.convertLocation.ConvertLocationConstants.RESULT_DATA;
import static com.example.nguyenduy.projectbase.base.location.convertLocation.ConvertLocationConstants.RESULT_RECEIVER;
import static com.example.nguyenduy.projectbase.base.location.convertLocation.ConvertLocationConstants.Result.SUCCESS;
import static com.example.nguyenduy.projectbase.base.location.convertLocation.ConvertLocationConstants.TYPE_CONVERT;
import static com.example.nguyenduy.projectbase.base.location.convertLocation.ConvertLocationConstants.TypeConvert.LOCATION_ADDRESS;
import static com.example.nguyenduy.projectbase.base.location.convertLocation.ConvertLocationConstants.TypeConvert.NAME_ADDRESS;

public class ConvertLocationUtils {

    private Activity mActivity;

    private NameAddressResultReceiver mResultReceiverNameAddress;
    private IConvertLocationListener mListenerLocation;

    private LocationResultReceiver mResultReceiverLocation;
    private IConvertNameAddressListener mListenerNameAddress;

    public ConvertLocationUtils(Activity context) {
        mActivity = context;
        mResultReceiverNameAddress = new NameAddressResultReceiver(new Handler());
        mResultReceiverLocation = new LocationResultReceiver(new Handler());
    }

    public interface IConvertLocationListener {
        void onResult(String nameAddress);
    }

    public void convert(final Location location, final IConvertLocationListener listener) {
        if (!Geocoder.isPresent()) {
            Toast.makeText(mActivity, ConvertLocationConstants.MessageErrorLog.NO_GEOCODER_AVAILABLE, Toast.LENGTH_SHORT).show();
            return;
        }
        LocationUtils.isOpenSettingLocation(mActivity, new LocationSetting.ILocationSettingListener() {
            @Override
            public void onResult(boolean isSuccess) {
                // not open setting location
                if (!isSuccess) {
                    return;
                }
                startServiceLocation(location);
                mListenerLocation = listener;
            }
        });
    }

    private void startServiceLocation(Location location) {
        Intent intent = new Intent(mActivity, FetchAddressIntentService.class);
        // get single address
        intent.putExtra(NUMBER_ADDRESS, 1);
        intent.putExtra(TYPE_CONVERT, LOCATION_ADDRESS);
        intent.putExtra(RESULT_RECEIVER, mResultReceiverLocation);
        intent.putExtra(DATA_LOCATION, location);
        mActivity.startService(intent);
    }

    private class LocationResultReceiver extends ResultReceiver {

        LocationResultReceiver(Handler handler) {
            super(handler);
        }

        @Override
        protected void onReceiveResult(int resultCode, Bundle resultData) {

            if (resultData == null) {
                return;
            }

            List<Address> addresses = resultData.getParcelableArrayList(RESULT_DATA);
            if (resultCode != SUCCESS && !SystemUtils.isNetworkOnline()) {
                // turn on network
                Toast.makeText(mActivity, "you must turn on network and try again", Toast.LENGTH_SHORT).show();
            } else {
                mListenerLocation.onResult(MethodUtils.isEmpty(addresses) ? null : convertAddressToString(addresses.get(0)));
            }
        }
    }

    private String convertAddressToString(Address address) {
        ArrayList<String> addressFragments = new ArrayList<>();
        // Fetch the address lines using getAddressLine, join them, and send them to the thread.
        for (int i = 0; i <= address.getMaxAddressLineIndex(); i++) {
            addressFragments.add(address.getAddressLine(i));
        }
        return TextUtils.join(System.getProperty("line.separator"), addressFragments);
    }

    public interface IConvertNameAddressListener {
        void onResult(Location location);
    }

    public void convert(final String nameAddress, final IConvertNameAddressListener listener) {
        if (!Geocoder.isPresent()) {
            Toast.makeText(mActivity, ConvertLocationConstants.MessageErrorLog.NO_GEOCODER_AVAILABLE, Toast.LENGTH_SHORT).show();
            return;
        }
        LocationUtils.isOpenSettingLocation(mActivity, new LocationSetting.ILocationSettingListener() {
            @Override
            public void onResult(boolean isSuccess) {
                // not open setting location
                if (!isSuccess) {
                    return;
                }
                startServiceNameAddress(nameAddress);
                mListenerNameAddress = listener;
            }
        });
    }

    private void startServiceNameAddress(String nameAddress) {
        Intent intent = new Intent(mActivity, FetchAddressIntentService.class);
        // get single address
        intent.putExtra(NUMBER_ADDRESS, 1);
        intent.putExtra(TYPE_CONVERT, NAME_ADDRESS);
        intent.putExtra(RESULT_RECEIVER, mResultReceiverNameAddress);
        intent.putExtra(DATA_NAME_ADDRESS, nameAddress);
        mActivity.startService(intent);
    }

    private class NameAddressResultReceiver extends ResultReceiver {

        NameAddressResultReceiver(Handler handler) {
            super(handler);
        }

        @Override
        protected void onReceiveResult(int resultCode, Bundle resultData) {

            if (resultData == null) {
                return;
            }
            List<Address> addresses = resultData.getParcelableArrayList(RESULT_DATA);
            if (resultCode != SUCCESS && !SystemUtils.isNetworkOnline()) {
                // turn on network
                Toast.makeText(mActivity, "you must turn on network and try again", Toast.LENGTH_SHORT).show();
            } else {
                mListenerNameAddress.onResult(MethodUtils.isEmpty(addresses) ? null : convertAddressToLocation(addresses.get(0)));
            }
        }
    }

    private Location convertAddressToLocation(Address address) {
        Location location = new Location(convertAddressToString(address));
        location.setLongitude(address.getLongitude());
        location.setLatitude(address.getLatitude());
        return location;
    }
}
