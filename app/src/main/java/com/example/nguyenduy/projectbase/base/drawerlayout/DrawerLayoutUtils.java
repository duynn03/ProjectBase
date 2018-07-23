package com.example.nguyenduy.projectbase.base.drawerlayout;

import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import com.example.nguyenduy.projectbase.R;
import com.example.nguyenduy.projectbase.utils.method.MethodContextUtils;

import java.util.List;

public class DrawerLayoutUtils implements NavigationView.OnNavigationItemSelectedListener, DrawerLayout.DrawerListener {

    private AppCompatActivity mActivity;
    private IDrawerLayoutListener mListener;

    private DrawerLayout mDrawer;
    private AppBarLayout mAppBar;
    private Toolbar mToolbar;
    private ActionBarDrawerToggle mToggle;
    private NavigationView mNavigation;
    private FrameLayout mContent;

    public interface IDrawerLayoutListener {

        int getIdHeaderDrawerLayout();

        int getIdMenuDrawerLayout();

        List<ItemMenuDrawerLayout> getListMenuDrawerLayout();

        boolean hasToolbarDrawerLayout();

        void onNavigationItemSelected(int idItem);
    }

    public DrawerLayoutUtils(AppCompatActivity activity, IDrawerLayoutListener listener) {
        mActivity = activity;
        mListener = listener;
        findViewById();
        initViews();
        initComponents();
        setEvents();
        prepareComplete();
    }

    private void findViewById() {
        mDrawer = mActivity.findViewById(R.id.drawer_layout);
        mNavigation = mActivity.findViewById(R.id.navigation_view);
        mContent = mActivity.findViewById(R.id.fl_drawer_layout_content);
        mAppBar = mActivity.findViewById(R.id.appbar);
        mToolbar = mActivity.findViewById(R.id.toolbar);
    }

    private boolean hasHeaderDrawerLayout() {
        return mListener.getIdHeaderDrawerLayout() > 0;
    }

    private boolean hasMenuDrawerLayout() {
        return mListener.getIdMenuDrawerLayout() > 0;
    }

    private boolean hasToolbarDrawerLayout() {
        return mListener.hasToolbarDrawerLayout();
    }

    private void initViews() {
        DrawerLayoutView view = new DrawerLayoutView();
        // add header
        if (hasHeaderDrawerLayout()) {
            mNavigation.addHeaderView(MethodContextUtils.createView(mActivity, mListener.getIdHeaderDrawerLayout()));
        }
        // add menu
        if (hasMenuDrawerLayout()) {
            mNavigation.inflateMenu(mListener.getIdMenuDrawerLayout());
            view.setViewMenu();
        }
        // toolbar
        MethodContextUtils.setVisibility(mAppBar, hasToolbarDrawerLayout() ? View.VISIBLE : View.GONE);
    }

    private void initComponents() {
        // add Toolbar vào app
        mActivity.setSupportActionBar(mToolbar);
        // init toggle
        mToggle = new ActionBarDrawerToggle(mActivity, mDrawer, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawer.addDrawerListener(mToggle);
        mToggle.syncState();
    }

    private void setEvents() {
        mDrawer.addDrawerListener(this);
        if (hasMenuDrawerLayout())
            mNavigation.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {
        // kéo ra thì mContent cũng kéo theo
        mContent.setTranslationX(mContent.getWidth() * slideOffset);
    }

    @Override
    public void onDrawerOpened(@NonNull View drawerView) {

    }

    @Override
    public void onDrawerClosed(@NonNull View drawerView) {

    }

    @Override
    public void onDrawerStateChanged(int newState) {

    }

    private void prepareComplete() {

    }

    public boolean isDrawerOpen() {
        return mDrawer.isDrawerOpen(GravityCompat.START);
    }

    public void closeDrawer() {
        if (isDrawerOpen()) {
            mDrawer.closeDrawer(GravityCompat.START);
        }
    }

    public void openDrawer() {
        if (!isDrawerOpen()) {
            mDrawer.openDrawer(GravityCompat.START);
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        mListener.onNavigationItemSelected(item.getItemId());
        setTitleToolbar(item.getItemId());
        closeDrawer();
        return true;
    }

    private void setTitleToolbar(int idMenu) {
        int indexMenu = findIndexMenuInListMenuDrawerLayout(idMenu);
        if (indexMenu != -1)
            mToolbar.setTitle(mListener.getListMenuDrawerLayout().get(indexMenu).getNameMenu());
    }

    private int findIndexMenuInListMenuDrawerLayout(int idMenu) {
        for (int i = 0; i < mListener.getListMenuDrawerLayout().size(); i++) {
            if (idMenu == mListener.getListMenuDrawerLayout().get(i).getIdMenu()) return i;
        }
        return -1;
    }

    public View getHeader() {
        return mNavigation.getHeaderView(0);
    }

}
