package com.example.nguyenduy.projectbase.screen;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.nguyenduy.projectbase.R;
import com.example.nguyenduy.projectbase.base.BaseActivity;
import com.example.nguyenduy.projectbase.base.IBasePresenter;
import com.example.nguyenduy.projectbase.base.drawerlayout.DrawerLayoutUtils;

public class DrawerLayoutActivity<P extends IBasePresenter> extends BaseActivity<P> implements DrawerLayoutUtils.IDrawerLayoutListener {

    private DrawerLayoutUtils drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen()) drawerLayout.closeDrawer();
        else super.onBackPressed();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.drawer_layout, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public int getIdDrawerLayout() {
        return R.id.drawer_layout;
    }

    @Override
    public int getIdHeaderDrawerLayout() {
        return R.layout.activity_drawer_layout_header;
    }

    @Override
    public int getIdMenuDrawerLayout() {
        return R.menu.activity_drawer_layout_menu;
    }

    @Override
    public int getIdToolbar() {
        return R.id.toolbar;
    }

    @Override
    public int getIdNavigationView() {
        return R.id.nav_view;
    }

    @Override
    public void onNavigationItemSelected(int idItem) {
        switch (idItem) {
            case R.id.nav_camera:
                break;
            case R.id.nav_gallery:
                break;
            case R.id.nav_slideshow:
                break;
            case R.id.nav_manage:
                break;
            case R.id.nav_share:
                break;
            case R.id.nav_send:
                break;
        }
    }

    @Override
    public int getIdLayout() {
        return R.layout.activity_drawer_layout;
    }

    @Override
    public <P extends IBasePresenter> P initPresenter() {
        return null;
    }

    @Override
    public void initViews() {
        drawerLayout = new DrawerLayoutUtils(this, this);
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
}
