package com.example.nguyenduy.projectbase.screen.main2.architectureComponents.lifecycle;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.nguyenduy.projectbase.R;

public class LifecycleDefaultActivity extends AppCompatActivity {

    private MyLifecycleObserver lifecycleObserver;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lifecycle);

        // default lifecycle
        lifecycleObserver = new MyLifecycleObserver(getLifecycle());
        getLifecycle().addObserver(lifecycleObserver);
    }
}
