package com.example.nguyenduy.projectbase.base.broadcast.system.screen;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class ScreenReceiver extends BroadcastReceiver {

    private IChangeScreenListener mListener;

    ScreenReceiver() {
    }

    public interface IChangeScreenListener {
        void changeStatusScreen(boolean isOnScreen);
    }

    public void setListenerScreenChange(IChangeScreenListener listener) {
        mListener = listener;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        updateStatusScreen(intent);
    }

    private void updateStatusScreen(Intent intent) {
        if (null != mListener)
            if (Intent.ACTION_SCREEN_OFF.equals(intent.getAction()))
                mListener.changeStatusScreen(false);
            else if (Intent.ACTION_SCREEN_ON.equals(intent.getAction()))
                mListener.changeStatusScreen(true);
    }
}
