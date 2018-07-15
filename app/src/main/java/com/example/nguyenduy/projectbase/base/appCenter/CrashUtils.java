package com.example.nguyenduy.projectbase.base.appCenter;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.nguyenduy.projectbase.R;
import com.example.nguyenduy.projectbase.screen.start.crash.CrashActivity;
import com.example.nguyenduy.projectbase.utils.LogUtils;
import com.example.nguyenduy.projectbase.utils.method.ResourceUtils;
import com.microsoft.appcenter.crashes.Crashes;
import com.microsoft.appcenter.crashes.CrashesListener;
import com.microsoft.appcenter.crashes.ingestion.models.ErrorAttachmentLog;
import com.microsoft.appcenter.crashes.model.ErrorReport;
import com.microsoft.appcenter.utils.async.AppCenterConsumer;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;

public class CrashUtils implements CrashesListener {

    private final int ID_INTENT_START_ACTIVITY_CRASH = 1;
    private final String NAME_FILE_LOG_CRASH = "CrashInfor";
    private final String FORMAT_IMAGE_JPEG = "image/jpeg";

    private ICrashListener mListener;
    private Activity mActivity;

    public interface ICrashListener {
        void onComplete();
    }

    public CrashUtils(Activity activity, ICrashListener listener) {
        mActivity = activity;
        mListener = listener;
        Crashes.setEnabled(true);
        Crashes.notifyUserConfirmation(Crashes.ALWAYS_SEND);
        Crashes.setListener(this);
        checkHasCrashInLastSession();
    }

    private void checkHasCrashInLastSession() {
        Crashes.hasCrashedInLastSession().thenAccept(new AppCenterConsumer<Boolean>() {
            @Override
            public void accept(Boolean isCrashed) {
                if (isCrashed) {
                    showActivitySorryCrashed();
                } else {
                    mListener.onComplete();
                }
            }
        });
    }

    private void showActivitySorryCrashed() {
        Intent intent = new Intent(mActivity, CrashActivity.class);
        mActivity.startActivityForResult(intent, ID_INTENT_START_ACTIVITY_CRASH);
    }

    public void onActivityResult(int requestCode) {
        if (requestCode == ID_INTENT_START_ACTIVITY_CRASH && null != mListener) {
            mListener.onComplete();
        }
    }

    @Override
    public boolean shouldProcess(ErrorReport report) {
        // always send report
        return true;
    }

    @Override
    public boolean shouldAwaitUserConfirmation() {
        // always send report
        return true;
    }

    @Override
    public Iterable<ErrorAttachmentLog> getErrorAttachments(ErrorReport report) {
        return Arrays.asList(
                attachmentFileText(report),
                attachmentIconApp(report)
        );
    }

    private ErrorAttachmentLog attachmentFileText(final ErrorReport report) {
        // TODO
        String content = "Thêm thông tin vào đây null";
        return ErrorAttachmentLog.attachmentWithText(content, NAME_FILE_LOG_CRASH + ".txt");
    }

    private ErrorAttachmentLog attachmentIconApp(final ErrorReport report) {
        return ErrorAttachmentLog.attachmentWithBinary(convertIconApplicationToByte(), "icon_app.jpeg", FORMAT_IMAGE_JPEG);
    }

    private byte[] convertIconApplicationToByte() {
        Bitmap bitmap = BitmapFactory.decodeResource(ResourceUtils.getResource(), R.mipmap.ic_launcher);
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        return stream.toByteArray();
    }

    private byte[] convertFileToByte(File file) {
        if (null == file) return null;
        int size = (int) file.length();
        byte[] bytes = new byte[size];
        try {
            BufferedInputStream buf = new BufferedInputStream(new FileInputStream(file));
            buf.read(bytes, 0, bytes.length);
            buf.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bytes;
    }

    @Override
    public void onBeforeSending(ErrorReport report) {
    }

    @Override
    public void onSendingFailed(ErrorReport report, Exception e) {
        LogUtils.e("Crash onSendingFailed(): " + report.getThrowable().toString() + ", Exception: " + e.toString());
    }

    @Override
    public void onSendingSucceeded(ErrorReport report) {
        LogUtils.e("Crash onSendingSucceeded(): " + report.getThrowable().toString());
    }
}
