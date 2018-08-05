package com.example.nguyenduy.projectbase.base.navigation.navigationBottom;

import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.MenuItem;

import com.example.nguyenduy.projectbase.R;
import com.example.nguyenduy.projectbase.base.BaseFragment;
import com.example.nguyenduy.projectbase.utils.method.MethodUtils;
import com.example.nguyenduy.projectbase.utils.method.ResourceUtils;

public class NavigationBottomUtils implements BottomNavigationView.OnNavigationItemSelectedListener {

    private AppCompatActivity mActivity;
    private BaseFragment mFragment;
    private INavigationBottomListener mListener;
    private BottomNavigationView mNavigation;
    private CollapsingToolbarLayout collapsingToolbar;
    private String[] titleMenuToolbars;

    NavigationBottomUtils(BaseFragment fragment, INavigationBottomListener listener) {
        mListener = listener;
        mFragment = fragment;
        mActivity = mFragment.getRootActivity();
        findViewById();
        initViews();
        initComponents();
        setEvents();
        prepareComplete();
    }

    public interface INavigationBottomListener {

        int getIdMenuNavigationBottom();

        // nếu không cần title toolbar thì return về 0
        int getIdArrayTitleToolbarNavigationBottom();

        void onNavigationItemSelectedNavigationBottom(int idItem);
    }

    private void findViewById() {
        mNavigation = (BottomNavigationView) mFragment.findViewById(R.id.navigation_bottom);
        collapsingToolbar = mActivity.findViewById(R.id.collapsing_toolbar);
    }

    private void initViews() {
        // add menu
        mNavigation.inflateMenu(mListener.getIdMenuNavigationBottom());
        attachBehavior();
    }

    // attaching bottom sheet behaviour - hide / show on scroll
    private void attachBehavior() {
        // ((CoordinatorLayout.LayoutParams) mNavigation.getLayoutParams()).setBehavior(new BottomNavigationBehavior());
        // if (null != mActivity.findViewById(R.id.fl_content))
        //    ((CoordinatorLayout.LayoutParams) mActivity.findViewById(R.id.fl_content).getLayoutParams()).setBehavior(null);
    }

    private void initComponents() {

    }

    private void setEvents() {
        mNavigation.setOnNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        mListener.onNavigationItemSelectedNavigationBottom(menuItem.getItemId());
        setTitleToolbar(menuItem.getItemId());
        return true;
    }

    /* chỉ support title 2 level*/
    private void setTitleToolbar(int idMenu) {
        int indexMenu = findIndexMenu(idMenu);
        if (MethodUtils.isEmpty(titleMenuToolbars))
            getTitleMenuToolbar();

        if (indexMenu != -1 && null != titleMenuToolbars && titleMenuToolbars.length > indexMenu && !TextUtils.isEmpty(titleMenuToolbars[indexMenu]))
            if (null != collapsingToolbar)
                collapsingToolbar.setTitle(titleMenuToolbars[indexMenu]);
            else
                mActivity.getSupportActionBar().setTitle(titleMenuToolbars[indexMenu]);
    }

    private int findIndexMenu(int idMenu) {
        for (int i = 0; i < mNavigation.getMenu().size(); i++) {
            if (mNavigation.getMenu().getItem(i).getItemId() == idMenu) return i;
        }
        return -1;
    }

    private void getTitleMenuToolbar() {
        // không có title menu
        try {
            titleMenuToolbars = ResourceUtils.getStringArray(mListener.getIdArrayTitleToolbarNavigationBottom());
        } catch (Resources.NotFoundException e) {

        }
    }

    public void setSelectMenu(int idMenu) {
        mNavigation.setSelectedItemId(idMenu);
    }

    private void prepareComplete() {

    }

}
