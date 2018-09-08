package com.example.nguyenduy.projectbase.base.backgroundTask.service.bindService;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.example.nguyenduy.projectbase.utils.LogUtils;
import com.example.nguyenduy.projectbase.utils.method.MethodUtils;

/*https://developer.android.com/guide/components/bound-services#Binder*/
public class BindServiceMessenger extends Service {

    private static final String TAG = MethodUtils.getTagClass(BindServiceMessenger.class);

    public static final int MSG_SAY_HELLO = 1;

    /**
     * Handler of incoming messages from clients.
     */
    private class IncomingHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_SAY_HELLO:
                    Toast.makeText(getApplicationContext(), "hello!", Toast.LENGTH_SHORT).show();
                    break;
                default:
                    super.handleMessage(msg);
            }
        }
    }

    /**
     * Target we publish for clients to send messages to IncomingHandler.
     */
    private final Messenger mMessenger = new Messenger(new IncomingHandler());

    /*call method này để setup service (trước khi call onStartCommand() và onBind())
     * Nếu service đang running thì sẽ không vào method này*/
    @Override
    public void onCreate() {
        LogUtils.i(TAG + "onCreate");
        super.onCreate();
    }

    /**
     * When binding to the service, we return an interface to our messenger
     * for sending messages to the service.
     */
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        LogUtils.i(TAG + "onBind");
        return mMessenger.getBinder();
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

    /*call method khi service không được sử dụng nữa. bằng cách tất cả các component unbind
     * clear all resource. Ex: threads, registered listeners, or receivers */
    @Override
    public void onDestroy() {
        LogUtils.i(TAG + "onDestroy");
        super.onDestroy();
    }
}
