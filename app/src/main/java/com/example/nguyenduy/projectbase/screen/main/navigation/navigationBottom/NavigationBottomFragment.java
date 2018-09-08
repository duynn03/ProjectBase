package com.example.nguyenduy.projectbase.screen.main.navigation.navigationBottom;

import com.example.nguyenduy.projectbase.R;
import com.example.nguyenduy.projectbase.base.IBasePresenter;
import com.example.nguyenduy.projectbase.base.navigation.navigationBottom.BaseFragmentWithNavigationBottom;
import com.example.nguyenduy.projectbase.screen.main.navigation.navigationBottom.handshake.HandshakeNavigationBottomFragment;
import com.example.nguyenduy.projectbase.screen.main.navigation.navigationBottom.home.HomeNavigationBottomFragment;
import com.example.nguyenduy.projectbase.screen.main.navigation.navigationBottom.notification.NotificationNavigationBottomFragment;

public class NavigationBottomFragment extends BaseFragmentWithNavigationBottom<INavigationBottomPresenter> implements INavigationBottomView {

    @Override
    public int getIdLayout() {
        return R.layout.fragment_navigation_bottom;
    }

    @Override
    public IBasePresenter initPresenter() {
        return new NavigationBottomPresenterImp(this);
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
        setSelectMenuNavigationBottom(R.id.navigation_bottom_notification);
    }

    @Override
    public int getIdMenuNavigationBottom() {
        return R.menu.navigation_bottom;
    }

    @Override
    public int getIdArrayTitleToolbarNavigationBottom() {
        return R.array.navigation_bottom_title_menu_toolbar;
    }

    @Override
    public void onNavigationItemSelectedNavigationBottom(int idItem) {
        switch (idItem) {
            case R.id.navigation_bottom_home:
                replaceFragment(new HomeNavigationBottomFragment(), false);
                break;
            case R.id.navigation_bottom_notification:
                replaceFragment(new NotificationNavigationBottomFragment(), false);
                break;
            case R.id.navigation_bottom_hand_shake:
                replaceFragment(new HandshakeNavigationBottomFragment(), false);
                break;
        }
    }
}
