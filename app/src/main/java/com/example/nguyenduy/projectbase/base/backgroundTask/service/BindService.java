package com.example.nguyenduy.projectbase.base.backgroundTask.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.example.nguyenduy.projectbase.utils.LogUtils;
import com.example.nguyenduy.projectbase.utils.method.MethodUtils;

public class BindService extends Service {

    private static final String TAG = MethodUtils.getTagClass(BindService.class);

    /*call method này để setup service (trước khi call onStartCommand() và onBind())
     * Nếu service đang running thì sẽ không vào method này*/
    @Override
    public void onCreate() {
        LogUtils.i(TAG + "onCreate");
        super.onCreate();
    }

    /*bất kì component nào call bindService()
     * Cần phải cung cấp 1 interface IBinder cho component giao tiếp */
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        LogUtils.i(TAG + "onBind");
        return null;
    }

    /*bất kì component nào call startService() thì khi vào method này thì service đã start và run nó trên background vô thời hạn
    ==> khi nào không dùng thì phải stopService()*/
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        LogUtils.i(TAG + "onStartCommand");
        return START_STICKY;
    }

    /*call method khi service không được sử dụng nữa. bằng cách tất cả các component unbind
     * clear all resource. Ex: threads, registered listeners, or receivers */
    @Override
    public void onDestroy() {
        LogUtils.i(TAG + "onDestroy");
        super.onDestroy();
    }
}
