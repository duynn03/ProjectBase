package com.example.nguyenduy.projectbase.screen.main2.listener.network;

import android.net.ConnectivityManager;

import com.example.nguyenduy.projectbase.R;
import com.example.nguyenduy.projectbase.base.BaseFragment;
import com.example.nguyenduy.projectbase.base.IBasePresenter;
import com.example.nguyenduy.projectbase.base.broadcast.system.network.DataSaverReceiver;
import com.example.nguyenduy.projectbase.base.broadcast.system.network.DataSaverUtils;
import com.example.nguyenduy.projectbase.base.broadcast.system.network.NetworkReceiver;
import com.example.nguyenduy.projectbase.base.broadcast.system.network.NetworkUtils;

import butterknife.OnClick;

public class FragmentNetwork extends BaseFragment<INetworkPresenter> implements INetworkView, NetworkReceiver.IChangeNetworkListener, NetworkReceiver.IChangeMobileDataListener, NetworkReceiver.IChangeWifiListener, DataSaverReceiver.IChangeDataSaverListener {

    private NetworkUtils networkUtils;
    private DataSaverUtils dataSaver;

    @Override
    public int getIdLayout() {
        return R.layout.fragment_network;
    }

    @Override
    public IBasePresenter initPresenter() {
        return new NetworkPresenterImp(this);
    }

    @Override
    public void initViews() {

    }

    @Override
    public void initComponents() {
        networkUtils = new NetworkUtils(getRootActivity());
        dataSaver = new DataSaverUtils(getRootActivity());
    }

    @Override
    public void setEvents() {
    }

    @Override
    public void prepareComplete() {
        networkUtils.register(this, this, this);
    }

    @Override
    public void changeMobileDataConnected(boolean isMobileConnected) {
        showToast("Mobile: " + isMobileConnected);
    }

    @Override
    public void changeNetworkConnected(boolean isConnected) {
        showToast("Network: " + isConnected);
    }

    @Override
    public void changeWifiConnected(boolean isWifiConnected) {
        showToast("Wifi: " + isWifiConnected);
    }

    @Override
    public void onDestroy() {
        networkUtils.unregister();
        super.onDestroy();
    }

    @OnClick(R.id.btn_get_data_saver)
    public void onClickButtonGetDataSaver() {
        int status = DataSaverUtils.getStatusDataSaver();
        String msg = "";
        switch (status) {
            case ConnectivityManager.RESTRICT_BACKGROUND_STATUS_ENABLED:
                msg = "RESTRICT_BACKGROUND_STATUS_ENABLED";
                break;
            case ConnectivityManager.RESTRICT_BACKGROUND_STATUS_WHITELISTED:
                msg = "RESTRICT_BACKGROUND_STATUS_WHITELISTED";
                break;
            case ConnectivityManager.RESTRICT_BACKGROUND_STATUS_DISABLED:
                msg = "RESTRICT_BACKGROUND_STATUS_DISABLED";
                break;
            case DataSaverUtils.NOT_ACTIVE_NETWORK_METERED:
                msg = "NOT_ACTIVE_NETWORK_METERED";
                break;
        }
        showToast(msg);
    }

    @OnClick(R.id.btn_open_setting_data_saver)
    public void onClickOpenSettingDataSaver() {
        DataSaverUtils.openSettingApplicationWhiteListDataSaver(getRootActivity());
    }

    @OnClick(R.id.btn_register_data_saver)
    public void onClickRegisterDataSaver() {
        dataSaver.register(this);
    }

    @Override
    public void changeStatusDataSaver(int status) {
        String msg = "";
        switch (status) {
            case ConnectivityManager.RESTRICT_BACKGROUND_STATUS_ENABLED:
                msg = "changeStatusDataSaver(): RESTRICT_BACKGROUND_STATUS_ENABLED";
                break;
            case ConnectivityManager.RESTRICT_BACKGROUND_STATUS_WHITELISTED:
                msg = "changeStatusDataSaver(): RESTRICT_BACKGROUND_STATUS_WHITELISTED";
                break;
            case ConnectivityManager.RESTRICT_BACKGROUND_STATUS_DISABLED:
                msg = "changeStatusDataSaver(): RESTRICT_BACKGROUND_STATUS_DISABLED";
                break;
            case DataSaverUtils.NOT_ACTIVE_NETWORK_METERED:
                msg = "changeStatusDataSaver(): NOT_ACTIVE_NETWORK_METERED";
                break;
        }
        showToast(msg);
    }

    @OnClick(R.id.btn_unregister_data_saver)
    public void onClickUnregisterDataSaver() {
        dataSaver.unregister();
    }

    @OnClick(R.id.btn_down_task)
    public void onClickDownloadTask() {
        if (DataSaverUtils.getStatusDataSaver() == ConnectivityManager.RESTRICT_BACKGROUND_STATUS_ENABLED) {
            showToast("Open Setting and try again");
            DataSaverUtils.openSettingApplicationWhiteListDataSaver(getRootActivity());
        } else {
            showToast("Downloading task");
        }
    }
}
