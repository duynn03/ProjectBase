package com.example.nguyenduy.projectbase.screen.start.ForgotPassword;

import com.example.nguyenduy.projectbase.R;
import com.example.nguyenduy.projectbase.base.BaseFragment;
import com.example.nguyenduy.projectbase.base.IBasePresenter;

import butterknife.OnClick;

public class ForgotPasswordFragment extends BaseFragment<IForgotPasswordPresenter> implements IForgotPasswordView {

    @Override
    public int getIdLayout() {
        return R.layout.fragment_forgot_password;
    }

    @Override
    public IBasePresenter initPresenter() {
        return new ForgotPasswordPresenterImp(this);
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

    @OnClick(R.id.btn_forgot_password_forgot_password)
    public void onClickButtonPassword() {
        getPresenter().forgotPassword();
    }

    @OnClick(R.id.btn_back_to_login)
    public void onClickBacktoLogin() {

    }

}
