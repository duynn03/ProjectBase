package com.example.nguyenduy.projectbase.screen.main2.location.location;

import android.location.Location;

import com.example.nguyenduy.projectbase.R;
import com.example.nguyenduy.projectbase.base.BaseFragment;
import com.example.nguyenduy.projectbase.base.IBasePresenter;
import com.example.nguyenduy.projectbase.base.location.LocationUtils;

import butterknife.OnClick;

public class LocationFragment extends BaseFragment<ILocationPresenter> implements ILocationView {

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

    }

    @Override
    public void setEvents() {

    }

    @Override
    public void prepareComplete() {

    }


    @OnClick(R.id.btn_last_knowwn_location)
    public void onClickGetLastKnownLocation() {
        LocationUtils.getInstance().getLastKnownLocation(getRootActivity(), new LocationUtils.IGetLastKnownLocationListener() {
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

}
