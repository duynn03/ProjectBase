package com.example.nguyenduy.projectbase.base.drawerlayout;

import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.nguyenduy.projectbase.R;
import com.example.nguyenduy.projectbase.utils.method.MethodUtils;
import com.example.nguyenduy.projectbase.utils.method.ResourceUtils;
import com.example.nguyenduy.projectbase.utils.method.ViewUtils;

public class DrawerLayoutUtils implements NavigationView.OnNavigationItemSelectedListener, DrawerLayout.DrawerListener {

    private AppCompatActivity mActivity;
    private IDrawerLayoutListener mListener;

    private DrawerLayout mDrawerLayout;
    private AppBarLayout mAppBar;
    private CollapsingToolbarLayout collapsingToolbar;
    private Toolbar mToolbar;
    private String[] titleMenuToolbars;
    private NavigationView mNavigation;
    private FrameLayout mContent;

    public interface IDrawerLayoutListener {

        int getIdHeaderDrawerLayout();

        int getIdMenuDrawerLayout();

        int getIdArrayTitleToolbarDrawerLayout();

        void onNavigationItemSelectedDrawerLayout(int idItem);
    }

    DrawerLayoutUtils(AppCompatActivity activity, IDrawerLayoutListener listener) {
        mActivity = activity;
        mListener = listener;
        findViewById();
        initViews();
        initComponents();
        setEvents();
        prepareComplete();
    }

    private void findViewById() {
        mDrawerLayout = mActivity.findViewById(R.id.drawer_layout);
        mNavigation = mActivity.findViewById(R.id.navigation_view);
        mContent = mActivity.findViewById(R.id.fl_content);
        mAppBar = mActivity.findViewById(R.id.appbar);
        mToolbar = mActivity.findViewById(R.id.toolbar);
        collapsingToolbar = mActivity.findViewById(R.id.collapsing_toolbar);
    }

    private boolean hasHeaderDrawerLayout() {
        return mListener.getIdHeaderDrawerLayout() > 0;
    }

    private void initViews() {
        // add header
        if (hasHeaderDrawerLayout()) {
            mNavigation.addHeaderView(ViewUtils.createView(mActivity, mListener.getIdHeaderDrawerLayout()));
        }
        // add menu
        mNavigation.inflateMenu(mListener.getIdMenuDrawerLayout());
        new DrawerLayoutView().setViewMenu();
    }

    private void initComponents() {
        // add Toolbar vào app
        mActivity.setSupportActionBar(mToolbar);
        // init toggle
        ActionBarDrawerToggle mToggle = new ActionBarDrawerToggle(mActivity, mDrawerLayout, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();
    }

    public void setCountMenu(int idMenu, int count) {
        TextView counter = (TextView) mNavigation.getMenu().findItem(idMenu).getActionView();
        if (count > 0)
            ViewUtils.setText(counter, count < 100 ? count : "99+");
        else ViewUtils.setText(counter, "");
    }

    private void setEvents() {
        mDrawerLayout.addDrawerListener(this);
        mNavigation.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {
        // kéo ra thì mContent cũng kéo theo
        //mContent.setTranslationX(mContent.getWidth() * slideOffset);
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
        return mDrawerLayout.isDrawerOpen(GravityCompat.START);
    }

    public void closeDrawer() {
        if (isDrawerOpen()) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        }
    }

    public void openDrawer() {
        if (!isDrawerOpen()) {
            mDrawerLayout.openDrawer(GravityCompat.START);
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        mListener.onNavigationItemSelectedDrawerLayout(item.getItemId());
        setTitleToolbar(item.getItemId());
        closeDrawer();
        return true;
    }

    /* chỉ support title 2 level*/
    private void setTitleToolbar(int idMenu) {
        int indexMenu = findIndexMenu(idMenu);
        if (MethodUtils.isEmpty(titleMenuToolbars))
            getTitleMenuToolbar();

        if (indexMenu != -1 && titleMenuToolbars.length > indexMenu && !TextUtils.isEmpty(titleMenuToolbars[indexMenu]))
            if (null != collapsingToolbar)
                collapsingToolbar.setTitle(titleMenuToolbars[indexMenu]);
            else
                mActivity.getSupportActionBar().setTitle(titleMenuToolbars[indexMenu]);
    }

    private MenuItem findMenu(int idMenu) {
        return mNavigation.getMenu().findItem(idMenu);
    }

    public void setSelectMenu(int idMenu) {
        onNavigationItemSelected(findMenu(idMenu));
        mNavigation.setCheckedItem(idMenu); // highlight menu
    }

    private int findIndexMenu(int idMenu) {
        int index = 0;
        MenuItem item;
        SubMenu subMenu;
        for (int i = 0; i < mNavigation.getMenu().size(); i++) {
            item = mNavigation.getMenu().getItem(i);
            if (!isGroupItem(item)) {
                if (idMenu == item.getItemId()) return index;
                else index++;
            } else {
                subMenu = item.getSubMenu();
                int indexSubMenu = findIndexInSubMenu(idMenu, subMenu);
                if (indexSubMenu != -1) return index + indexSubMenu;
                else index += subMenu.size();
            }
        }
        return -1;
    }

    private boolean isGroupItem(MenuItem subMenu) {
        return subMenu.hasSubMenu();
    }

    private int findIndexInSubMenu(int idMenu, SubMenu subMenu) {
        for (int i = 0; i < subMenu.size(); i++) {
            if (idMenu == subMenu.getItem(i).getItemId()) return i;
        }
        return -1;
    }

    private void getTitleMenuToolbar() {
        titleMenuToolbars = ResourceUtils.getStringArray(mListener.getIdArrayTitleToolbarDrawerLayout());
    }

    public View getHeader() {
        return mNavigation.getHeaderView(0);
    }

}
