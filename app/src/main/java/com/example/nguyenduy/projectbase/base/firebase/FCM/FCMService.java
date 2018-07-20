package com.example.nguyenduy.projectbase.base.firebase.FCM;

import com.example.nguyenduy.projectbase.utils.LogUtils;
import com.example.nguyenduy.projectbase.utils.data.SharedPreference.SharedPreferenceUtils;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Map;

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
        LogUtils.e("FCMService: " + "Refreshed token: " + token);
        // send token to server
        SharedPreferenceUtils.getInstance().setToken(token);
        sendRegistrationToServer(token);
        // https://firebase.google.com/docs/reference/android/com/google/firebase/iid/FirebaseInstanceId
    }

    private void sendRegistrationToServer(String token) {
        // TODO
        LogUtils.e("FCMService: " + "sendRegistrationToServer() success with token: " + token);
    }

    // xử lý onMessageReceived() trong vòng 10s, sau đó android sẽ terminate process bất kì lúc nào
    // Nếu cần xử lý hơn 10s thì nghiên cứu thêm: https://github.com/firebase/firebase-jobdispatcher-android#user-content-firebase-jobdispatcher-
    // Khi app đang ở foreground thì message mới vào method này
    // ==> không get message ở method này.
    @Override
    public void onMessageReceived(RemoteMessage message) {
        LogUtils.e("FCMService: onMessageReceived(): From: " + message.getFrom());
        // if has data
        if (hasData(message)) {
            handleData(message.getData());
        }

        if (hasNotification(message)) {
            handleNotification(message.getNotification());
        }
    }

    private boolean hasData(RemoteMessage message) {
        return null != message.getData() && message.getData().size() > 0;
    }

    private void handleData(Map<String, String> data) {
        // Check if data needs to be processed by long running job */
        // TODO
        if (false) {
            // For long-running tasks (10 seconds or more) use Firebase Job Dispatcher
            handleScheduleJobData(data);
        } else {
            // Handle message within 10 seconds
            handleFastData(data);
        }
    }

    /**
     * For long-running tasks (10 seconds or more) use Firebase Job Dispatcher
     * https://github.com/firebase/firebase-jobdispatcher-android#user-content-firebase-jobdispatcher-
     */
    private void handleScheduleJobData(Map<String, String> data) {
        LogUtils.e("FCMService: handleScheduleJobData(): data: " + data);
    }

    /**
     * For fast-running tasks (less 10 seconds)
     */
    private void handleFastData(Map<String, String> data) {
        LogUtils.e("FCMService: handleFastData(): data: " + data);
        // TODO
        // có thể send intent broadcast application
    }

    private boolean hasNotification(RemoteMessage message) {
        return null != message.getNotification();
    }

    private void handleNotification(RemoteMessage.Notification notification) {
        // TODO
        LogUtils.e("FCMService: handleNotification(): body: " + notification.getBody());
    }

    // message vào method này khi FCM không gửi được message
    // xảy ra khi có quá nhiều message (> 100) bị pending trên device tại thời điểm connect hoặc device không connect tới FCM trong hơn 1 month
    // khi xảy ra method này thì nên thực hiện full sync với your app server
    @Override
    public void onDeletedMessages() {
        LogUtils.e("FCMService: Do quá lâu không dùng FCM hoặc có quá nhiều data nên chúng tôi cần đồng bộ lại với server...");
        syncFullApplicationWithServer();
        super.onDeletedMessages();
    }

    private void syncFullApplicationWithServer() {
        // TODO
    }

    private void setAutoInitEnable() {
        FirebaseMessaging.getInstance().setAutoInitEnabled(true);
    }
}
