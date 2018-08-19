package com.example.nguyenduy.projectbase.base.notification;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
        return new NotificationCompat.Builder(mContext, NotificationChannelUtils.CHANNEL_ID)
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
                .setContentIntent(createIntentToRegularActivity())
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
        return new NotificationCompat.Builder(mContext, NotificationChannelUtils.CHANNEL_ID)
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

        return new NotificationCompat.Builder(mContext, NotificationChannelUtils.CHANNEL_ID)
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
        return new NotificationCompat.Builder(mContext, NotificationChannelUtils.CHANNEL_ID)
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
        return new NotificationCompat.Builder(mContext, NotificationChannelUtils.CHANNEL_ID)
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
        return new NotificationCompat.Builder(mContext, NotificationChannelUtils.CHANNEL_ID)
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
                // default thì content sẽ chỉ hiển thị trên 1 line
                .setContentText(contentSmall)
                .setStyle(new NotificationCompat.InboxStyle()
                        .addLine("Line 1 Line 1 Line 1 Line 1  Line 1 Line 1 Line 1 Line 1 Line 1 Line 1 Line 1")
                        .addLine("Line 2 Line 2 Line 2 Line 2  Line 2 Line 2 Line 2 Line 2 Line 2 Line 2 Line 2")
                        .addLine("Line 3 Line 3 Line 3 Line 3  Line 3 Line 3 Line 3 Line 3 Line 3 Line 3 Line 3"))
                .build();
    }*/

    //use constant ID for notification used as group summary
    int SUMMARY_ID = 0;

    public void showNotificationGroup() {
        notificationManager.notify(idNotification, createNotificationGroup1());
        notificationManager.notify(idNotification + 1, createNotificationGroup2());
        notificationManager.notify(SUMMARY_ID, createNotificationSummaryGroup());
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
                .setContentTitle("Title Summary Group")
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
        String contentSmall = "Content Small Notification Content Small Notification Content Small Notification Content Small Notification Content Small Notification Content Small Notification Content Small Notification Content Small Notification Content Small Notification Content Small Notification Content Small Notification Content Small Notification Content Small Notification Content Small Notification Content Small Notification";
        return new NotificationCompat.Builder(mContext, NotificationChannelUtils.CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_splash)
                .setContentTitle("Title Notification")
                .setContentText(contentSmall)
                // set custom badge notification
                // default thì notification sẽ tự đếm
                .setNumber(5)
                // TODO
                // .setBadgeIconType(NotificationCompat.BADGE_ICON_NONE)
                //.setShortcutId(shortcutId)
                .build();
    }

    public void showNotificationReplyAction() {
        notificationManager.notify(idNotification, createNotificationReplyAction());
    }

    private Notification createNotificationReplyAction() {
        return new NotificationCompat.Builder(mContext, NotificationChannelUtils.CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_splash)
                .setContentTitle("Title Notification 2")
                .setContentText("Content Small 2")
                .addAction(new ReplyActionUtils(mContext).createAction())
                .build();
    }

    /*update notification khi ddax replied*/
    public void showNotificationReplyActionReplied() {
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
                .setContentTitle("Picture Download")
                .setContentText((PROGRESS_MAX - PROGRESS_CURRENT) / 10 + " second left")
                .setPriority(NotificationCompat.PRIORITY_LOW);
    }

    /* để test: vào notification --> tích vào hide content*/
    public void showNotificationLockScreenHiddenContent() {
        notificationManager.notify(idNotification, createNotificationLockScreenHiddenContent());
    }

    private Notification createNotificationLockScreenHiddenContent() {
        String contentSmall = "Content Small Content Small Content Small Content Small Content Small";
        return new NotificationCompat.Builder(mContext, NotificationChannelUtils.CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_splash)
                .setContentTitle("Title Notification")
                .setContentText(contentSmall)
                .setVisibility(NotificationCompat.VISIBILITY_PRIVATE)
                .setPublicVersion(createNotificationPublicVersion())
                .build();
    }

    private Notification createNotificationPublicVersion() {
        String contentSmall = "Content Small Thu gọn...";
        return new NotificationCompat.Builder(mContext, NotificationChannelUtils.CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_splash)
                .setContentTitle("Title Thu gọn...")
                .setContentText(contentSmall)
                .build();
    }

    public void showNotificationSettingOther() {
        notificationManager.notify(idNotification, createNotificationSettingOther());
    }

    private Notification createNotificationSettingOther() {
        String contentSmall = "Content Small";
        return new NotificationCompat.Builder(mContext, NotificationChannelUtils.CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_splash)
                .setContentTitle("Title Notification")
                // default thì content sẽ chỉ hiển thị trên 1 line
                .setContentText(contentSmall)
                // Không bắt buộc phải set, chỉ nên set category khi rơi vào các category đã được defined trong NotificationCompat
                // sử dụng trong Do Not Disturb mode
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .build();
    }


    public void showNotificationOpenRegularActivity() {
        notificationManager.notify(idNotification, createNotificationOpenRegularActivity());
    }

    /*https://developer.android.com/training/notify-user/navigation?hl=pt-br*/
    private Notification createNotificationOpenRegularActivity() {
        return new NotificationCompat.Builder(mContext, NotificationChannelUtils.CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_splash)
                .setContentTitle("Title Notification")
                // default thì content sẽ chỉ hiển thị trên 1 line
                .setContentText("Content Small")
                // set intent to Regular activity
                // https://developer.android.com/training/notify-user/navigation?hl=pt-br#DirectEntry
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
            TaskStackBuilder stackBuilder = TaskStackBuilder.create(mContext)
                    .addNextIntentWithParentStack(intent);
            // Get the PendingIntent containing the entire back stack
            return stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
        }
    }

    public void showNotificationOpenSpecialActivity() {
        notificationManager.notify(idNotification, createNotificationOpenSpecialActivity());
    }

    /*https://developer.android.com/training/notify-user/navigation?hl=pt-br*/
    private Notification createNotificationOpenSpecialActivity() {
        return new NotificationCompat.Builder(mContext, NotificationChannelUtils.CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_splash)
                .setContentTitle("Title Notification")
                // default thì content sẽ chỉ hiển thị trên 1 line
                .setContentText("Content Small")
                // set intent to Regular activity
                // https://developer.android.com/training/notify-user/navigation?hl=pt-br#DirectEntry
                .setContentIntent(createIntentToSpecialActivity())
                .build();
    }

    @SuppressLint("NewApi")
    private PendingIntent createIntentToSpecialActivity() {
        Intent intent = new Intent(mContext, SpecialActivity.class)
                .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        return PendingIntent.getActivity(mContext, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
    }

    private final static String EXTRA_NOTIFICATION_ID = "EXTRA_NOTIFICATION_ID";

    private PendingIntent createIntentAction() {
        // có thể send intent tới service, activity, broadcast ...
        Intent actionIntent = new Intent(mContext, NotificationReceiver.class)
                .setAction("Intent Action Button");
        // có thể put thêm data
        actionIntent.putExtra(EXTRA_NOTIFICATION_ID, idNotification);
        return PendingIntent.getBroadcast(mContext, 0, actionIntent, PendingIntent.FLAG_UPDATE_CURRENT);
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
        Intent actionIntent = new Intent(mContext, NotificationReceiver.class)
                .setAction(nameAction);
        // có thể put thêm data
        //actionIntent.putExtra(EXTRA_NOTIFICATION_ID, idNotification);
        return PendingIntent.getBroadcast(mContext, 0, actionIntent, PendingIntent.FLAG_UPDATE_CURRENT);
    }

}