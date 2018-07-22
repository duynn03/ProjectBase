package com.example.nguyenduy.projectbase.base.drawerlayout;

import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.nguyenduy.projectbase.R;
import com.example.nguyenduy.projectbase.utils.method.MethodContextUtils;

public class DrawerLayoutUtils implements NavigationView.OnNavigationItemSelectedListener {

    private AppCompatActivity mActivity;
    private IDrawerLayoutListener mListener;

    private DrawerLayout mDrawer;
    private Toolbar mToolbar;
    private ActionBarDrawerToggle mToggle;
    private NavigationView mNavigation;

    public interface IDrawerLayoutListener {

        int getIdDrawerLayout();

        int getIdHeaderDrawerLayout();

        int getIdMenuDrawerLayout();

        int getIdToolbar();

        int getIdNavigationView();

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
        this.mDrawer = mActivity.findViewById(mListener.getIdDrawerLayout());
        this.mToolbar = mActivity.findViewById(mListener.getIdToolbar());
        mNavigation = mActivity.findViewById(mListener.getIdNavigationView());
    }

    private void initViews() {
        // add header
        if (mListener.getIdHeaderDrawerLayout() > 0) {
            mNavigation.addHeaderView(MethodContextUtils.createView(mActivity, mListener.getIdHeaderDrawerLayout()));
        }
        // add menu
        mNavigation.inflateMenu(mListener.getIdMenuDrawerLayout());

        // set view
        DrawerLayoutView view = new DrawerLayoutView();
        view.setViewHeader();
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
        mNavigation.setNavigationItemSelectedListener(this);
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
        closeDrawer();
        return true;
    }

}
