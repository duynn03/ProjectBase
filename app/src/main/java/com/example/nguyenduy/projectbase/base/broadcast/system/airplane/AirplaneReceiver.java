package com.example.nguyenduy.projectbase.base.broadcast.system.airplane;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.example.nguyenduy.projectbase.utils.method.SystemUtils;

public class AirplaneReceiver extends BroadcastReceiver {

    private IChangeAirplaneListener mListener;

    AirplaneReceiver() {
    }

    public interface IChangeAirplaneListener {
        void changeStatusAirplane(boolean isOnAirplane);
    }

    public void setListenerAirplaneChange(IChangeAirplaneListener listener) {
        mListener = listener;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        updateAirplane();
    }

    private void updateAirplane() {
        if (null != mListener)
            mListener.changeStatusAirplane(SystemUtils.isAirplaneModeOn());
    }
}
