package com.example.nguyenduy.projectbase.screen.main2.architectureComponents.room;

import com.example.nguyenduy.projectbase.R;
import com.example.nguyenduy.projectbase.base.BaseFragment;
import com.example.nguyenduy.projectbase.base.IBasePresenter;

public class RoomFragment extends BaseFragment<IRoomPresenter> implements IRoomView {

    @Override
    public int getIdLayout() {
        return R.layout.fragment_room;
    }

    @Override
    public IBasePresenter initPresenter() {
        return new RoomPresenterImp(this);
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
