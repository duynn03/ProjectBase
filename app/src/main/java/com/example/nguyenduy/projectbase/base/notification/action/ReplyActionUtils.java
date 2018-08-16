package com.example.nguyenduy.projectbase.base.notification.action;

import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.RemoteInput;

import com.example.nguyenduy.projectbase.R;
import com.example.nguyenduy.projectbase.application.MyApplication;

public class ReplyActionUtils {

    public ReplyActionUtils() {
    }

    public static NotificationCompat.Action createAction() {
        return new NotificationCompat.Action.Builder(R.drawable.ic_menu_send,
                "reply", createIntentDirectReplyAction())
                .addRemoteInput(createRemoteInputDirectReplyAction())
                .build();
    }

    private static PendingIntent createIntentDirectReplyAction() {
        return null;
               /* PendingIntent.getBroadcast(MyApplication.getAppContext(),
                        1,
                        getMessageReplyIntent(conversation.getConversationId()),
                        PendingIntent.FLAG_UPDATE_CURRENT);*/
    }

    private final static String KEY_TEXT_REPLY = "key_text_reply";

    private static RemoteInput createRemoteInputDirectReplyAction() {
        return new RemoteInput.Builder(KEY_TEXT_REPLY)
                .setLabel("reply label")
                .build();
    }

    public static CharSequence getReplyMessage(Intent intent) {
        Bundle remoteInput = RemoteInput.getResultsFromIntent(intent);
        if (remoteInput != null) {
            return remoteInput.getCharSequence(KEY_TEXT_REPLY);
        }
        return null;
    }

}
