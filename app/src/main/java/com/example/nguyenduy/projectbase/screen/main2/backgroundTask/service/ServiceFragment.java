package com.example.nguyenduy.projectbase.screen.main2.backgroundTask.service;

import com.example.nguyenduy.projectbase.R;
import com.example.nguyenduy.projectbase.base.BaseFragment;
import com.example.nguyenduy.projectbase.base.IBasePresenter;
import com.example.nguyenduy.projectbase.base.backgroundTask.service.BackgroundService;
import com.example.nguyenduy.projectbase.base.backgroundTask.service.ForegroundService;
import com.example.nguyenduy.projectbase.base.backgroundTask.service.ServiceUtils;
import com.example.nguyenduy.projectbase.base.backgroundTask.service.bindService.BindServiceBinder;
import com.example.nguyenduy.projectbase.base.backgroundTask.service.bindService.BindServiceMessenger;
import com.example.nguyenduy.projectbase.utils.LogUtils;
import com.example.nguyenduy.projectbase.utils.method.MethodUtils;

import butterknife.OnClick;

public class ServiceFragment extends BaseFragment<IServicePresenter> implements IServiceView {

    public static final String TAG = MethodUtils.getTagClass(ServiceFragment.class);

    private ServiceUtils serviceUtils;

    @Override
    public int getIdLayout() {
        return R.layout.fragment_service;
    }

    @Override
    public IBasePresenter initPresenter() {
        return new ServicePresenterImp(this);
    }

    @Override
    public void initViews() {

    }

    @Override
    public void initComponents() {
        serviceUtils = new ServiceUtils(getContext());
    }

    @Override
    public void setEvents() {

    }

    @Override
    public void prepareComplete() {

    }

    @OnClick(R.id.btn_start_background_service)
    public void onClickButtonStartBackgroundService() {
        serviceUtils.startBackgroundService();
    }

    @OnClick(R.id.btn_start_foreground_service)
    public void onClickButtonStartForegroundService() {
        serviceUtils.startForegroundService();
    }

    @OnClick(R.id.btn_stop_foreground_service)
    public void onClickButtonStopForegroundService() {
        serviceUtils.stopForegroundService();
    }

    @OnClick(R.id.btn_check_service_running)
    public void onClickButtonCheckServiceRunning() {
        showToast("BindServiceBinder Running: " + serviceUtils.isServiceRunning(BindServiceBinder.class));
        showToast("BindServiceMessenger Running: " + serviceUtils.isServiceRunning(BindServiceMessenger.class));
        showToast("ForegroundService Running: " + serviceUtils.isServiceRunning(ForegroundService.class));
        showToast("BackgroundService Running: " + serviceUtils.isServiceRunning(BackgroundService.class));

        LogUtils.i(TAG + "BindServiceBinder Running: " + serviceUtils.isServiceRunning(BindServiceBinder.class));
        LogUtils.i(TAG + "BindServiceMessenger Running: " + serviceUtils.isServiceRunning(BindServiceMessenger.class));
        LogUtils.i(TAG + "ForegroundService Running: " + serviceUtils.isServiceRunning(ForegroundService.class));
        LogUtils.i(TAG + "BackgroundService Running: " + serviceUtils.isServiceRunning(BackgroundService.class));
    }

    @OnClick(R.id.btn_get_data_from_bind_service_binder)
    public void onClickButtonGetDataFromService() {
        if (serviceUtils.isConnectedBindServiceBinder())
            showToast(serviceUtils.getDataFromBindService());
        else
            showToast("Not Connect Bind Service");
    }

    @OnClick(R.id.btn_send_message_to_service_messenger)
    public void onClickButtonSendMessageToServiceMessenger() {
        serviceUtils.sendMessageToServiceMessenger();
    }

    @Override
    public void onStart() {
        super.onStart();
        serviceUtils.startBindServiceBinder();
        serviceUtils.startBindServiceMessage();
    }

    @Override
    public void onStop() {
        super.onStop();
        serviceUtils.stopBindServiceBinder();
        serviceUtils.stopBindServiceMessage();
    }
}
