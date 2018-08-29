package com.example.nguyenduy.projectbase.screen.main2.architectureComponents.lifecycle;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LifecycleRegistry;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

import com.example.nguyenduy.projectbase.R;
import com.example.nguyenduy.projectbase.utils.method.MethodUtils;

public class CustomLifecycleActivity extends AppCompatActivity implements LifecycleOwner {

    public static final String TAG = MethodUtils.getTagClass(CustomLifecycleActivity.class);

    private LifecycleRegistry lifecycleRegistry;
    private MyLifecycleObserver lifecycleObserver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lifecycle);

        // custom lifecycle
        lifecycleRegistry = new LifecycleRegistry(this);
        lifecycleRegistry.markState(Lifecycle.State.CREATED);

        lifecycleObserver = new MyLifecycleObserver(getLifecycle());
        getLifecycle().addObserver(lifecycleObserver);
    }

    @Override
    public void onStart() {
        super.onStart();
        lifecycleRegistry.markState(Lifecycle.State.STARTED);
    }

    @NonNull
    @Override
    public Lifecycle getLifecycle() {
        return lifecycleRegistry;
    }
}
