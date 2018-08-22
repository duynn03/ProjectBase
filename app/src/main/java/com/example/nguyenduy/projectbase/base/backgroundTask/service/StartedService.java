package com.example.nguyenduy.projectbase.base.backgroundTask.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

/*khi service start thì lifecycle sẽ độc lập với component gọi nó*/
public class StartedService extends Service {


    /*call method này để setup service (trước khi call onStartCommand() và onBind())
     * Nếu service đang running thì sẽ không vào method này*/
    @Override
    public void onCreate() {
        super.onCreate();
    }

    /*bất kì component nào call startService() thì khi vào method này thì service đã start và run nó trên background vô thời hạn
    ==> khi nào không dùng thì phải stopService()*/
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_STICKY;
    }


    // không phải bindService thì return null
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    /*call method khi service không được sử dụng nữa. bằng cách gọi stopSelf() hoặc stopService()
     * clear all resource. Ex: threads, registered listeners, or receivers */
    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
