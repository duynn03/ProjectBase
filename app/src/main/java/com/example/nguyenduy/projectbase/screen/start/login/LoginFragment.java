package com.example.nguyenduy.projectbase.screen.start.login;

import android.content.Intent;

import com.example.nguyenduy.projectbase.R;
import com.example.nguyenduy.projectbase.base.BaseFragment;
import com.example.nguyenduy.projectbase.base.IBasePresenter;
import com.example.nguyenduy.projectbase.screen.main.MainActivity;
import com.example.nguyenduy.projectbase.screen.start.ForgotPassword.ForgotPasswordFragment;
import com.example.nguyenduy.projectbase.screen.start.SignUp.SignUpFragment;

import butterknife.OnClick;

public class LoginFragment extends BaseFragment<ILoginPresenter> implements ILoginView {

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
        getRootActivity().replaceFragment(new ForgotPasswordFragment(), true);
    }

    @OnClick(R.id.btn_sign_up)
    public void onClickSignUp() {
        getRootActivity().replaceFragment(new SignUpFragment(), true);
    }

}
