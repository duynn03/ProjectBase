package com.example.nguyenduy.projectbase.base.notification;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.app.Person;
import android.support.v4.graphics.drawable.IconCompat;

import com.example.nguyenduy.projectbase.R;
import com.example.nguyenduy.projectbase.application.MyApplication;
import com.example.nguyenduy.projectbase.screen.main2.MainActivity;
import com.example.nguyenduy.projectbase.utils.method.ResourceUtils;
import com.example.nguyenduy.projectbase.utils.method.SDKUtils;

public class NotificationUtils {

    private static final String CHANNEL_ID = "channel_01_ID";
    private static final String CHANNEL_NAME = "channel_01_NAME";
    private static final String CHANNEL_DESCRIPTION = "channel_01_DESCRIPTION";

    private Context mContext;
    private NotificationManagerCompat notificationManager;

    public NotificationUtils(Context context) {
        mContext = context;
        notificationManager = NotificationManagerCompat.from(mContext);
    }

    // lưu lại id notification để update notification hoặc remove notification
    private int idNotification = 1;

    public void showNotificationBigText() {
        notificationManager.notify(idNotification, createNotificationBigText());
    }

    private Notification createNotificationBigText() {
        String contentSmall = "Content Small Notification Content Small Notification Content Small Notification Content Small Notification Content Small Notification Content Small Notification Content Small Notification Content Small Notification Content Small Notification Content Small Notification Content Small Notification Content Small Notification Content Small Notification Content Small Notification Content Small Notification";
        String contentBig = "Content Big Notification Content Big Notification Content Big Notification Content Big Notification Content Big Notification Content Big Notification Content Big Notification Content Big Notification Content Big Notification Content Big Notification Content Big Notification Content Big Notification Content Big Notification Content Big Notification Content Big Notification Content Big Notification Content Big Notification Content Big Notification Content Big Notification";
        return new NotificationCompat.Builder(mContext, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_splash)
                .setContentTitle("Title Notification")
                // default thì content sẽ chỉ hiển thị trên 1 line
                .setContentText(contentSmall)
                // set content hiển thị full trên expanable notification thì set style
                // https://developer.android.com/training/notify-user/expanded
                .setStyle(new NotificationCompat.BigTextStyle().bigText(contentBig))
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                // set intent to activity
                // https://developer.android.com/training/notify-user/navigation
                .setContentIntent(createIntentToActivity())
                // auto remove notification khi user taps vào notification
                .setAutoCancel(true)
                .addAction(R.drawable.ic_menu_gallery, "Action Button", createIntentAction())
                //  .addAction(ReplyActionUtils.createAction())
                .build();
    }

    /*Add tối đa 6 line, nếu add nhiều hơn 6 line thì 6 line đầu sẽ hiển thị*/
    public void showNotificationTextMultiLine() {
        notificationManager.notify(idNotification, createNotificationTextMultiLine());
    }

    private Notification createNotificationTextMultiLine() {
        String contentSmall = "Content Small Notification Content Small Notification Content Small Notification Content Small Notification Content Small Notification Content Small Notification Content Small Notification Content Small Notification Content Small Notification Content Small Notification Content Small Notification Content Small Notification Content Small Notification Content Small Notification Content Small Notification";
        return new NotificationCompat.Builder(mContext, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_splash)
                .setContentTitle("Title Notification")
                // default thì content sẽ chỉ hiển thị trên 1 line
                .setContentText(contentSmall)
                .setStyle(new NotificationCompat.InboxStyle()
                        .addLine("Line 1 Line 1 Line 1 Line 1  Line 1 Line 1 Line 1 Line 1 Line 1 Line 1 Line 1")
                        .addLine("Line 2 Line 2 Line 2 Line 2  Line 2 Line 2 Line 2 Line 2 Line 2 Line 2 Line 2")
                        .addLine("Line 3 Line 3 Line 3 Line 3  Line 3 Line 3 Line 3 Line 3 Line 3 Line 3 Line 3"))
                .build();
    }

    public void showNotificationTextStyleMessage() {
        notificationManager.notify(idNotification, createNotificationTextStyleMessage());
    }

