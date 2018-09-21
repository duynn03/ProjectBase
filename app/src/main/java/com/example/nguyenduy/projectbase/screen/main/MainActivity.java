package com.example.nguyenduy.projectbase.screen.main;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.Menu;
import android.view.MenuItem;

import com.example.nguyenduy.projectbase.R;
import com.example.nguyenduy.projectbase.base.drawerlayout.BaseActivityWithHeaderUserDrawerLayout;
import com.example.nguyenduy.projectbase.base.drawerlayout.OptionMenuUtils;
import com.example.nguyenduy.projectbase.base.firebase.FirebaseAuthen;
import com.example.nguyenduy.projectbase.base.sharedPreference.SharedPreferenceUtils;
import com.example.nguyenduy.projectbase.screen.main.appbar.appbarBottom.AppbarBottomActivity;
import com.example.nguyenduy.projectbase.screen.main.appcenter.AppCenterFragment;
import com.example.nguyenduy.projectbase.screen.main.architectureComponents.butterKnife.ButterKnifeFragment;
import com.example.nguyenduy.projectbase.screen.main.architectureComponents.dataBinding.DataBindingFragment;
import com.example.nguyenduy.projectbase.screen.main.architectureComponents.dragger.DraggerFragment;
import com.example.nguyenduy.projectbase.screen.main.architectureComponents.lifecycle.LifecycleFragment;
import com.example.nguyenduy.projectbase.screen.main.architectureComponents.liveData.LiveDataFragment;
import com.example.nguyenduy.projectbase.screen.main.architectureComponents.paging.PagingFragment;
import com.example.nguyenduy.projectbase.screen.main.architectureComponents.room.RoomFragment;
import com.example.nguyenduy.projectbase.screen.main.architectureComponents.viewModel.ViewModelFragment;
import com.example.nguyenduy.projectbase.screen.main.backgroundTask.service.ServiceFragment;
import com.example.nguyenduy.projectbase.screen.main.backgroundTask.thread.ThreadFragment;
import com.example.nguyenduy.projectbase.screen.main.broadcast.BroadcastFragment;
import com.example.nguyenduy.projectbase.screen.main.database.RealmFragment;
import com.example.nguyenduy.projectbase.screen.main.firebase.FirebaseFragment;
import com.example.nguyenduy.projectbase.screen.main.home.HomeFragment;
import com.example.nguyenduy.projectbase.screen.main.listView.recycleView.RecycleViewFragment;
import com.example.nguyenduy.projectbase.screen.main.listener.handshake.HandshakeFragment;
import com.example.nguyenduy.projectbase.screen.main.listener.network.FragmentNetwork;
import com.example.nguyenduy.projectbase.screen.main.location.detectActivityUser.DetectActivityUserFragment;
import com.example.nguyenduy.projectbase.screen.main.location.geofence.GeofenceFragment;
import com.example.nguyenduy.projectbase.screen.main.location.location.LocationFragment;
import com.example.nguyenduy.projectbase.screen.main.navigation.dialog.DialogFragment;
import com.example.nguyenduy.projectbase.screen.main.navigation.navigationBottom.NavigationBottomFragment;
import com.example.nguyenduy.projectbase.screen.main.navigation.notification.NotificationNavigationFragment;
import com.example.nguyenduy.projectbase.screen.main.navigation.snackbar.SnackBarFragment;
import com.example.nguyenduy.projectbase.screen.main.notification.NotificationFragment;
import com.example.nguyenduy.projectbase.screen.main.permission.PermissionFragment;
import com.example.nguyenduy.projectbase.screen.main.retrofit.RetrofitFragment;
import com.example.nguyenduy.projectbase.screen.main.sharedpreference.SharedPreferenceFragment;
import com.example.nguyenduy.projectbase.screen.main.toolbar.CollapsingToolbarActivity;
import com.example.nguyenduy.projectbase.screen.main.toolbar.CollapsingToolbarCustomBehaviorActivity;
import com.example.nguyenduy.projectbase.screen.start.StartActivity;
import com.example.nguyenduy.projectbase.utils.method.ResourceUtils;

