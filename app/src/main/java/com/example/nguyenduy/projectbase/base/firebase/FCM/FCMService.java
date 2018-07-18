package com.example.nguyenduy.projectbase.base.firebase.FCM;

import com.example.nguyenduy.projectbase.utils.LogUtils;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class FCMService extends FirebaseMessagingService {
    /**
     * Called if InstanceID token is updated. This may occur if the security of
     * the previous token had been compromised. Note that this is called when the InstanceID token
     * is initially generated so this is where you would retrieve the token.
     * <p>
     * registration token sẽ thay đổi khi
     * The app deletes Instance ID
     * The app is restored on a new device
     * The user uninstalls/reinstall the app
     * The user clears app data.
     */
    @Override
    public void onNewToken(String token) {
        LogUtils.d("FCMService " + "Refreshed token: " + token);
        // send token to server
        sendRegistrationToServer(token);
        // https://firebase.google.com/docs/reference/android/com/google/firebase/iid/FirebaseInstanceId
    }

    private void getCurrentToken() {
        FirebaseInstanceId.getInstance().getInstanceId();
    }

    private void sendRegistrationToServer(String token) {

    }

    // xử lý onMessageReceived() trong vòng 10s, sau đó android sẽ terminate process bất kì lúc nào
    // Nếu cần xử lý hơn 10s thì nghiên cứu thêm: https://github.com/firebase/firebase-jobdispatcher-android#user-content-firebase-jobdispatcher-
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
    }


    @Override
    public void onDeletedMessages() {
        super.onDeletedMessages();
    }

    private void setAutoInitEnable() {
        FirebaseMessaging.getInstance().setAutoInitEnabled(true);
    }
}
