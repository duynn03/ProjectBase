package com.example.nguyenduy.projectbase.base.firebase;

import com.example.nguyenduy.projectbase.utils.LogUtils;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessagingService;

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
}
