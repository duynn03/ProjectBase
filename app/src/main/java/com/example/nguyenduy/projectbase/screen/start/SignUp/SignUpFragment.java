package com.example.nguyenduy.projectbase.screen.start.SignUp;

import com.example.nguyenduy.projectbase.R;
import com.example.nguyenduy.projectbase.base.BaseFragment;
import com.example.nguyenduy.projectbase.base.IBasePresenter;

import butterknife.OnClick;

public class SignUpFragment extends BaseFragment<ISignUpPresenter> implements ISignUpView {

    @Override
    public int getIdLayout() {
        return R.layout.fragment_sign_up;
    }

    @Override
    public IBasePresenter initPresenter() {
        return new SignUpPresenterImp(this);
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

    @OnClick(R.id.btn_sign_up_sign_up)
    public void onClickButtonSignUp() {
        getPresenter().signUp();
    }

    @OnClick(R.id.btn_login_sign_up)
    public void onClickLoginNow() {

    }

}
