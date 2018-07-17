package com.example.nguyenduy.projectbase.base.firebase;

import android.content.Context;

import com.crashlytics.android.Crashlytics;

import io.fabric.sdk.android.Fabric;

public class CrashUtils {

    private Context mContext;

    public CrashUtils(Context context) {
        mContext = context;
        enableDebuggerCrashlytics();
    }

    // Enables Crashlytics debugger
    public void enableDebuggerCrashlytics() {
        Fabric.with(new Fabric.Builder(mContext)
                .kits(new Crashlytics())
                .debuggable(true)           // Enables Crashlytics debugger
                .build());
    }
}
