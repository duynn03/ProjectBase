package com.example.nguyenduy.projectbase.base.firebase;

import com.google.firebase.perf.FirebasePerformance;
import com.google.firebase.perf.metrics.Trace;

public class PerformanceUtils {

    public PerformanceUtils() {

    }

    private void addTrace(String key) {
        Trace myTrace = FirebasePerformance.getInstance().newTrace(key);
        myTrace.start();
    }

    public void onDestroy(){

    }

}
