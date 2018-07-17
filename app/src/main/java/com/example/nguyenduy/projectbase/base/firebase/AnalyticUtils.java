package com.example.nguyenduy.projectbase.base.firebase;

import android.content.Context;
import android.os.Bundle;

import com.google.firebase.analytics.FirebaseAnalytics;

public class AnalyticUtils {

    private FirebaseAnalytics mAnalytics;

    public AnalyticUtils(Context context) {
        mAnalytics = FirebaseAnalytics.getInstance(context);
    }

    public void log(String information) {
        Bundle params = new Bundle();
        params.putString("full_text", information);
        mAnalytics.logEvent("log_infor", params);
    }

}
