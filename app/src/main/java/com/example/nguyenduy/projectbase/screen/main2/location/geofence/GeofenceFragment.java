package com.example.nguyenduy.projectbase.screen.main2.location.geofence;

import android.text.TextUtils;

import com.example.nguyenduy.projectbase.R;
import com.example.nguyenduy.projectbase.base.BaseFragment;
import com.example.nguyenduy.projectbase.base.IBasePresenter;
import com.example.nguyenduy.projectbase.base.location.geofencing.GeofenceUtils;
import com.example.nguyenduy.projectbase.utils.LogUtils;
import com.example.nguyenduy.projectbase.utils.method.MethodUtils;
import com.google.android.gms.location.Geofence;

import java.util.ArrayList;
import java.util.List;

public class GeofenceFragment extends BaseFragment<IGeofencePresenter> implements IGeofenceView, GeofenceUtils.IGeofenceListener {

    private static final String TAG = MethodUtils.getTagClass(GeofenceFragment.class);

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
        geofenceUtils = new GeofenceUtils(getRootActivity());
    }

    private List<Geofence> initListGeofences() {
        List<Geofence> geofences = new ArrayList<>();
        geofences.add(geofenceUtils.createGeofence(21.030831, 105.784454, "CMC Telecom"));
        geofences.add(geofenceUtils.createGeofence(20.960108, 105.742533, "HH2 ABC"));
        return geofences;
    }

    @Override
    public void setEvents() {

    }

    @Override
    public void prepareComplete() {
        geofenceUtils.register(initListGeofences(), this);
    }

    @Override
    public void onResult(int typeTransition, List<Geofence> triggeringGeofences) {
        // Log detail...
        String geofenceTransitionDetails = getGeofenceTransitionDetails(typeTransition, triggeringGeofences);
        showToast(geofenceTransitionDetails);
        LogUtils.i(TAG + geofenceTransitionDetails);
    }

    public static String getGeofenceTransitionDetails(int typeTransition, List<Geofence> triggeringGeofences) {
        String geofenceTransitionString = GeofenceUtils.getTransitionString(typeTransition);
        // Get the Ids of each geofence that was triggered.
        List<String> triggeringGeofencesIdsList = new ArrayList<>();
        for (Geofence geofence : triggeringGeofences) {
            triggeringGeofencesIdsList.add(geofence.getRequestId());
        }
        String triggeringGeofencesIdsString = TextUtils.join(", ", triggeringGeofencesIdsList);
        return geofenceTransitionString + ": " + triggeringGeofencesIdsString;
    }

    @Override
    public void onDestroy() {
        geofenceUtils.unregister();
        super.onDestroy();
    }

}
