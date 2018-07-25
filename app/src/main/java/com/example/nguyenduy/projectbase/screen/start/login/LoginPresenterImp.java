package com.example.nguyenduy.projectbase.screen.start.login;

import com.example.nguyenduy.projectbase.base.BasePresenterImp;
import com.example.nguyenduy.projectbase.utils.data.SharedPreference.SharedPreferenceUtils;
import com.example.nguyenduy.projectbase.utils.data.SharedPreference.UserInformation;

class LoginPresenterImp extends BasePresenterImp<ILoginView> implements ILoginPresenter {

    LoginPresenterImp(ILoginView view) {
        super(view);
    }

    @Override
    public void login() {
        UserInformation user = new UserInformation()
                .setId("123")
                .setUsername("Nguyễn Ngọc Duy")
                .setEmail("duynn03@gmail.com");
        SharedPreferenceUtils.getInstance().setUserInformation(user);
    }
}
