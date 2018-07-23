package com.example.nguyenduy.projectbase.screen.main;

import com.example.nguyenduy.projectbase.R;
import com.example.nguyenduy.projectbase.base.BaseActivity;
import com.example.nguyenduy.projectbase.base.firebase.FireBaseConstants;
import com.example.nguyenduy.projectbase.base.firebase.FCM.TopicUtils;
import com.example.nguyenduy.projectbase.base.firebase.FireBaseUtils;
import com.example.nguyenduy.projectbase.screen.start.StartActivity;
import com.example.nguyenduy.projectbase.utils.data.SharedPreference.SharedPreferenceUtils;

import butterknife.OnClick;

public class MainActivity extends BaseActivity<IMainPresenter> implements IMainView {

    @Override
    public int getIdLayout() {
        return R.layout.activity_main;
    }

    @Override
    public IMainPresenter initPresenter() {
        return new MainPresenterImp(this);
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
