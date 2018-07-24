package com.example.nguyenduy.projectbase.base.drawerlayout;

import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nguyenduy.projectbase.R;
import com.example.nguyenduy.projectbase.utils.method.ViewUtils;

public class DrawerLayoutView {

    public void setViewHeader(ViewGroup view) {
        ViewUtils.setPaddingNormal(view, R.dimen.activity_horizontal_margin, R.dimen.drawer_layout_header_padding_top, R.dimen.activity_horizontal_margin, R.dimen.activity_vertical_margin);
    }

    public void setViewAvatarUser(ImageView view) {
        ViewUtils.setWidthHeightImage(view, R.dimen.drawer_layout_header_avatar_user_width_height, R.dimen.drawer_layout_header_avatar_user_width_height);
    }

    public void setViewUserName(TextView view) {
        ViewUtils.setPaddingNormal(view, 0, R.dimen.drawer_layout_header_vertical_spacing, 0, 0);
    }

    public void setViewUserEmail(TextView view) {
        ViewUtils.setPaddingNormal(view, 0, R.dimen.drawer_layout_header_vertical_spacing, 0, 0);
    }

    public void setViewMenu() {

    }
}
