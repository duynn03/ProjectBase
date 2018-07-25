package com.example.nguyenduy.projectbase.screen.main2.permission;

import com.example.nguyenduy.projectbase.R;
import com.example.nguyenduy.projectbase.base.BaseFragment;
import com.example.nguyenduy.projectbase.base.IBasePresenter;

import butterknife.OnClick;

public class PermissionFragment extends BaseFragment<IPermissionPresenter> implements IPermissionView {

    @Override
    public int getIdLayout() {
        return R.layout.fragment_permission;
    }

    @Override
    public IBasePresenter initPresenter() {
        return new PermissionPresenterImp(this);
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

/*
    @OnClick(R.id.btn_forgot_password_forgot_password)
    public void onClickButtonPassword() {
        getPresenter().forgotPassword();
    }

    @OnClick(R.id.btn_back_to_login)
    public void onClickBacktoLogin() {

    }
*/

}
