package com.example.nguyenduy.projectbase.screen.main.listener.handshake;

import com.example.nguyenduy.projectbase.R;
import com.example.nguyenduy.projectbase.base.BaseFragment;
import com.example.nguyenduy.projectbase.base.IBasePresenter;
import com.example.nguyenduy.projectbase.base.listener.HandShakeListener;
import com.example.nguyenduy.projectbase.base.listener.HandShakeListenerUtils;
import com.example.nguyenduy.projectbase.utils.LogUtils;
import com.example.nguyenduy.projectbase.utils.method.MethodUtils;

import butterknife.OnClick;

public class HandshakeFragment extends BaseFragment<IHandshakePresenter> implements IHandshakeView, HandShakeListener.OnHandShakeListener {

    private static final String TAG = MethodUtils.getTagClass(HandshakeFragment.class);

    private HandShakeListener mHandShakeListener;

    @Override
    public int getIdLayout() {
        return R.layout.fragment_handshake;
    }

    @Override
    public IBasePresenter initPresenter() {
        return new HandshakePresenterImp(this);
    }

    @Override
    public void initViews() {

    }

    @Override
    public void initComponents() {

    }

    @Override
    public void setEvents() {
        mHandShakeListener = new HandShakeListener(this);
    }

    @Override
    public void prepareComplete() {

    }

    @OnClick(R.id.btn_register_hand_shake)
    public void registerHandShakeListener() {
        HandShakeListenerUtils.getInstance().registerListener(mHandShakeListener);
    }

    @OnClick(R.id.btn_unregister_hand_shake)
    public void unRegisterHandShakeListener() {
        HandShakeListenerUtils.getInstance().unregisterListener(mHandShakeListener);
    }

    @Override
    public void onShake(int count) {
        showToast("HandShakeListener: " + count);
        LogUtils.i(TAG + "onShake: " + count);
    }

}