public class MainActivity extends BaseActivityWithHeaderUserDrawerLayout<IMainPresenter> implements IMainView, OptionMenuUtils.IOptionMenuListener {

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
        setSelectMenuDrawerLayout(R.id.menu_home);
    }

    @Override
    public int getIdMenuDrawerLayout() {
        return R.menu.drawer_layout_menu;
    }

    @Override
    public int getIdArrayTitleToolbarDrawerLayout() {
        return R.array.navigation_drawer_title_menu_toolbar;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // init toolbar
        new OptionMenuUtils(this, this, menu);
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
        return R.menu.option_menu;
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
            case R.id.menu_broadcast:
                replaceFragment(new BroadcastFragment(), false);
                break;
            case R.id.menu_firebase:
                replaceFragment(new FirebaseFragment(), false);
                break;
            case R.id.menu_appcenter:
                replaceFragment(new AppCenterFragment(), false);
                break;
            case R.id.menu_appbar_bottom:
                startActivity(AppbarBottomActivity.class, null);
                break;
            case R.id.menu_navigation_bottom:
                replaceFragment(new NavigationBottomFragment(), false);
                break;
            case R.id.menu_navigation_notification:
                replaceFragment(new NotificationNavigationFragment(), false);
                break;
            case R.id.menu_snack_bar:
                replaceFragment(new SnackBarFragment(), false);
                break;
            case R.id.menu_toolbar_collapsing:
                startActivity(CollapsingToolbarActivity.class, null);
                break;
            case R.id.menu_toolbar_collapsing_custom_behavior:
                startActivity(CollapsingToolbarCustomBehaviorActivity.class, null);
                break;
            case R.id.menu_dialog:
                replaceFragment(new DialogFragment(), false);
                break;
            case R.id.menu_hand_shake:
                replaceFragment(new HandshakeFragment(), false);
                break;
            case R.id.menu_network:
                replaceFragment(new FragmentNetwork(), false);
                break;
            case R.id.menu_realm:
                replaceFragment(new RealmFragment(), false);
                break;
            case R.id.menu_location:
                replaceFragment(new LocationFragment(), false);
                break;
            case R.id.menu_geofence:
                replaceFragment(new GeofenceFragment(), false);
                break;
            case R.id.menu_detect_activity_user:
                replaceFragment(new DetectActivityUserFragment(), false);
                break;
            case R.id.menu_dragger:
                replaceFragment(new DraggerFragment(), false);
                break;
            case R.id.menu_data_binding:
                replaceFragment(new DataBindingFragment(), false);
                break;
            case R.id.menu_butter_knife:
                replaceFragment(new ButterKnifeFragment(), false);
                break;
            case R.id.menu_lifecycle:
                replaceFragment(new LifecycleFragment(), false);
                break;
            case R.id.menu_room:
                replaceFragment(new RoomFragment(), false);
                break;
            case R.id.menu_live_data:
                replaceFragment(new LiveDataFragment(), false);
                break;
            case R.id.menu_view_model:
                replaceFragment(new ViewModelFragment(), false);
                break;
            case R.id.menu_paging:
                replaceFragment(new PagingFragment(), false);
                break;
            case R.id.menu_thread:
                replaceFragment(new ThreadFragment(), false);
                break;
            case R.id.menu_service:
                replaceFragment(new ServiceFragment(), false);
                break;
            case R.id.menu_recycle:
                replaceFragment(new RecycleViewFragment(), false);
                break;
            case R.id.menu_retrofit:
                replaceFragment(new RetrofitFragment(), false);
                break;
            case R.id.menu_share:
                showToast("menu_share");
                break;
            case R.id.menu_signout:
                onClickButtonSignout();
                break;
        }
    }

    private void onClickButtonSignout() {
        new AlertDialog.Builder(this)
                .setTitle(ResourceUtils.getString(R.string.dialog_signout_title))
                .setIcon(R.drawable.ic_splash)
                .setMessage(ResourceUtils.getString(R.string.dialog_signout_message))
                .setCancelable(true)
                .setPositiveButton(ResourceUtils.getString(R.string.dialog_signout_positive_button), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        SharedPreferenceUtils.getInstance().clearUserInformation();
                        new FirebaseAuthen().signOut();
                        startRootActivity(StartActivity.class);
                    }
                })
                .setNegativeButton(ResourceUtils.getString(R.string.cancel), null)
                .create()
                .show();
    }
}
