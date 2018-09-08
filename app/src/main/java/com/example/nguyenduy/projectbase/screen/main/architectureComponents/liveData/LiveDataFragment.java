package com.example.nguyenduy.projectbase.screen.main.architectureComponents.liveData;

import com.example.nguyenduy.projectbase.R;
import com.example.nguyenduy.projectbase.base.BaseFragment;
import com.example.nguyenduy.projectbase.base.IBasePresenter;
import com.example.nguyenduy.projectbase.screen.main.architectureComponents.liveData.activity.LiveDataActivity;

import butterknife.OnClick;

public class LiveDataFragment extends BaseFragment<ILiveDataPresenter> implements ILiveDataView {

    @Override
    public int getIdLayout() {
        return R.layout.fragment_live_data;
    }

    @Override
    public IBasePresenter initPresenter() {
        return new LiveDataPresenterImp(this);
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

    @OnClick(R.id.btn_open_activity_live_data)
    public void oncLickButtonOpenActivityLiveData() {
        (getRootActivity()).startActivity(LiveDataActivity.class, null, null);
    }
}
