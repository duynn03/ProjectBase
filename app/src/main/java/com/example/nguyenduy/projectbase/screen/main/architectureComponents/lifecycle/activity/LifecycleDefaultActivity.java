package com.example.nguyenduy.projectbase.screen.main.architectureComponents.lifecycle.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.nguyenduy.projectbase.R;

public class LifecycleDefaultActivity extends AppCompatActivity {
    
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lifecycle);

        // default lifecycle
        MyLifecycleObserver lifecycleObserver = new MyLifecycleObserver(getLifecycle());
        getLifecycle().addObserver(lifecycleObserver);
        /*Có thể sử dụng*/
        // getLifecycle().removeObserver(lifecycleObserver);
    }

}
