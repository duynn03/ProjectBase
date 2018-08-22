package com.example.nguyenduy.projectbase.base.backgroundTask.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

public class BindService extends Service {

    /*call method này để setup service (trước khi call onStartCommand() và onBind())
     * Nếu service đang running thì sẽ không vào method này*/
    @Override
    public void onCreate() {
        super.onCreate();
    }

    /*bất kì component nào call bindService()
     * Cần phải cung cấp 1 interface IBinder cho component giao tiếp */
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    /*call method khi service không được sử dụng nữa. bằng cách tất cả các component unbind
     * clear all resource. Ex: threads, registered listeners, or receivers */
    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
