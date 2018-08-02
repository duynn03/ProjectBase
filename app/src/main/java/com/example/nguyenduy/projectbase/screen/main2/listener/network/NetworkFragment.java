package com.example.nguyenduy.projectbase.screen.main2.listener.network;

import com.example.nguyenduy.projectbase.R;
import com.example.nguyenduy.projectbase.base.BaseFragment;
import com.example.nguyenduy.projectbase.base.IBasePresenter;
import com.example.nguyenduy.projectbase.base.listener.network.NetworkReceiver;
import com.example.nguyenduy.projectbase.base.listener.network.NetworkUtils;

public class NetworkFragment extends BaseFragment<INetworkPresenter> implements INetworkView, NetworkReceiver.INetworkChangeListener, NetworkReceiver.IChangeMobileDataListener, NetworkReceiver.IChangeWifiListener {

    private NetworkUtils networkUtils;

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
    }

    @Override
    public void setEvents() {
        networkUtils.setListenerMobileDataChange(this);
        networkUtils.setListenerWifiChange(this);
        networkUtils.setListenerNetworkChange(this);
    }

    @Override
    public void prepareComplete() {
        networkUtils.registerChangeNetwork();
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
        networkUtils.unregisterChangeNetwork();
        super.onDestroy();
    }

}
