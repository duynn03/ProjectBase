package com.example.nguyenduy.projectbase.base.backgroundTask.thread;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

public class PhotoManager {

    private static PhotoManager instance;

    private PhotoManager() {
    }

    public static synchronized PhotoManager getInstance() {
        if (instance == null) {
            instance = new PhotoManager();
        }
        return instance;
    }

    // Called by the PhotoView to get a photo
 /*   public static PhotoTask startDownload(
            PhotoView imageView,
            DownloadTask downloadTask,
            boolean cacheFlag) {
        // Adds a download task to the thread pool for execution
        instance.
                mDownloadThreadPool.
                execute(downloadTask.getHTTPDownloadRunnable());
        ...
    }

    // Defines a Handler object that's attached to the UI thread
    Handler mHandler = new Handler(Looper.getMainLooper()) {
        *//*
         * handleMessage() defines the operations to perform when
         * the Handler receives a new Message to process.
         *//*
        @Override
        public void handleMessage(Message inputMessage) {

        }

    };*/


}
