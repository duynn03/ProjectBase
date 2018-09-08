package com.example.nguyenduy.projectbase.screen.start.login;

import com.example.nguyenduy.projectbase.base.BasePresenterImp;
import com.example.nguyenduy.projectbase.base.sharedPreference.SharedPreferenceUtils;
import com.example.nguyenduy.projectbase.base.sharedPreference.UserInformation;

class LoginPresenterImp extends BasePresenterImp<ILoginView> implements ILoginPresenter {

    LoginPresenterImp(ILoginView view) {
        super(view);
    }
}
