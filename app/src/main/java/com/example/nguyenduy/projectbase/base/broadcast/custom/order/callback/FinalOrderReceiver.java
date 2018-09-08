package com.example.nguyenduy.projectbase.base.broadcast.custom.order.callback;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.nguyenduy.projectbase.utils.LogUtils;
import com.example.nguyenduy.projectbase.utils.method.MethodUtils;

public class FinalOrderReceiver extends BroadcastReceiver {

    private static final String TAG = MethodUtils.getTagClass(FinalOrderReceiver.class);

    private IChangeFinalOrderListener mListener;

    FinalOrderReceiver() {
    }

    public interface IChangeFinalOrderListener {
        void changeStatusFinalOrder(String nameBroadcast, String data);
    }

    public void setListenerOrderChange(IChangeFinalOrderListener listener) {
        mListener = listener;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (getResultCode() == Activity.RESULT_CANCELED) {
            LogUtils.i(TAG + "FinalOrderReceiver(): " + "Activity.RESULT_CANCEL");
            return;
        }
        LogUtils.i(TAG + "FinalOrderReceiver(): " + "Activity.RESULT_OK");
        // callback
        updateOrder(intent.getAction(), intent.getStringExtra("data"));
        // send data to Order3Receiver
        Bundle results = getResultExtras(true);
        String extras = results.getString("Extras") + "-->FinalOrderReceiver";
        LogUtils.i(extras);
    }

    private void updateOrder(String nameBroadcast, String data) {
        if (null != mListener)
            mListener.changeStatusFinalOrder(nameBroadcast, data);
    }
}
