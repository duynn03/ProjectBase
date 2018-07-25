package com.example.nguyenduy.projectbase.screen.main2.listener.handshake;

import com.example.nguyenduy.projectbase.R;
import com.example.nguyenduy.projectbase.base.BaseFragment;
import com.example.nguyenduy.projectbase.base.IBasePresenter;

public class HandshakeFragment extends BaseFragment<IHandshakePresenter> implements IHandshakeView {

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

    }

    @Override
    public void prepareComplete() {

    }
}
