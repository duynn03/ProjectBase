package com.example.nguyenduy.projectbase.screen.main2;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.example.nguyenduy.projectbase.R;
import com.example.nguyenduy.projectbase.base.drawerlayout.BaseActivityWithHeaderUserDrawerLayout;
import com.example.nguyenduy.projectbase.base.drawerlayout.ToolbarUtils;
import com.example.nguyenduy.projectbase.screen.main2.appcenter.AppCenterFragment;
import com.example.nguyenduy.projectbase.screen.main2.database.RealmFragment;
import com.example.nguyenduy.projectbase.screen.main2.firebase.FirebaseFragment;
import com.example.nguyenduy.projectbase.screen.main2.home.HomeFragment;
import com.example.nguyenduy.projectbase.screen.main2.listener.handshake.HandshakeFragment;
import com.example.nguyenduy.projectbase.screen.main2.notification.NotificationFragment;
import com.example.nguyenduy.projectbase.screen.main2.permission.PermissionFragment;
import com.example.nguyenduy.projectbase.screen.main2.sharedpreference.SharedPreferenceFragment;
import com.example.nguyenduy.projectbase.utils.data.SharedPreference.SharedPreferenceUtils;
import com.example.nguyenduy.projectbase.utils.data.SharedPreference.UserInformation;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseActivityWithHeaderUserDrawerLayout<IMainPresenter> implements IMainView, ToolbarUtils.IToolbarListener {

    @BindView(R.id.fl_content)
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
        setSelectMenu(R.id.menu_home);
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
        // init toolbar
        new ToolbarUtils(this, this, menu);
        return true;
    }

    // handle toolbar
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.toolbar_help:
                showToast("toolbar_help");
                return true;
            case R.id.action_settings:
                showToast("action_settings");
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public int getIdToolbar() {
        return R.menu.toolbar_menu;
    }

    @Override
    public void onNavigationItemSelectedDrawerLayout(int idItem) {
        switch (idItem) {
            case R.id.menu_home:
                replaceFragment(new HomeFragment(), false);
                break;
            case R.id.menu_permission:
                replaceFragment(new PermissionFragment(), false);
                break;
            case R.id.menu_sharedpreference:
                replaceFragment(new SharedPreferenceFragment(), false);
                break;
            case R.id.menu_notification:
                replaceFragment(new NotificationFragment(), false);
                break;
            case R.id.menu_firebase:
                replaceFragment(new FirebaseFragment(), false);
                break;
            case R.id.menu_appcenter:
                replaceFragment(new AppCenterFragment(), false);
                break;
            case R.id.menu_hand_shake:
                replaceFragment(new HandshakeFragment(), false);
                break;
            case R.id.menu_realm:
                replaceFragment(new RealmFragment(), false);
                break;
            case R.id.menu_share:
                showToast("menu_share");
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
        UserInformation user = new UserInformation()
                .setId("123")
                .setUsername("Nguyễn Ngọc Duy")
                .setEmail("duynn03@gmail.com");
        SharedPreferenceUtils.getInstance().setUserInformation(user);
    }
}
