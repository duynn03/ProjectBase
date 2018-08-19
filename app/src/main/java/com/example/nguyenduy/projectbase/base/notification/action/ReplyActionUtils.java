package com.example.nguyenduy.projectbase.base.notification.action;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.RemoteInput;

import com.example.nguyenduy.projectbase.R;

public class ReplyActionUtils {

    private Context mContext;

    public ReplyActionUtils(Context context) {
        mContext = context;
    }

    public NotificationCompat.Action createAction() {
        return new NotificationCompat.Action.Builder(R.drawable.ic_menu_send, "reply", createIntentReplyAction())
                .addRemoteInput(createRemoteInputReplyAction())
                .build();
    }

    private PendingIntent createIntentReplyAction() {
        // có thể send intent tới service, activity, broadcast ...
        Intent replyActionIntent = new Intent(mContext, ReplyActionReceiver.class)
                .setAction("Intent Reply Action Button");
        // có thể put thêm data
        //replyActionIntent.putExtra(EXTRA_CONVERSATION_ID, ConversationId);

        return PendingIntent.getBroadcast(mContext, 0, replyActionIntent, PendingIntent.FLAG_UPDATE_CURRENT);
    }

    private final String KEY_TEXT_REPLY = "key_text_reply";

    private RemoteInput createRemoteInputReplyAction() {
        return new RemoteInput.Builder(KEY_TEXT_REPLY)
                .setLabel("reply label")
                .build();
    }

    public CharSequence getReplyMessage(Intent intent) {
        Bundle remoteInput = RemoteInput.getResultsFromIntent(intent);
        if (remoteInput != null) {
            return remoteInput.getCharSequence(KEY_TEXT_REPLY);
        }
        return null;
    }

}
