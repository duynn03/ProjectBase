package com.example.nguyenduy.projectbase.screen.main2.broadcast;

import com.example.nguyenduy.projectbase.R;
import com.example.nguyenduy.projectbase.base.BaseFragment;
import com.example.nguyenduy.projectbase.base.IBasePresenter;
import com.example.nguyenduy.projectbase.base.broadcast.system.airplane.AirplaneReceiver;
import com.example.nguyenduy.projectbase.base.broadcast.system.airplane.AirplaneUtils;
import com.example.nguyenduy.projectbase.base.broadcast.system.screen.ScreenReceiver;
import com.example.nguyenduy.projectbase.base.broadcast.system.screen.ScreenUtils;
import com.example.nguyenduy.projectbase.utils.LogUtils;
import com.example.nguyenduy.projectbase.utils.method.MethodUtils;
import com.example.nguyenduy.projectbase.utils.method.SystemUtils;

import butterknife.OnClick;

public class BroadcastFragment extends BaseFragment<IBroadcastPresenter> implements IBroadcastView, AirplaneReceiver.IChangeAirplaneListener, ScreenReceiver.IChangeScreenListener {

    private static final String TAG = MethodUtils.getTagClass(BroadcastFragment.class);

    private AirplaneUtils airplaneUtils;
    private ScreenUtils screenUtils;

    @Override
    public int getIdLayout() {
        return R.layout.fragment_broadcast;
    }

    @Override
    public IBasePresenter initPresenter() {
        return new BroadcastPresenterImp(this);
    }

    @Override
    public void initViews() {

    }

    @Override
    public void initComponents() {
        airplaneUtils = new AirplaneUtils(getRootActivity());
        screenUtils = new ScreenUtils(getRootActivity());
    }

    @Override
    public void setEvents() {

    }

    @Override
    public void prepareComplete() {

    }

    @OnClick(R.id.btn_get_status_airplane)
    public void onClickButtonGetStatusAirplane() {
        LogUtils.i(TAG + "onClickButtonGetStatusAirplane(): " + SystemUtils.isAirplaneModeOn());
        showToast("onClickButtonGetStatusAirplane(): " + SystemUtils.isAirplaneModeOn());
    }

    @OnClick(R.id.btn_register_airplane)
    public void onClickButtonRegisterAirplane() {
        airplaneUtils.register(this);
    }

    @OnClick(R.id.btn_unregister_airplane)
    public void onClickButtonUnregisterAirplane() {
        airplaneUtils.unregister();
    }

    @Override
    public void changeStatusAirplane(boolean isOnAirplane) {
        LogUtils.i(TAG + "changeStatusScreen(): " + isOnAirplane);
        showToast("changeStatusScreen(): " + isOnAirplane);
    }

    @OnClick(R.id.btn_register_screen)
    public void onClickButtonRegisterScreen() {
        screenUtils.register(this);
    }

    @OnClick(R.id.btn_unregister_screen)
    public void onClickButtonUnregisterScreen() {
        screenUtils.unregister();
    }

    @Override
    public void changeStatusScreen(boolean isOnScreen) {
        LogUtils.i(TAG + "changeStatusScreen: " + isOnScreen);
        showToast("changeStatusScreen: " + isOnScreen);
    }
}
