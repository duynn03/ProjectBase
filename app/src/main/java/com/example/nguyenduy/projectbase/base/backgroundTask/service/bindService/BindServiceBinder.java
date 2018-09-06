package com.example.nguyenduy.projectbase.base.backgroundTask.service.bindService;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.example.nguyenduy.projectbase.utils.LogUtils;
import com.example.nguyenduy.projectbase.utils.method.MethodUtils;

/*https://developer.android.com/guide/components/bound-services#Binder*/
public class BindServiceBinder extends Service {

    private static final String TAG = MethodUtils.getTagClass(BindServiceBinder.class);

    private final IBinder mBinder = new MyBinder();

    public class MyBinder extends Binder {
        public BindServiceBinder getService() {
            // Return this instance of LocalService so clients can call public methods
            return BindServiceBinder.this;
        }
    }

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
        return mBinder;
    }

    // check có phải tất cả các component đều unbind với service chưa
    @Override
    public boolean onUnbind(Intent intent) {
        LogUtils.i(TAG + "onUnbind: " + super.onUnbind(intent));
        return super.onUnbind(intent);
    }

    /*component rebind lại service*/
    @Override
    public void onRebind(Intent intent) {
        LogUtils.i(TAG + "onRebind: ");
        super.onRebind(intent);
    }

    public String getData() {
        return "Data from Bind Service Binder";
    }

    /*call method khi service không được sử dụng nữa. bằng cách tất cả các component unbind
     * clear all resource. Ex: threads, registered listeners, or receivers */
    @Override
    public void onDestroy() {
        LogUtils.i(TAG + "onDestroy");
        super.onDestroy();
    }
}
