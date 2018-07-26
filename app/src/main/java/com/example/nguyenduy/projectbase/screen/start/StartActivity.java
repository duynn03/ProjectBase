package com.example.nguyenduy.projectbase.screen.start;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.nguyenduy.projectbase.R;
import com.example.nguyenduy.projectbase.base.BaseActivity;
import com.example.nguyenduy.projectbase.screen.main.MainActivity;
import com.example.nguyenduy.projectbase.screen.start.login.LoginFragment;
import com.example.nguyenduy.projectbase.utils.data.SharedPreference.SharedPreferenceUtils;
import com.google.firebase.perf.metrics.AddTrace;

import butterknife.OnClick;

public class StartActivity extends BaseActivity<IStartActivityPresenter> implements IStartActivityView {

    @AddTrace(name = "onCreateTrace", enabled = true)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public int getIdLayout() {
        return R.layout.activity_start;
    }

    @Override
    public IStartActivityPresenter initPresenter() {
        return new StartActivityPresenterImp(this);
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
        if (SharedPreferenceUtils.getInstance().getUserInformation() == null) {
            addFragment(new LoginFragment(), false);
        } else {
            startRootActivity(MainActivity.class);
        }
    }

    @OnClick(R.id.btn_go_to_login_fragment)
    public void goToLoginFragment() {
        addFragment(new LoginFragment(), false);
    }

    @OnClick(R.id.btn_trace)
    public void testTrace() {
        PerformanceUtils.test(imGallery);
    }

}

