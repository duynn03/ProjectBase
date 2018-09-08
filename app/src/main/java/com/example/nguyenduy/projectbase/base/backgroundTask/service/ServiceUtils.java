package com.example.nguyenduy.projectbase.base.backgroundTask.service;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;

import com.example.nguyenduy.projectbase.base.backgroundTask.service.bindService.BindServiceBinder;
import com.example.nguyenduy.projectbase.base.backgroundTask.service.bindService.BindServiceMessenger;
import com.example.nguyenduy.projectbase.utils.LogUtils;
import com.example.nguyenduy.projectbase.utils.method.MethodUtils;
import com.example.nguyenduy.projectbase.utils.method.SDKUtils;

public class ServiceUtils {

    public static final String TAG = MethodUtils.getTagClass(ServiceUtils.class);

    private Context mContext;

    /*binder*/
    private BindServiceBinder bindServiceBinder;
    private boolean isConnectedBindServiceBinder;

    /*messenger*/
    private Messenger mMessenger;
    private boolean isConnectedBindServiceMessenger;

    public ServiceUtils(Context context) {
        mContext = context;
        isConnectedBindServiceBinder = false;
        isConnectedBindServiceMessenger = false;
    }

    /*background*/
    public void startBackgroundService() {
        Intent intent = new Intent(mContext, BackgroundService.class);
        intent.putExtra(BackgroundService.DATA, "123");
        mContext.startService(intent);
    }

    /*foreground*/
    @SuppressLint("NewApi")
    public void startForegroundService() {
        Intent intent = new Intent(mContext, ForegroundService.class);
        intent.setAction("Action Start Foreground Service");
        // put extra...
        if (SDKUtils.isVersionSdkCurrentGreaterOrEqualVersion0()) {
            mContext.startForegroundService(intent);
        } else {
            mContext.startService(intent);
        }
    }

    public void stopForegroundService() {
        Intent intent = new Intent(mContext, ForegroundService.class);
        mContext.stopService(intent);
    }

    /*binder*/
    public void startBindServiceBinder() {
        Intent intent = new Intent(mContext, BindServiceBinder.class);
        mContext.bindService(intent, mConnectionBindServiceBinder, Context.BIND_AUTO_CREATE);
    }

    public void stopBindServiceBinder() {
        /*có thể unbindService hoặc không*/
        mContext.unbindService(mConnectionBindServiceBinder);
        isConnectedBindServiceBinder = false;
    }

    /**
     * Defines callbacks for service binding, passed to bindService()
     */
    private ServiceConnection mConnectionBindServiceBinder = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName className, IBinder service) {
            // We've bound to LocalService, cast the IBinder and get LocalService instance
            BindServiceBinder.MyBinder binder = (BindServiceBinder.MyBinder) service;
            bindServiceBinder = binder.getService();
            isConnectedBindServiceBinder = true;
            LogUtils.i(TAG + "onServiceConnected(): Binder");
        }

        @Override
        public void onServiceDisconnected(ComponentName arg0) {
            isConnectedBindServiceBinder = false;
            LogUtils.i(TAG + "onServiceDisconnected(): Binder");
        }
    };

    public boolean isConnectedBindServiceBinder() {
        return isConnectedBindServiceBinder;
    }

    public String getDataFromBindService() {
        return bindServiceBinder.getData();
    }

    public boolean isServiceRunning(Class<?> serviceClass) {
        ActivityManager manager = (ActivityManager) mContext.getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }


    /*messenger*/
    public void startBindServiceMessage() {
        Intent intent = new Intent(mContext, BindServiceMessenger.class);
        mContext.bindService(intent, mConnectionBindServiceMessage, Context.BIND_AUTO_CREATE);
    }

    public void stopBindServiceMessage() {
        if (isConnectedBindServiceMessenger) {
            mContext.unbindService(mConnectionBindServiceMessage);
            isConnectedBindServiceMessenger = false;
        }
    }

    private ServiceConnection mConnectionBindServiceMessage = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName className, IBinder service) {
            // We've bound to LocalService, cast the IBinder and get LocalService instance
            mMessenger = new Messenger(service);
            isConnectedBindServiceMessenger = true;
            LogUtils.i(TAG + "onServiceConnected(): Messenger ");
        }

        @Override
        public void onServiceDisconnected(ComponentName arg0) {
            mMessenger = null;
            isConnectedBindServiceMessenger = false;
            LogUtils.i(TAG + "onServiceDisconnected(): Messenger ");
        }
    };

    public void sendMessageToServiceMessenger() {
        Message msg = Message.obtain(null, BindServiceMessenger.MSG_SAY_HELLO, 0, 0);
        try {
            mMessenger.send(msg);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public boolean isConnectedBindServiceMessenger() {
        return isConnectedBindServiceMessenger;
    }
}
