package com.example.nguyenduy.projectbase.screen.main2;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.example.nguyenduy.projectbase.R;
import com.example.nguyenduy.projectbase.base.drawerlayout.BaseActivityWithHeaderUserDrawerLayout;
import com.example.nguyenduy.projectbase.base.drawerlayout.ToolbarUtils;
import com.example.nguyenduy.projectbase.utils.data.SharedPreference.SharedPreferenceUtils;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseActivityWithHeaderUserDrawerLayout<IMainPresenter> implements IMainView, ToolbarUtils.IToolbarListener {

    @BindView(R.id.fl_drawer_layout_content)
    FrameLayout contentDrawerLayout;

    @Override
    public int getIdLayout() {
        return R.layout.activity_drawer_layout;
    }

    @Override
    public IMainPresenter initPresenter() {
        return new MainPresenterImp(this);
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

    @Override
    public int getIdMenuDrawerLayout() {
        return R.menu.drawer_layout_menu;
    }

    @Override
    public int getIdArrayTitleMenuDrawerLayout() {
        return R.array.navigation_drawer_title_menu_toolbar;
    }

    @Override
    public boolean hasToolbarDrawerLayout() {
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        new ToolbarUtils(this, this, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

    }


    @Override
    public int getIdToolbar() {
        return R.menu.toolbar_menu;
    }

    @Override
    public void onNavigationItemSelectedDrawerLayout(int idItem) {
        switch (idItem) {
            case R.id.nav_camera:
                showToast("nav_camera");
                break;
            case R.id.nav_gallery:
                showToast("nav_gallery");
                break;
            case R.id.nav_slideshow:
                showToast("nav_slideshow");
                break;
            case R.id.nav_manage:
                showToast("nav_manage");
                break;
            case R.id.nav_share:
                showToast("nav_share");
                break;
            case R.id.nav_send:
                showToast("nav_send");
                break;
        }
    }

    @OnClick(R.id.btn_logout)
    public void onClickButtonLogout() {
        SharedPreferenceUtils.getInstance().clearUserInformation();
        //  startRootActivity(StartActivity.class);
    }

    @OnClick(R.id.btn_login)
    public void onClickLogin() {
        /*UserInformation user = new UserInformation()
                .setId("123")
                .setUsername("Nguyễn Ngọc Duy")
                .setEmail("duynn03@gmail.com");
        SharedPreferenceUtils.getInstance().setUserInformation(user);*/
        setCountMenuDrawerLayout(R.id.nav_camera, 80);
    }
}
