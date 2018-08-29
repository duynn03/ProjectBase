package com.example.nguyenduy.projectbase.screen.main2.architectureComponents.lifecycle;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.LifecycleOwner;
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
    public void onCreate(LifecycleOwner owner) {
        LogUtils.i(TAG + "onCreate(): ");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    public void onStart(LifecycleOwner owner) {
        if (mLifecycle.getCurrentState().isAtLeast(Lifecycle.State.STARTED)) {
            LogUtils.i(TAG + "onStart(): ");
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void onResume(LifecycleOwner owner) {
        LogUtils.i(TAG + "onResume(): ");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    public void onPause(LifecycleOwner owner) {
        LogUtils.i(TAG + "onPause(): ");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    public void onStop(LifecycleOwner owner) {
        LogUtils.i(TAG + "onStop(): ");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public void onDestroy(LifecycleOwner owner) {
        if (mLifecycle.getCurrentState().isAtLeast(Lifecycle.State.DESTROYED)) {
            LogUtils.i(TAG + "onDestroy(): ");
        }
    }

    /*Có thể nhận vào 2 param*/
    @OnLifecycleEvent(Lifecycle.Event.ON_ANY)
    public void onAny(LifecycleOwner owner, Lifecycle.Event event) {
        LogUtils.i(TAG + "onAny(): " + event.toString());
    }

}
