package com.example.nguyenduy.projectbase.screen.main2.location.location;

import android.location.Location;
import android.widget.Toast;

import com.example.nguyenduy.projectbase.R;
import com.example.nguyenduy.projectbase.application.MyApplication;
import com.example.nguyenduy.projectbase.base.BaseFragment;
import com.example.nguyenduy.projectbase.base.IBasePresenter;
import com.example.nguyenduy.projectbase.base.location.LocationSetting;
import com.example.nguyenduy.projectbase.base.location.LocationUtils;
import com.example.nguyenduy.projectbase.base.location.ReceiveLocationUpdate;

import java.util.List;

import butterknife.OnClick;

public class LocationFragment extends BaseFragment<ILocationPresenter> implements ILocationView, ReceiveLocationUpdate.IReceiveLocationUpdateListener {

    private ReceiveLocationUpdate locationUpdate;

    @Override
    public int getIdLayout() {
        return R.layout.fragment_location;
    }

    @Override
    public IBasePresenter initPresenter() {
        return new LocationPresenterImp(this);
    }

    @Override
    public void initViews() {

    }

    @Override
    public void initComponents() {
        locationUpdate = new ReceiveLocationUpdate(getRootActivity());
    }

    @Override
    public void setEvents() {

    }

    @Override
    public void prepareComplete() {

    }


    @OnClick(R.id.btn_last_known_location)
    public void onClickGetLastKnownLocation() {
        LocationUtils.getLastKnownLocation(getRootActivity(), new LocationUtils.IGetLastKnownLocationListener() {
            @Override
            public void onResult(Location location) {
                if (null != location) {
                    showToast("getLastKnownLocation(): Latitude: " + location.getLatitude() + "\n" + "Longitude: " + location.getLongitude());
                } else {
                    showToast("not get location from getLastKnownLocation()");
                }
            }
        });
    }

    @OnClick(R.id.btn_change_location_setting)
    public void onClickChangeLocationSetting() {
        LocationUtils.isOpenSettingLocation(getRootActivity(), new LocationSetting.ILocationSettingListener() {
            @Override
            public void onResult(boolean isSuccess) {
                showToast("isOpenSettingLocation: " + isSuccess);
            }
        });
    }

    @OnClick(R.id.btn_register_update_location)
    public void onClickRegisterLocationUpdate() {
        locationUpdate.registerLocationUpdates(getRootActivity(), this);
    }

    @OnClick(R.id.btn_unregister_update_location)
    public void onClickUnregisterLocationUpdate() {
        locationUpdate.unRegisterLocationUpdates();
    }

    @Override
    public void onStart() {
        super.onStart();
        locationUpdate.reRegisterLocationUpdates();
    }

    @Override
    public void onStop() {
        super.onStop();
        locationUpdate.pendingRegisterLocationUpdates();
    }

    @Override
    public void onDestroy() {
        locationUpdate.unRegisterLocationUpdates();
        super.onDestroy();
    }

    @Override
    public void onResult(List<Location> locations) {
        for (Location location : locations) {
            // Update UI with location data
            Toast.makeText(MyApplication.getAppContext(),
                    "Latitude: " + location.getLatitude()
                            + "\nLongitude: " + location.getLongitude()
                            + "\nTime: " + location.getTime(),
                    Toast.LENGTH_SHORT).show();
        }
    }

}