    /*Khi sử dụng setStyle Message thì value của setContent sẽ bị ignore*/
    private Notification createNotificationTextStyleMessage() {
        NotificationCompat.MessagingStyle.Message message1 =
                new NotificationCompat.MessagingStyle.Message("Message 1 Message 1 Message 1 Message 1 Message 1",
                        1368436083157l,
                        new Person.Builder().setName("sender 1: ").build());
        NotificationCompat.MessagingStyle.Message message2 =
                new NotificationCompat.MessagingStyle.Message("Message 2 Message 2 Message 2 Message 2 Message 2",
                        1368436083159l,
                        new Person.Builder().setName("sender 2: ").build());
        Person person = new Person.Builder()
                .setName("Nguyễn Duy")
                .setIcon(IconCompat.createWithResource(mContext, R.drawable.ic_menu_camera)).build();

        return new NotificationCompat.Builder(mContext, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_splash)
                /*Sử dụng kèm với NotificationCompat thì system sẽ tự động hỗ trợ expanded notification style*/
                .setStyle(new NotificationCompat.MessagingStyle(person)
                        /*conversion (optional)*/
                        .setGroupConversation(true)
                        .setConversationTitle("Conversation Title")
                        .addMessage(message1)
                        .addMessage(message2))
                .build();
    }


    public void showNotificationBigImage1() {
        notificationManager.notify(idNotification, createNotificationBigImage1());
    }

    private Notification createNotificationBigImage1() {
        String contentSmall = "Content Small Notification Content Small Notification Content Small Notification Content Small Notification Content Small Notification Content Small Notification Content Small Notification Content Small Notification Content Small Notification Content Small Notification Content Small Notification Content Small Notification Content Small Notification Content Small Notification Content Small Notification";
        Bitmap bitmap = BitmapFactory.decodeResource(ResourceUtils.getResource(), R.drawable.ic_splash);
        return new NotificationCompat.Builder(mContext, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_splash)
                .setContentTitle("Title Notification")
                // default thì content sẽ chỉ hiển thị trên 1 line
                .setContentText(contentSmall)
                // https://developer.android.com/training/notify-user/expanded
                // set image to
                .setLargeIcon(bitmap)
                // set image nhỏ
                .setStyle(new NotificationCompat.BigPictureStyle().bigPicture(bitmap))
                .build();
    }

    public void showNotificationBigImage2() {
        notificationManager.notify(idNotification, createNotificationBigImage2());
    }

    private Notification createNotificationBigImage2() {
        String contentSmall = "Content Small Notification Content Small Notification Content Small Notification Content Small Notification Content Small Notification Content Small Notification Content Small Notification Content Small Notification Content Small Notification Content Small Notification Content Small Notification Content Small Notification Content Small Notification Content Small Notification Content Small Notification";
        Bitmap bitmap = BitmapFactory.decodeResource(ResourceUtils.getResource(), R.drawable.ic_splash);
        return new NotificationCompat.Builder(mContext, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_splash)
                .setContentTitle("Title Notification")
                // default thì content sẽ chỉ hiển thị trên 1 line
                .setContentText(contentSmall)
                // https://developer.android.com/training/notify-user/expanded
                // set image to
                .setLargeIcon(bitmap)
                // set image nhỏ
                .setStyle(new NotificationCompat.BigPictureStyle()
                        .bigPicture(bitmap)
                        .bigLargeIcon(null))
                .build();
    }

    public void showNotificationBigImage3() {
        notificationManager.notify(idNotification, createNotificationBigImage3());
    }

    private Notification createNotificationBigImage3() {
        String contentSmall = "Content Small Notification Content Small Notification Content Small Notification Content Small Notification Content Small Notification Content Small Notification Content Small Notification Content Small Notification Content Small Notification Content Small Notification Content Small Notification Content Small Notification Content Small Notification Content Small Notification Content Small Notification";
        Bitmap bitmap = BitmapFactory.decodeResource(ResourceUtils.getResource(), R.drawable.ic_splash);
        return new NotificationCompat.Builder(mContext, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_splash)
                .setContentTitle("Title Notification")
                // default thì content sẽ chỉ hiển thị trên 1 line
                .setContentText(contentSmall)
                // https://developer.android.com/training/notify-user/expanded
                // set image nhỏ
                .setStyle(new NotificationCompat.BigPictureStyle()
                        .bigPicture(bitmap))
                .build();
    }

    // TODO
    public void showNotificationControlMedia() {
        // notificationManager.notify(idNotification, createNotificationControlMedia());
    }

