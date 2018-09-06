package com.example.nguyenduy.projectbase.screen.start.login;

import com.example.nguyenduy.projectbase.base.BasePresenterImp;
import com.example.nguyenduy.projectbase.base.sharedPreference.SharedPreferenceUtils;
import com.example.nguyenduy.projectbase.base.sharedPreference.UserInformation;

class LoginPresenterImp extends BasePresenterImp<ILoginView> implements ILoginPresenter {

    LoginPresenterImp(ILoginView view) {
        super(view);
    }

    @Override
    public void login() {
        UserInformation user = new UserInformation()
                .setId("123")
                .setName("Nguyễn Ngọc Duy")
                .setEmail("duynn03@gmail.com");
        SharedPreferenceUtils.getInstance().setUserInformation(user);
    }
}
