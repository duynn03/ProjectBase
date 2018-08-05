package com.example.nguyenduy.projectbase.screen.main2.navigation.navigationBottom.handshake;

import com.example.nguyenduy.projectbase.R;
import com.example.nguyenduy.projectbase.base.BaseFragment;
import com.example.nguyenduy.projectbase.base.IBasePresenter;

public class HandshakeNavigationBottomFragment extends BaseFragment<IHandshakeNavigationBottomPresenter> implements IHandshakeNavigationBottomView {

    @Override
    public int getIdLayout() {
        return R.layout.fragment_navigation_bottom_hand_shake;
    }

    @Override
    public IBasePresenter initPresenter() {
        return new HandshakeNavigationBottomPresenterImp(this);
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
