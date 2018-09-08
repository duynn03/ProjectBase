package com.example.nguyenduy.projectbase.base.firebase;

import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.nguyenduy.projectbase.application.MyApplication;
import com.example.nguyenduy.projectbase.utils.LogUtils;
import com.example.nguyenduy.projectbase.utils.method.MethodUtils;
import com.google.firebase.perf.FirebasePerformance;
import com.google.firebase.perf.metrics.Trace;

import java.util.Map;

public class PerformanceUtils {

    private static final String TAG = MethodUtils.getTagClass(PerformanceUtils.class);

    private PerformanceUtils() {

    }

    public static Trace createTrace(String key) {
        return FirebasePerformance.getInstance().newTrace(key);
    }

    public static void startTrace(Trace trace) {
        trace.start();
        LogUtils.i(TAG + "Starting trace");
    }

    public static void test(ImageView view) {
        final Trace trace = createTrace("test_trace");
        startTrace(trace);
        Glide.with(MyApplication.getAppContext()).
                load("https://www.google.com/images/branding/googlelogo/2x/googlelogo_color_272x92dp.png")
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        LogUtils.i(TAG + "Error trace");
                        stopTrace(trace);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        LogUtils.i(TAG + "completed trace");
                        stopTrace(trace);
                        return false;
                    }
                }).into(view);
    }

    public static Trace putAttribute(Trace trace, String key, String value) {
        trace.putAttribute(key, value);
        return trace;
    }

    public static Trace removeAttribute(Trace trace, String key) {
        trace.removeAttribute(key);
        return trace;
    }

    public static String getAttribute(Trace trace, String key) {
        return trace.getAttribute(key);
    }

    public static Map<String, String> getAllAttribute(Trace trace) {
        return trace.getAttributes();
    }

    public static Trace incrementMetric(Trace trace, String key, long count) {
        trace.incrementMetric(key, count);
        return trace;
    }

    public static void stopTrace(Trace trace) {
        trace.stop();
    }

}
