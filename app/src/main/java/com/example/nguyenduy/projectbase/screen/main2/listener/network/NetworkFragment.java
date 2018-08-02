package com.example.nguyenduy.projectbase.screen.main2.listener.network;

import com.example.nguyenduy.projectbase.R;
import com.example.nguyenduy.projectbase.base.BaseFragment;
import com.example.nguyenduy.projectbase.base.IBasePresenter;
import com.example.nguyenduy.projectbase.base.listener.network.NetworkReceiver;
import com.example.nguyenduy.projectbase.base.listener.network.NetworkUtils;

public class NetworkFragment extends BaseFragment<INetworkPresenter> implements INetworkView, NetworkReceiver.INetworkChangeListener {

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
        networkUtils = new NetworkUtils(getRootActivity(), this);
    }

    @Override
    public void setEvents() {
        networkUtils.registerChangeNetwork();
    }

    @Override
    public void prepareComplete() {

    }

    @Override
    public void changeNetwork(boolean isWifiConnected, boolean isMobileConnected) {
        showToast("Wifi: " + isWifiConnected + "\nMobile: " + isMobileConnected);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        networkUtils.unregisterChangeNetwork();
    }
}
