package com.example.nguyenduy.projectbase.screen.main2.architectureComponents.lifecycle;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.OnLifecycleEvent;
import android.content.Context;

import com.example.nguyenduy.projectbase.utils.LogUtils;
import com.example.nguyenduy.projectbase.utils.method.MethodUtils;

public class MyLifecycleObserver implements LifecycleObserver {

    public static final String TAG = MethodUtils.getTagClass(MyLifecycleObserver.class);

    private Lifecycle mLifecycle;
    private boolean enabled = false;

    public MyLifecycleObserver(Context context, Lifecycle lifecycle, Callback callback) {
        mLifecycle = lifecycle;
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    public void connectListener() {
        if (enabled) {
            LogUtils.i(TAG + "connectListener(): ");
        }
    }

    public void enable() {
        enabled = true;
        if (mLifecycle.getCurrentState().isAtLeast(Lifecycle.State.STARTED)) {
            // connect if not connected
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    public void disconnectListener() {
        LogUtils.i(TAG + "disconnectListener(): ");
    }
    // myLifecycleOwner.getLifecycle().addObserver(new MyLifecycleObserver());
}
