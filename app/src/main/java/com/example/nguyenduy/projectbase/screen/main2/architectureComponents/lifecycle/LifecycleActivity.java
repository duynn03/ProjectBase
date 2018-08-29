package com.example.nguyenduy.projectbase.screen.main2.architectureComponents.lifecycle;

import android.arch.lifecycle.LifecycleOwner;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.nguyenduy.projectbase.R;
import com.example.nguyenduy.projectbase.utils.method.MethodUtils;

public class LifecycleActivity extends AppCompatActivity {

    public static final String TAG = MethodUtils.getTagClass(LifecycleActivity.class);

    /*private LifecycleRegistry lifecycleRegistry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        lifecycleRegistry = new LifecycleRegistry(this);
        lifecycleRegistry.markState(Lifecycle.State.CREATED);
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
    }*/

    private MyLifecycleObserver observer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lifecycle);

        // lifecycle
        observer = new MyLifecycleObserver(getLifecycle());
        getLifecycle().addObserver(observer);


        /*observer = new MyLifecycleObserver(this, getLifecycle(), location -> {
            // update UI
            LogUtils.i(TAG + "onCreate(): update UI");
        });
        Util.checkUserStatus(result -> {
            if (result) {
                observer.enable();
            }
        });*/
    }
}
