package com.example.nguyenduy.projectbase.screen.start.login;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;

import com.example.nguyenduy.projectbase.screen.main.MainActivity;
import com.example.nguyenduy.projectbase.R;
import com.example.nguyenduy.projectbase.base.BaseFragment;
import com.example.nguyenduy.projectbase.base.IBasePresenter;
import com.example.nguyenduy.projectbase.base.firebase.FireBaseUtils;
import com.example.nguyenduy.projectbase.utils.permission.BasePermission;
import com.example.nguyenduy.projectbase.utils.permission.PermissionUtils;

import java.util.List;

import butterknife.OnClick;

public class LoginFragment extends BaseFragment<ILoginPresenter> implements ILoginView{

    @Override
    public int getIdLayout() {
        return R.layout.fragment_login;
    }

    @Override
    public IBasePresenter initPresenter() {
        return new LoginPresenterImp(this);
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

    @OnClick(R.id.btn_login)
    public void onClickLogin() {
        getPresenter().login();
        startActivity(MainActivity.class);
    }

    private void startActivity(Class<?> clazz) {
        Intent intent = new Intent(getContext(), clazz);
        // root task
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    @OnClick(R.id.btn_forgot_password)
    public void onClickForgotPassword() {

    }

    @OnClick(R.id.btn_sign_up)
    public void onClickSignUp() {

    }

}
