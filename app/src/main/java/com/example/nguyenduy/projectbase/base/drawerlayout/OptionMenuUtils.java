package com.example.nguyenduy.projectbase.base.drawerlayout;

import android.app.Activity;
import android.view.Menu;

public class OptionMenuUtils {

    private Activity mActivity;
    private IOptionMenuListener mListener;

    public interface IOptionMenuListener {
        int getIdToolbar();
    }

    public OptionMenuUtils(Activity activity, IOptionMenuListener listener, Menu menu) {
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
