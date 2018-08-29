package com.example.nguyenduy.projectbase.screen.main2.architectureComponents.lifecycle;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.OnLifecycleEvent;

import com.example.nguyenduy.projectbase.utils.LogUtils;
import com.example.nguyenduy.projectbase.utils.method.MethodUtils;

public class MyLifecycleObserver implements LifecycleObserver {

    public static final String TAG = MethodUtils.getTagClass(MyLifecycleObserver.class);

    private Lifecycle mLifecycle;

    public MyLifecycleObserver(Lifecycle lifecycle) {
        mLifecycle = lifecycle;
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    public void onCreate() {
        LogUtils.i(TAG + "onCreate(): ");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    public void onStart() {
        if (mLifecycle.getCurrentState().isAtLeast(Lifecycle.State.STARTED)) {
            LogUtils.i(TAG + "onStart(): ");
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void onResume() {
        LogUtils.i(TAG + "onResume(): ");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    public void onPause() {
        LogUtils.i(TAG + "onPause(): ");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    public void onStop() {
        LogUtils.i(TAG + "onStop(): ");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public void onDestroy() {
        if (mLifecycle.getCurrentState().isAtLeast(Lifecycle.State.DESTROYED)) {
            LogUtils.i(TAG + "onDestroy(): ");
        }
    }

}
