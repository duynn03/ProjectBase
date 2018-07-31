package com.example.nguyenduy.projectbase.screen.main2.location.geofence;

import com.example.nguyenduy.projectbase.R;
import com.example.nguyenduy.projectbase.base.BaseFragment;
import com.example.nguyenduy.projectbase.base.IBasePresenter;
import com.example.nguyenduy.projectbase.base.location.geofencing.GeofenceUtils;
import com.google.android.gms.location.Geofence;

import java.util.ArrayList;

public class GeofenceFragment extends BaseFragment<IGeofencePresenter> implements IGeofenceView {

    private ArrayList<Geofence> geofences;
    private GeofenceUtils geofenceUtils;

    @Override
    public int getIdLayout() {
        return R.layout.fragment_geofence;
    }

    @Override
    public IBasePresenter initPresenter() {
        return new GeofencePresenterImp(this);
    }

    @Override
    public void initViews() {

    }

    @Override
    public void initComponents() {
        geofences = new ArrayList<>();
        geofenceUtils = new GeofenceUtils(getRootActivity());

        geofences.add(geofenceUtils.createGeofence(21.030694f, 105.784402f, "58 Duy Tân"));
        geofences.add(geofenceUtils.createGeofence(21.030819f, 105.784492f, "CMC Telecom Duy tân"));
        geofenceUtils.addGeofences(getRootActivity(), geofences);
    }

    @Override
    public void setEvents() {

    }

    @Override
    public void prepareComplete() {

    }

    @Override
    public void onDestroy() {
        geofenceUtils.removeGeofence(getRootActivity());
        super.onDestroy();
    }
}
