package com.example.nguyenduy.projectbase.base.firebase.FCM;

import android.text.TextUtils;

import com.example.nguyenduy.projectbase.utils.LogUtils;
import com.example.nguyenduy.projectbase.base.sharedPreference.SharedPreferenceUtils;
import com.example.nguyenduy.projectbase.utils.method.MethodUtils;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Date;
import java.util.Map;

public class FCMService extends FirebaseMessagingService {

    private static final String TAG = MethodUtils.getTagClass(FCMService.class);

    public static void init() {
        FirebaseMessaging.getInstance().setAutoInitEnabled(true);
        LogUtils.i(TAG + "Token: " + SharedPreferenceUtils.getInstance().getToken());
    }

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
        LogUtils.i(TAG + "Refreshed token: " + token);
        SharedPreferenceUtils.getInstance().setToken(token);
        // send token to server
        sendRegistrationToServer(token);
    }

    private void sendRegistrationToServer(String token) {
        // TODO
        LogUtils.i(TAG + "sendRegistrationToServer(): success with token: " + token);
    }

    // xử lý onMessageReceived() trong vòng 10s, sau đó android sẽ terminate process bất kì lúc nào
    // Nếu cần xử lý hơn 10s thì nghiên cứu thêm: https://github.com/firebase/firebase-jobdispatcher-android#user-content-firebase-jobdispatcher-
    // Khi app đang ở foreground thì message mới vào method này, nếu app đang ở background thì data sẽ được send vào intent của activity launcher default
    @Override
    public void onMessageReceived(RemoteMessage message) {
        logInformationMessageReceived(message);
        // if has notification
        if (null != message.getNotification()) handleNotification(message.getNotification());
        // if has data
        if (hasData(message)) handleData(message.getData());
    }

    private void logInformationMessageReceived(RemoteMessage message) {
        LogUtils.i(TAG + "onMessageReceived(): MessageId: " + message.getMessageId());
        LogUtils.i(TAG + "onMessageReceived(): MessageType: " + message.getMessageType());
        LogUtils.i(TAG + "onMessageReceived(): SentTime: " + new Date(message.getSentTime()).toString());
        LogUtils.i(TAG + "onMessageReceived(): TimeToLive: " + message.getTtl());
        LogUtils.i(TAG + "onMessageReceived(): From: " + message.getFrom());
        LogUtils.i(TAG + "onMessageReceived(): To: " + message.getTo());
        // PRIORITY_HIGH = 1, PRIORITY_NORMAL = 2, PRIORITY_UNKNOWN = 0
        LogUtils.i(TAG + "onMessageReceived(): Priority: " + message.getPriority());
        LogUtils.i(TAG + "onMessageReceived(): OriginalPriority: " + message.getOriginalPriority());
        LogUtils.i(TAG + "onMessageReceived(): CollapseKey: " + message.getCollapseKey());
    }

    private void handleNotification(RemoteMessage.Notification notification) {
        // TODO
        // có thể ghét cả sound, ...
        // https://firebase.google.com/docs/reference/fcm/rest/v1/projects.messages#Notification
        LogUtils.i(TAG + "handleNotification(): title: " + (TextUtils.isEmpty(notification.getTitle()) ? "null" : notification.getTitle()));
        LogUtils.i(TAG + "handleNotification(): body: " + (TextUtils.isEmpty(notification.getBody()) ? "null" : notification.getBody()));
        LogUtils.i(TAG + "handleNotification(): color: " + (TextUtils.isEmpty(notification.getColor()) ? "null" : notification.getColor()));
        LogUtils.i(TAG + "handleNotification(): icon: " + (TextUtils.isEmpty(notification.getIcon()) ? "null" : notification.getIcon()));
        LogUtils.i(TAG + "handleNotification(): clickAction: " + (TextUtils.isEmpty(notification.getClickAction()) ? "null" : notification.getClickAction()));
        LogUtils.i(TAG + "handleNotification(): tag: " + (TextUtils.isEmpty(notification.getTag()) ? "null" : notification.getTag()));
        LogUtils.i(TAG + "handleNotification(): TitleLocalizationKey: " + (TextUtils.isEmpty(notification.getTitleLocalizationKey()) ? "null" : notification.getTitleLocalizationKey()));
        LogUtils.i(TAG + "handleNotification(): BodyLocalizationKey: " + (TextUtils.isEmpty(notification.getBodyLocalizationKey()) ? "null" : notification.getBodyLocalizationKey()));
        LogUtils.i(TAG + "handleNotification(): TitleLocalizationArgs: " + (MethodUtils.isEmpty(notification.getTitleLocalizationArgs()) ? "null" : notification.getTitleLocalizationArgs()));
        LogUtils.i(TAG + "handleNotification(): BodyLocalizationArgs: " + (MethodUtils.isEmpty(notification.getBodyLocalizationArgs()) ? "null" : notification.getBodyLocalizationArgs()));

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
        LogUtils.i(TAG + "handleScheduleJobData(): data: " + data);
    }

    /**
     * For fast-running tasks (less 10 seconds)
     */
    private void handleFastData(Map<String, String> data) {
        LogUtils.i(TAG + "handleFastData(): data: " + data);
        // TODO
        // có thể send intent broadcast application
    }

    // message vào method này khi FCM không gửi được message
    // xảy ra khi có quá nhiều message (> 100) bị pending trên device tại thời điểm connect hoặc device không connect tới FCM trong hơn 1 month
    // khi vào method này thì nên thực hiện full sync với your app server
    @Override
    public void onDeletedMessages() {
        LogUtils.w(TAG + "onDeletedMessages(): Do quá lâu không dùng FCM hoặc có quá nhiều data nên chúng tôi cần đồng bộ lại với server...");
        syncFullApplicationWithServer();
        super.onDeletedMessages();
    }

    private void syncFullApplicationWithServer() {
        // TODO
    }
}
