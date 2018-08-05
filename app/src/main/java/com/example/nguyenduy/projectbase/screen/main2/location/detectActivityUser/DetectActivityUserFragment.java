package com.example.nguyenduy.projectbase.screen.main2.location.detectActivityUser;

import com.example.nguyenduy.projectbase.R;
import com.example.nguyenduy.projectbase.base.BaseFragment;
import com.example.nguyenduy.projectbase.base.IBasePresenter;
import com.example.nguyenduy.projectbase.base.location.detectActivityUser.DetectActivityUserUtils;
import com.example.nguyenduy.projectbase.utils.LogUtils;
import com.example.nguyenduy.projectbase.utils.method.MethodUtils;
import com.google.android.gms.location.ActivityTransition;
import com.google.android.gms.location.DetectedActivity;

import java.util.ArrayList;
import java.util.List;

public class DetectActivityUserFragment extends BaseFragment<IDetectActivityUserPresenter> implements IDetectActivityUserView, DetectActivityUserUtils.IDetectActivityUserListener {

    private static final String TAG = MethodUtils.getTagClass(DetectActivityUserFragment.class);

    private DetectActivityUserUtils detectActivityUserUtils;

    @Override
    public int getIdLayout() {
        return R.layout.fragment_realm;
    }

    @Override
    public IBasePresenter initPresenter() {
        return new DetectActivityUserPresenterImp(this);
    }

    @Override
    public void initViews() {

    }

    @Override
    public void initComponents() {
        detectActivityUserUtils = new DetectActivityUserUtils(getRootActivity());
    }

    private List<ActivityTransition> initListActivityTransions() {
        List<ActivityTransition> transitions = new ArrayList<>();
        transitions.add(detectActivityUserUtils.createTransition(DetectedActivity.IN_VEHICLE, ActivityTransition.ACTIVITY_TRANSITION_ENTER));
        transitions.add(detectActivityUserUtils.createTransition(DetectedActivity.IN_VEHICLE, ActivityTransition.ACTIVITY_TRANSITION_EXIT));
        transitions.add(detectActivityUserUtils.createTransition(DetectedActivity.ON_BICYCLE, ActivityTransition.ACTIVITY_TRANSITION_ENTER));
        transitions.add(detectActivityUserUtils.createTransition(DetectedActivity.ON_BICYCLE, ActivityTransition.ACTIVITY_TRANSITION_EXIT));
        transitions.add(detectActivityUserUtils.createTransition(DetectedActivity.ON_FOOT, ActivityTransition.ACTIVITY_TRANSITION_ENTER));
        transitions.add(detectActivityUserUtils.createTransition(DetectedActivity.ON_FOOT, ActivityTransition.ACTIVITY_TRANSITION_EXIT));
        transitions.add(detectActivityUserUtils.createTransition(DetectedActivity.STILL, ActivityTransition.ACTIVITY_TRANSITION_ENTER));
        transitions.add(detectActivityUserUtils.createTransition(DetectedActivity.STILL, ActivityTransition.ACTIVITY_TRANSITION_EXIT));
         /*transitions.add(detectActivityUserUtils.createTransition(DetectedActivity.UNKNOWN, ActivityTransition.ACTIVITY_TRANSITION_ENTER));
        transitions.add(detectActivityUserUtils.createTransition(DetectedActivity.UNKNOWN, ActivityTransition.ACTIVITY_TRANSITION_EXIT));
        transitions.add(detectActivityUserUtils.createTransition(DetectedActivity.TILTING, ActivityTransition.ACTIVITY_TRANSITION_ENTER));
        transitions.add(detectActivityUserUtils.createTransition(DetectedActivity.TILTING, ActivityTransition.ACTIVITY_TRANSITION_EXIT));*/
        transitions.add(detectActivityUserUtils.createTransition(DetectedActivity.WALKING, ActivityTransition.ACTIVITY_TRANSITION_ENTER));
        transitions.add(detectActivityUserUtils.createTransition(DetectedActivity.WALKING, ActivityTransition.ACTIVITY_TRANSITION_EXIT));
        transitions.add(detectActivityUserUtils.createTransition(DetectedActivity.RUNNING, ActivityTransition.ACTIVITY_TRANSITION_ENTER));
        transitions.add(detectActivityUserUtils.createTransition(DetectedActivity.RUNNING, ActivityTransition.ACTIVITY_TRANSITION_EXIT));
        return transitions;
    }

    @Override
    public void setEvents() {

    }

    @Override
    public void prepareComplete() {
        detectActivityUserUtils.register(/*initListActivityTransions(),*/ this);
    }

    @Override
    public void onResult(List<DetectedActivity> activities) {
        for (DetectedActivity activity : activities) {
            LogUtils.i(TAG + DetectActivityUserUtils.getActivityString(activity.getType()) + " " + activity.getConfidence() + "%");
        }
    }

    @Override
    public void onDestroy() {
        detectActivityUserUtils.unregister();
        super.onDestroy();
    }
}
