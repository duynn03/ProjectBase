package com.example.nguyenduy.projectbase.screen.start;

import com.example.nguyenduy.projectbase.R;
import com.example.nguyenduy.projectbase.base.BaseActivity;
import com.example.nguyenduy.projectbase.base.sharedPreference.SharedPreferenceUtils;
import com.example.nguyenduy.projectbase.screen.main.MainActivity;
import com.example.nguyenduy.projectbase.screen.start.login.LoginFragment;

public class StartActivity extends BaseActivity<IStartActivityPresenter> implements IStartActivityView {

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
        if (isUserLogin())
            startRootActivity(MainActivity.class);
        else
            addFragment(new LoginFragment(), false);
    }

    private boolean isUserLogin() {
        return SharedPreferenceUtils.getInstance().getUserInformation() != null;
    }
}

