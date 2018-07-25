package com.example.nguyenduy.projectbase.base.drawerlayout;

import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;

public class ToolbarUtils {

    private Activity mActivity;
    private IToolbarListener mListener;

    public interface IToolbarListener {
        int getIdToolbar();
    }

    public ToolbarUtils(Activity activity, IToolbarListener listener, Menu menu) {
        mActivity = activity;
        mListener = listener;
        mActivity.getMenuInflater().inflate(listener.getIdToolbar(), menu);
        findViewById();
        initViews();
        initComponents();
        setEvents();
        prepareComplete();
    }


    private void findViewById() {

    }

    private void initViews() {

    }

    private void initComponents() {

    }

    private void setEvents() {

    }

    private void prepareComplete() {

    }


}
