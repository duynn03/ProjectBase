package com.example.nguyenduy.projectbase.screen.main.architectureComponents.liveData.activity;

import android.app.Activity;
import android.arch.lifecycle.LiveData;
import android.location.Location;

import com.example.nguyenduy.projectbase.base.location.ReceiveLocationUpdate;

import java.util.List;

public class ExtendLiveData extends LiveData<List<Location>> implements ReceiveLocationUpdate.IReceiveLocationUpdateListener {

    private Activity mActivity;

    private ReceiveLocationUpdate locationUpdate;

    public ExtendLiveData(Activity activity) {
        mActivity = activity;
        locationUpdate = new ReceiveLocationUpdate(mActivity);
    }

    @Override
    protected void onActive() {
        locationUpdate.registerLocationUpdates(mActivity, this);
    }

    @Override
    protected void onInactive() {
        locationUpdate.unRegisterLocationUpdates();
    }

    @Override
    public void onResult(List<Location> locations) {
        setValue(locations);
    }
}
