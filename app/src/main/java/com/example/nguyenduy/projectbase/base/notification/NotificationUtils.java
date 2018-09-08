package com.example.nguyenduy.projectbase.base.notification;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.app.Person;
import android.support.v4.graphics.drawable.IconCompat;
import android.widget.RemoteViews;

import com.example.nguyenduy.projectbase.R;
import com.example.nguyenduy.projectbase.base.notification.action.ReplyActionUtils;
import com.example.nguyenduy.projectbase.base.notification.channel.NotificationChannelUtils;
import com.example.nguyenduy.projectbase.base.notification.startActivity.RegularActivity;
import com.example.nguyenduy.projectbase.base.notification.startActivity.SpecialActivity;
import com.example.nguyenduy.projectbase.utils.method.ResourceUtils;
import com.example.nguyenduy.projectbase.utils.method.SDKUtils;

public class NotificationUtils {

    private final static String EXTRA_NOTIFICATION_ID = "EXTRA_NOTIFICATION_ID";

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
        String smallText = "Content Text Small Content Text Small Content Text Small Content Text Small Content Text Small Content Text Small Content Text Small Content Text Small Content Text Small";
        String bigText = "Content Text Big Content Text Big Content Text Big Content Text Big Content Text Big Content Text Big Content Text Big Content Text Big Content Text Big Content Text Big ";
        return new NotificationCompat.Builder(mContext, NotificationChannelUtils.CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_splash)
                .setContentTitle("Big Text")
                // default thì content sẽ chỉ hiển thị trên 1 line
                .setContentText(smallText)
                .setStyle(new NotificationCompat.BigTextStyle().bigText(bigText))
                .build();
    }

    public void showNotificationTextMultiLine() {
        notificationManager.notify(idNotification, createNotificationTextMultiLine());
    }

    private Notification createNotificationTextMultiLine() {
        String smallText = "Content Text";
        return new NotificationCompat.Builder(mContext, NotificationChannelUtils.CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_splash)
                .setContentTitle("Text MultiLine")
                .setContentText(smallText)
                /*Add tối đa 6 line, nếu add nhiều hơn 6 line thì 6 line đầu sẽ hiển thị*/
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
                new NotificationCompat.MessagingStyle.Message(
                        "Message 1 Message 1 Message 1 Message 1 Message 1",
                        1368436083157L,
                        new Person.Builder().setName("sender 1: ").build())
                        // set data
                        .setData("image/*", Uri.parse("https://www.w3schools.com/css/paris.jpg"));
        NotificationCompat.MessagingStyle.Message message2 =
                new NotificationCompat.MessagingStyle.Message(
                        "Message 2 Message 2 Message 2 Message 2 Message 2",
                        1368436083159L,
                        new Person.Builder().setName("sender 2: ").build());

        Person personReceiver = new Person.Builder()
                .setName("Nguyễn Duy")
                .setIcon(IconCompat.createWithResource(mContext, R.drawable.ic_menu_camera)).build();

        return new NotificationCompat.Builder(mContext, NotificationChannelUtils.CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_splash)
                .setStyle(new NotificationCompat.MessagingStyle(personReceiver)
                                /*conversion là optional*/
                                .setGroupConversation(true)
                                .setConversationTitle("Title Conversation")
                                .addMessage(message1)
                                .addMessage(message2)
                        //.addHistoricMessage(new Message("historic text", 0, "historic sender")
                )
                .build();
    }

    public void showNotificationBigImage1() {
        notificationManager.notify(idNotification, createNotificationBigImage1());
    }

    private Notification createNotificationBigImage1() {
        String smallText = "Content Text Small";
        Bitmap bitmap = BitmapFactory.decodeResource(ResourceUtils.getResource(), R.drawable.ic_splash);
        return new NotificationCompat.Builder(mContext, NotificationChannelUtils.CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_splash)
                .setContentTitle("Big Text 1")
                .setContentText(smallText)
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
        String smallText = "Content Text Small";
        Bitmap bitmap = BitmapFactory.decodeResource(ResourceUtils.getResource(), R.drawable.ic_splash);
        return new NotificationCompat.Builder(mContext, NotificationChannelUtils.CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_splash)
                .setContentTitle("Big Text 2")
                .setContentText(smallText)
                // https://developer.android.com/training/notify-user/expanded
                // set image to
                .setLargeIcon(bitmap)
                // not set image nhỏ
                .setStyle(new NotificationCompat.BigPictureStyle()
                        .bigPicture(bitmap)
                        .bigLargeIcon(null))
                .build();
    }

    public void showNotificationBigImage3() {
        notificationManager.notify(idNotification, createNotificationBigImage3());
    }

    private Notification createNotificationBigImage3() {
        String smallText = "Content Text Small";
        Bitmap bitmap = BitmapFactory.decodeResource(ResourceUtils.getResource(), R.drawable.ic_splash);
        return new NotificationCompat.Builder(mContext, NotificationChannelUtils.CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_splash)
                .setContentTitle("Big Text 3")
                .setContentText(smallText)
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

        return new NotificationCompat.Builder(mContext, NotificationChannelUtils.CHANNEL_ID)
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

        return new NotificationCompat.Builder(mContext, NotificationChannelUtils.CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_splash)
                .setContentTitle("Title Notification")
                .setContentText(contentSmall)
                .setStyle(new NotificationCompat.InboxStyle()
                        .addLine("Line 1 Line 1 Line 1 Line 1  Line 1 Line 1 Line 1 Line 1 Line 1 Line 1 Line 1")
                        .addLine("Line 2 Line 2 Line 2 Line 2  Line 2 Line 2 Line 2 Line 2 Line 2 Line 2 Line 2")
                        .addLine("Line 3 Line 3 Line 3 Line 3  Line 3 Line 3 Line 3 Line 3 Line 3 Line 3 Line 3"))
                .build();
    }*/

    //use constant ID for notification used as group summary
    int GROUP_ID = 0;

    public void showNotificationGroup() {
        notificationManager.notify(idNotification, createNotificationGroup1());
        notificationManager.notify(idNotification + 1, createNotificationGroup2());
        notificationManager.notify(GROUP_ID, createNotificationSummaryGroup());
    }

    private Notification createNotificationGroup1() {
        return new NotificationCompat.Builder(mContext, NotificationChannelUtils.CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_splash)
                .setContentTitle("Title Notification 1")
                .setContentText("Content Small 1")
                .setGroup(NotificationChannelUtils.GROUP_CHANNEL_ID)
                .build();
    }

    private Notification createNotificationGroup2() {
        return new NotificationCompat.Builder(mContext, NotificationChannelUtils.CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_splash)
                .setContentTitle("Title Notification 2")
                .setContentText("Content Small 2")
                .setGroup(NotificationChannelUtils.GROUP_CHANNEL_ID)
                .build();
    }

    private Notification createNotificationSummaryGroup() {
        return new NotificationCompat.Builder(mContext, NotificationChannelUtils.CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_splash)
                .setContentTitle("Title Group")
                //set content text to support devices running API level < 24
                .setContentText("Content Summary Group")
                .setStyle(new NotificationCompat.InboxStyle()
                        .addLine("Line 1")
                        .addLine("Line 2")
                        .setBigContentTitle("Big Content Title")
                        .setSummaryText("Summary Text"))
                //specify which group this notification belongs to
                .setGroup(NotificationChannelUtils.GROUP_CHANNEL_ID)
                //set this notification as the summary for the group
                .setGroupSummary(true)
                .build();
    }

    public void showNotificationBadge() {
        notificationManager.notify(idNotification, createNotificationBadge());
    }

    /*https://developer.android.com/training/notify-user/badges?hl=pt-br
     * https://medium.com/exploring-android/exploring-android-o-notification-badges-32e1152eb1a0*/
    private Notification createNotificationBadge() {
        String smallText = "Content Text Small";
        return new NotificationCompat.Builder(mContext, NotificationChannelUtils.CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_splash)
                .setContentTitle("Notification Badge")
                .setContentText(smallText)
                // default thì notification sẽ tự đếm
                // set custom badge notification
                .setNumber(5)
                // TODO
                //.setBadgeIconType(NotificationCompat.BADGE_ICON_NONE)
                //.setShortcutId(shortcutId)
                .build();
    }

    public void showNotificationAction() {
        notificationManager.notify(idNotification, createNotificationAction());
    }

    private Notification createNotificationAction() {
        String smallText = "Content Text Small";
        return new NotificationCompat.Builder(mContext, NotificationChannelUtils.CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_splash)
                .setContentTitle("Notification Action")
                .setContentText(smallText)
                .addAction(R.drawable.ic_menu_gallery, "Action Button", createIntentAction())
                .build();
    }

    private PendingIntent createIntentAction() {
        // có thể send intent tới service, activity, broadcast ...
        Intent actionIntent = new Intent(mContext, NotificationReceiver.class)
                .setAction("Intent Action Button");
        // có thể put thêm data
        actionIntent.putExtra(EXTRA_NOTIFICATION_ID, idNotification);
        return PendingIntent.getBroadcast(mContext, 0, actionIntent, PendingIntent.FLAG_UPDATE_CURRENT);
    }

    public void showNotificationReplyAction() {
        notificationManager.notify(idNotification, createNotificationReplyAction());
    }

    private Notification createNotificationReplyAction() {
        String smallText = "Content Text Small";
        return new NotificationCompat.Builder(mContext, NotificationChannelUtils.CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_splash)
                .setContentTitle("Notification Reply Action")
                .setContentText(smallText)
                .addAction(new ReplyActionUtils(mContext).createReplyAction())
                .build();
    }

    /*update notification khi đã replied*/
    public void updateNotificationReplyActionReplied() {
        notificationManager.notify(idNotification, createNotificationReplyActionReplied());
    }

    private Notification createNotificationReplyActionReplied() {
        return new NotificationCompat.Builder(mContext, NotificationChannelUtils.CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_splash)
                .setContentText("Replied")
                .build();
    }

    private final int PROGRESS_MAX = 100;
    private int PROGRESS_CURRENT;
    NotificationCompat.Builder mBuilder;

    /*https://developer.android.com/training/notify-user/build-notification?hl=pt-br#progressbar
    Nên sử dụng worker thread để update progressbar
    Nếu download file thì nên sử dụng  DownloadManager*/
    public void showNotificationProgressbar() {
        // create builder notification
        mBuilder = createNotificationProgressbar();
        PROGRESS_CURRENT = 0;
        mBuilder.setProgress(PROGRESS_MAX, PROGRESS_CURRENT, false);
        notificationManager.notify(idNotification, mBuilder.build());
    }

    public void increaseNotificationProgressbar() {
        if (null == mBuilder) return;
        PROGRESS_CURRENT += 10;
        // complete
        if (PROGRESS_CURRENT >= PROGRESS_MAX) {
            mBuilder.setContentText("Download Complete")
                    // remove progressbar bằng cách set max = 0, current = 0
                    .setProgress(0, 0, false);
        } else {
            // update
            mBuilder.setContentText((PROGRESS_MAX - PROGRESS_CURRENT) / 10 + " second left")
                    // set indeterminate = true để thanh progressbar chạy liên tục
                    // set indeterminate = false để thanh progressbar đứng im theo % progress current
                    .setProgress(PROGRESS_MAX, PROGRESS_CURRENT, true);
        }
        notificationManager.notify(idNotification, mBuilder.build());
    }

    private NotificationCompat.Builder createNotificationProgressbar() {
        return new NotificationCompat.Builder(mContext, NotificationChannelUtils.CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_splash)
                .setContentTitle("Notification Progressbar")
                .setContentText((PROGRESS_MAX - PROGRESS_CURRENT) / 10 + " second left");
    }

    /*Để test: vào notification --> tích vào hide content*/
    public void showNotificationLockScreenHiddenContent() {
        notificationManager.notify(idNotification, createNotificationLockScreenHiddenContent());
    }

    private Notification createNotificationLockScreenHiddenContent() {
        String smallText = "Content Text Small";
        return new NotificationCompat.Builder(mContext, NotificationChannelUtils.CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_splash)
                .setContentTitle("Notification Lock Screen Hidden Content")
                .setContentText(smallText)
                .setVisibility(NotificationCompat.VISIBILITY_PRIVATE)
                .setPublicVersion(createNotificationPublicVersion())
                .build();
    }

    private Notification createNotificationPublicVersion() {
        String smallText = "Content Text...";
        return new NotificationCompat.Builder(mContext, NotificationChannelUtils.CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_splash)
                .setContentTitle("Notification Lock Screen...")
                .setContentText(smallText)
                .build();
    }

    public void showNotificationSettingOther() {
        notificationManager.notify(idNotification, createNotificationSettingOther());
    }

    private Notification createNotificationSettingOther() {
        String smallText = "Content Text...";
        return new NotificationCompat.Builder(mContext, NotificationChannelUtils.CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_splash)
                .setContentTitle("Notification Setting Other")
                .setContentText(smallText)
                // set rung - cần permission android.permission.VIBRATE
                .setVibrate(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400})
                // TODO - chưa test
                // set light - system sẽ bật light nếu screen off
                .setLights(Color.RED, 3000, 3000)
                // set sound default
                .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)) // .setSound(Uri.parse("uri://sadfasdfasdf.mp3"));
                // Không bắt buộc phải set, chỉ nên set category khi rơi vào các category đã được defined trong NotificationCompat
                // sử dụng trong Do Not Disturb mode
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                // https://developer.android.com/training/notify-user/channels?hl=pt-br#importance
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                // auto remove notification khi user taps vào notification
                .setAutoCancel(true)
                .build();
    }

    public void showNotificationOpenRegularActivity() {
        notificationManager.notify(idNotification, createNotificationOpenRegularActivity());
    }

    /*https://developer.android.com/training/notify-user/navigation?hl=pt-br#DirectEntry*/
    private Notification createNotificationOpenRegularActivity() {
        String smallText = "Content Text...";
        return new NotificationCompat.Builder(mContext, NotificationChannelUtils.CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_splash)
                .setContentTitle("Notification Open Regular Activity")
                .setContentText(smallText)
                // set intent to Regular activity
                .setContentIntent(createIntentToRegularActivity())
                .build();
    }

    @SuppressLint("NewApi")
    private PendingIntent createIntentToRegularActivity() {
        Intent intent = new Intent(mContext, RegularActivity.class);
        if (!SDKUtils.isVersionSdkCurrentGreaterOrEqualVersionJellyBean()) {
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            return PendingIntent.getActivity(mContext, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        } else {
            // Create the TaskStackBuilder and add the intent, which inflates the back stack
            TaskStackBuilder stackBuilder = TaskStackBuilder.create(mContext).addNextIntentWithParentStack(intent);
            // Get the PendingIntent containing the entire back stack
            return stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
        }
    }

    public void showNotificationOpenSpecialActivity() {
        notificationManager.notify(idNotification, createNotificationOpenSpecialActivity());
    }

    /*https://developer.android.com/training/notify-user/navigation?hl=pt-br#ExtendedNotification*/
    private Notification createNotificationOpenSpecialActivity() {
        String smallText = "Content Text...";
        return new NotificationCompat.Builder(mContext, NotificationChannelUtils.CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_splash)
                .setContentTitle("Notification Open Special Activity")
                .setContentText(smallText)
                // set intent to Special activity
                .setContentIntent(createIntentToSpecialActivity())
                .build();
    }

    @SuppressLint("NewApi")
    private PendingIntent createIntentToSpecialActivity() {
        Intent intent = new Intent(mContext, SpecialActivity.class)
                .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        return PendingIntent.getActivity(mContext, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
    }

    public void updateNotification() {
        notificationManager.notify(idNotification, createNotificationUpdate());
    }

    /*Để update notification thì gọi lại NotificationManagerCompat.notify() với Id cũ
          Nếu ID cũ đã bị dismiss thì 1 new notification sẽ được thay thế*/
    /*Nếu push nhiều hơn 1 notification / 1s thì system có thể sẽ drop đi 1 vài notification*/
    private Notification createNotificationUpdate() {
        String smallText = "Content Text...";
        return new NotificationCompat.Builder(mContext, NotificationChannelUtils.CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_splash)
                .setContentTitle("Notification Update")
                .setContentText(smallText)
                // set rung - cần permission android.permission.VIBRATE
                .setVibrate(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400})
                // (sound, vibration, visual clues) chỉ xuất hiện ở lần đầu tiên show notification (theo ID), không apply cho update notification
                .setOnlyAlertOnce(true)
                .build();
    }

    public void removeNotification() {
        notificationManager.cancel(idNotification);
    }

    /*TODo - Chưa test*/
    public void removeNotificationTimeout() {
        notificationManager.notify(idNotification, createNotificationTimeout());
    }

    private Notification createNotificationTimeout() {
        String smallText = "Content Text...";
        return new NotificationCompat.Builder(mContext, NotificationChannelUtils.CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_splash)
                .setContentTitle("Notification Time Out")
                .setContentText(smallText)
                .setTimeoutAfter(3 * 1000)
                .build();
    }

    public void removeAllNotification() {
        notificationManager.cancelAll();
    }

    public void showCustomNotification() {
        notificationManager.notify(idNotification, createCustomNotification());
    }

    /*https://developer.android.com/training/notify-user/custom-notification?hl=pt-br*/
    private Notification createCustomNotification() {
        // Get the layouts to use in the custom notification
        RemoteViews notificationLayoutCollapsed = new RemoteViews(mContext.getPackageName(), R.layout.fragment_notification_custom_layout_collapsed);
        RemoteViews notificationLayoutExpanded = new RemoteViews(mContext.getPackageName(), R.layout.fragment_notification_custom_layout_expaned);

        setEventsCustomNotification(notificationLayoutCollapsed, notificationLayoutExpanded);

        return new NotificationCompat.Builder(mContext, NotificationChannelUtils.CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_splash)
                // Có thể dùng NotificationCompat.DecoratedMediaCustomViewStyle cho custom layout play music
                // setStyle để giữ notification icon, tên project, timestamp default của project
                .setStyle(new NotificationCompat.DecoratedCustomViewStyle())
                // setContent để support cho version old than Android 4.1 (API 16)
                .setContent(notificationLayoutCollapsed)
                .setCustomContentView(notificationLayoutCollapsed)
                .setCustomBigContentView(notificationLayoutExpanded)
                .build();
    }

    private void setEventsCustomNotification(@NonNull RemoteViews collapsed, @NonNull RemoteViews expanded) {
        PendingIntent intentPrev = createIntentCustomNotification("Prev");
        PendingIntent intentPlay = createIntentCustomNotification("Play");
        PendingIntent intentNext = createIntentCustomNotification("Next");

        collapsed.setOnClickPendingIntent(R.id.im_next_notification_music, intentNext);
        collapsed.setOnClickPendingIntent(R.id.im_play_notification_music, intentPlay);

        expanded.setOnClickPendingIntent(R.id.im_prev_notification_music, intentPrev);
        expanded.setOnClickPendingIntent(R.id.im_next_notification_music, intentNext);
        expanded.setOnClickPendingIntent(R.id.im_play_notification_music, intentPlay);
    }

    private PendingIntent createIntentCustomNotification(@NonNull String nameAction) {
        // có thể send intent tới service, activity, broadcast ...
        Intent actionIntent = new Intent(mContext, NotificationReceiver.class).setAction(nameAction);
        // có thể put thêm data
        //actionIntent.putExtra(EXTRA_NOTIFICATION_ID, idNotification);
        return PendingIntent.getBroadcast(mContext, 0, actionIntent, PendingIntent.FLAG_UPDATE_CURRENT);
    }

}