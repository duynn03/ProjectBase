package com.example.nguyenduy.projectbase.screen.main2.backgroundTask.service;

import com.example.nguyenduy.projectbase.R;
import com.example.nguyenduy.projectbase.base.BaseFragment;
import com.example.nguyenduy.projectbase.base.IBasePresenter;
import com.example.nguyenduy.projectbase.base.backgroundTask.service.ServiceUtils;

import butterknife.OnClick;

public class ServiceFragment extends BaseFragment<IServicePresenter> implements IServiceView {

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
    public void onClickButtonStartBackgoundService() {
        serviceUtils.startBackgroundService();
    }

    @OnClick(R.id.btn_start_foreground_service)
    public void onClickButtonStartForegoundService() {
        serviceUtils.startForegroundService();
    }

    @OnClick(R.id.btn_stop_foreground_service)
    public void onClickButtonStopForegoundService() {
        serviceUtils.stopForegroundService();
    }
}
