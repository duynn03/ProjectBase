package com.example.nguyenduy.projectbase.screen.main;

import android.content.Intent;

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

    @OnClick(R.id.btn_subscribe)
    public void onClickButtonSubscribe() {
        TopicUtils.subscribe(FireBaseConstants.Topic.NEWS);
    }

    @OnClick(R.id.btn_unsubscribe)
    public void onClickButtonUnsubscribe() {
        TopicUtils.unsubscribe(FireBaseConstants.Topic.NEWS);
    }

    @OnClick(R.id.btn_logout)
    public void onClickButtonLogout() {
        SharedPreferenceUtils.getInstance().clearUserInformation();
        startActivity(StartActivity.class);
    }

    @OnClick(R.id.btn_generate_crash)
    public void onClickCrash() {
        // AppCenterUtils.generateCrash();
        FireBaseUtils.testCrash();
    }

    private void startActivity(Class<?> clazz) {
        Intent intent = new Intent(this, clazz);
        // root task
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
