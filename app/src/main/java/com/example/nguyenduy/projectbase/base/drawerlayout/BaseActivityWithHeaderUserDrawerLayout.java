package com.example.nguyenduy.projectbase.base.drawerlayout;

import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.nguyenduy.projectbase.R;
import com.example.nguyenduy.projectbase.base.IBasePresenter;
import com.example.nguyenduy.projectbase.utils.data.SharedPreference.SharedPreferenceUtils;
import com.example.nguyenduy.projectbase.utils.data.SharedPreference.UserInformation;
import com.example.nguyenduy.projectbase.utils.method.ViewUtils;

public abstract class BaseActivityWithHeaderUserDrawerLayout<P extends IBasePresenter> extends BaseActivityWithDrawerLayout<P> {

    private RelativeLayout header;

    private ImageView avatar;

    private TextView userName;

    private TextView userEmail;

    @Override
    public int getIdHeaderDrawerLayout() {
        return R.layout.drawer_layout_header;
    }

    @Override
    protected void initBaseView() {
        super.initBaseView();
        findViewById();
        DrawerLayoutView view = new DrawerLayoutView();
        view.setViewHeader(header);
        view.setViewAvatarUser(avatar);
        view.setViewUserName(userName);
        view.setViewUserEmail(userEmail);
    }

    private void findViewById() {
        header = getHeader().findViewById(R.id.drawer_layout_header);
        avatar = getHeader().findViewById(R.id.im_user_avatar);
        userName = getHeader().findViewById(R.id.tv_user_name);
        userEmail = getHeader().findViewById(R.id.tv_user_email);
    }

    @Override
    protected void initBaseComponents() {
        super.initBaseComponents();
        updateUserInformation();
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
        ViewUtils.setText(userName, name);
    }

    private void setEmail(String email) {
        ViewUtils.setText(userEmail, email);
    }

    private void setAvatar(String url) {
        ViewUtils.setImage(avatar, url, R.mipmap.ic_launcher_round);
    }

}
