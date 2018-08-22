package com.example.nguyenduy.projectbase.base.backgroundTask.thread;

import android.os.Process;

public class PhotoDecodeRunnable implements Runnable {

    @Override
    public void run() {
        // Moves the current Thread into the background
        android.os.Process.setThreadPriority(Process.THREAD_PRIORITY_BACKGROUND);
        /* Stores the current Thread in the PhotoTask instance,
         so that the instance can interrupt the Thread.*/
        // mPhotoTask.setImageDecodeThread(Thread.currentThread());
    }
}