    /*
     * https://developer.android.com/training/notify-user/expanded#media-style
     * https://developer.android.com/guide/topics/media-apps/audio-app/building-a-mediabrowserservice?hl=pt-br#mediastyle-notifications
     */
 /*   private Notification createNotificationControlMedia() {
        String contentSmall = "Content Small Notification Content Small Notification Content Small Notification Content Small Notification Content Small Notification Content Small Notification Content Small Notification Content Small Notification Content Small Notification Content Small Notification Content Small Notification Content Small Notification Content Small Notification Content Small Notification Content Small Notification";
        Bitmap bitmap = BitmapFactory.decodeResource(ResourceUtils.getResource(), R.drawable.ic_splash);

        // Get the session's metadata
        MediaControllerCompat controller = mediaSession.getController();
        MediaMetadataCompat mediaMetadata = controller.getMetadata();
        MediaDescriptionCompat description = mediaMetadata.getDescription();

        return new NotificationCompat.Builder(mContext, CHANNEL_ID)
                // Add the metadata for the currently playing track
                .setContentTitle("Title Content Title Content Title Content Title Content Title Content Title Content")
                .setContentText("Content Text Content Text Content Text Content Text Content Text Content Text")
                .setSubText("Sub Text Sub Text Sub Text Sub Text Sub Text Sub Text Sub Text Sub Text Sub Text ")
                .setLargeIcon(bitmap)

                // Enable launching the player by clicking the notification
                .setContentIntent(controller.getSessionActivity())

                .setColor(ContextCompat.getColor(mContext, R.color.colorPrimary))


                // Show controls on lock screen even when user hides sensitive content.
                .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)

                .setSmallIcon(R.drawable.ic_splash)
                // Add media control buttons that invoke intents in your media service
                .addAction(R.drawable.ic_notification_prev, "Previous", prevPendingIntent) // #0
                .addAction(R.drawable.ic_notification_pause, "Pause", pausePendingIntent)  // #1
                .addAction(R.drawable.ic_notification_next, "Next", nextPendingIntent)     // #2
                // Apply the media style template
                .setStyle(new android.support.v4.media.app.NotificationCompat.MediaStyle()
                        .setShowActionsInCompactView(1 *//* #1: pause button *//*)
                        .setMediaSession(mMediaSession.getSessionToken()))
                .build();

        return new NotificationCompat.Builder(mContext, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_splash)
                .setContentTitle("Title Notification")
                // default thì content sẽ chỉ hiển thị trên 1 line
                .setContentText(contentSmall)
                .setStyle(new NotificationCompat.InboxStyle()
                        .addLine("Line 1 Line 1 Line 1 Line 1  Line 1 Line 1 Line 1 Line 1 Line 1 Line 1 Line 1")
                        .addLine("Line 2 Line 2 Line 2 Line 2  Line 2 Line 2 Line 2 Line 2 Line 2 Line 2 Line 2")
                        .addLine("Line 3 Line 3 Line 3 Line 3  Line 3 Line 3 Line 3 Line 3 Line 3 Line 3 Line 3"))
                .build();
    }*/

    private PendingIntent createIntentToActivity() {
        Intent intent = new Intent(mContext, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        return PendingIntent.getActivity(mContext, 0, intent, 0);
    }

    private final static String EXTRA_NOTIFICATION_ID = "EXTRA_NOTIFICATION_ID";

    private PendingIntent createIntentAction() {
        Intent actionIntent = new Intent(mContext, NotificationReceiver.class);
        actionIntent.setAction("Intent Action Button");
        actionIntent.putExtra(EXTRA_NOTIFICATION_ID, idNotification);
        return PendingIntent.getBroadcast(mContext, 0, actionIntent, 0);
    }

    @SuppressLint("NewApi")
    public static void init() {
        // register channel
        if (SDKUtils.isVersionSdkCurrentGreaterOrEqualVersion0())
            createChannel();
    }

    // Create the NotificationChannel, but only on API 26+ because the NotificationChannel class is new and not in the support library
    @RequiresApi(api = Build.VERSION_CODES.O)
    private static void createChannel() {
        // Register the channel with the system; you can't change the importance or other notification behaviors after this
        // NotificationManager.IMPORTANCE_DEFAULT giống với setPriority trên api 24 trở xuống
        // https://developer.android.com/training/notify-user/channels#importance
        NotificationChannel channel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT);
        channel.setDescription(CHANNEL_DESCRIPTION);
        MyApplication.getAppContext().getSystemService(NotificationManager.class).createNotificationChannel(channel);
    }
}