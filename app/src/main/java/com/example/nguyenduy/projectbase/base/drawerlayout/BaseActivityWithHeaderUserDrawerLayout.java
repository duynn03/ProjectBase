package com.example.nguyenduy.projectbase.base.drawerlayout;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.nguyenduy.projectbase.R;
import com.example.nguyenduy.projectbase.base.IBasePresenter;
import com.example.nguyenduy.projectbase.utils.Constants;
import com.example.nguyenduy.projectbase.utils.data.SharedPreference.SharedPreferenceUtils;
import com.example.nguyenduy.projectbase.utils.data.SharedPreference.UserInformation;
import com.example.nguyenduy.projectbase.utils.method.MethodContextUtils;

import butterknife.BindView;

public abstract class BaseActivityWithHeaderUserDrawerLayout<P extends IBasePresenter> extends BaseActivityWithDrawerLayout<P> implements SharedPreferences.OnSharedPreferenceChangeListener {

    @BindView(R.id.item_drawer_layout_header)
    LinearLayout header;

    @BindView(R.id.im_user_avatar)
    ImageView avatar;

    @BindView(R.id.tv_user_name)
    TextView userName;

    @BindView(R.id.tv_user_email)
    TextView userEmail;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferenceUtils.getInstance().registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public int getIdHeaderDrawerLayout() {
        return R.layout.drawer_layout_header;
    }

    @Override
    protected void initBaseView() {
        super.initBaseView();
        DrawerLayoutView view = new DrawerLayoutView();
        view.setViewHeader();
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        if (Constants.SharedPreference.USER_INFORMATION.equals(key)) {
            updateUserInformation();
        }
    }

    private void updateUserInformation() {
        UserInformation user = SharedPreferenceUtils.getInstance().getUserInformation();
        if (null == user) {
            clearUserInformation();
            return;
        }
        setName(user.getUsername());
        setEmail(user.getEmail());
        setAvatar(user.getAvatarUrl());
    }

    private void clearUserInformation() {
        setName("");
        setEmail("");
        setAvatar("");
    }

    private void setName(String name) {
        MethodContextUtils.setText(userName, name);
    }

    private void setEmail(String email) {
        MethodContextUtils.setText(userEmail, email);
    }

    private void setAvatar(String url) {
        MethodContextUtils.loadImage(avatar, url, R.mipmap.ic_launcher_round);
    }

    @Override
    protected void onDestroy() {
        SharedPreferenceUtils.getInstance().unregisterOnSharedPreferenceChangeListener(this);
        super.onDestroy();
    }
}
