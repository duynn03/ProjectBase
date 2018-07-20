package com.example.nguyenduy.projectbase.base.appCenter;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.nguyenduy.projectbase.R;
import com.example.nguyenduy.projectbase.screen.start.crash.CrashActivity;
import com.example.nguyenduy.projectbase.utils.LogUtils;
import com.example.nguyenduy.projectbase.utils.method.ResourceUtils;
import com.example.nguyenduy.projectbase.utils.method.SDKUtils;
import com.microsoft.appcenter.AppCenter;
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
    private final String CONTENT_TYPE_IMAGE = "image/jpeg";
    private final String CONTENT_TYPE_FILE_TO_BINARY_GREATER_VERSION_M = "binary/octet-stream";
    private final String CONTENT_TYPE_FILE_TO_BINARY_LESS_VERSION_M = "application/octet-stream";

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
                attachmentIconApp(report)//,
                // attachmentFileDatabase(report)
        );
    }

    private ErrorAttachmentLog attachmentFileText(final ErrorReport report) {
        // TODO
        StringBuilder content = new StringBuilder();
        content
                .append("InstallId: " + AppCenter.getInstallId())
                .append("\n *****************************************")
                .append("\n" + getInfoCrashApp(report));
        return ErrorAttachmentLog.attachmentWithText(content.toString(), NAME_FILE_LOG_CRASH + ".txt");
    }

    public static String getInfoCrashApp(ErrorReport report) {
        StackTraceElement[] stackTrace = null == report || null == report.getThrowable() ? null : report.getThrowable().getStackTrace();
        StringBuilder stringBuilder = new StringBuilder();
        if (null == stackTrace || stackTrace.length == 0) {
            return "";
        }
        for (StackTraceElement stackTraceElement : stackTrace) {
            stringBuilder.append("Class: ").append(stackTraceElement.getClassName()).append("\n");
            stringBuilder.append("FileName: ").append(stackTraceElement.getFileName()).append("\n");
            stringBuilder.append("Line: ").append(stackTraceElement.getLineNumber()).append("\n");
            stringBuilder.append("Method: ").append(stackTraceElement.getMethodName()).append("\n");
            stringBuilder.append(" ------------------------------------: ").append("\n");
        }
        return stringBuilder.toString();
    }

    private ErrorAttachmentLog attachmentIconApp(final ErrorReport report) {
        return ErrorAttachmentLog.attachmentWithBinary(convertIconApplicationToByte(), "icon_app.jpeg", CONTENT_TYPE_IMAGE);
    }

    private byte[] convertIconApplicationToByte() {
        Bitmap bitmap = BitmapFactory.decodeResource(ResourceUtils.getResource(), R.mipmap.ic_launcher);
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        return stream.toByteArray();
    }

    private ErrorAttachmentLog attachmentFileDatabase(final ErrorReport report) {
        String filePath = "/data/data/" + mActivity.getPackageName() + "/databases/Ezyhaul.db";
        return ErrorAttachmentLog.attachmentWithBinary(convertFileToByte(new File(filePath)), "Ezyhaul.db", getContentTypeBinary());
    }

    private String getContentTypeBinary() {
        return SDKUtils.isVersionSDKGreaterVersionM() ? CONTENT_TYPE_FILE_TO_BINARY_GREATER_VERSION_M : CONTENT_TYPE_FILE_TO_BINARY_LESS_VERSION_M;
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
        LogUtils.e("AppCenter: Crash onSendingFailed(): " + report.getThrowable().toString() + ", Exception: " + e.toString());
    }

    @Override
    public void onSendingSucceeded(ErrorReport report) {
        LogUtils.e("AppCenter: Crash onSendingSucceeded(): " + report.getThrowable().toString());
    }
}
