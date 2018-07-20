package com.example.nguyenduy.projectbase.base.firebase;

import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

import java.io.IOException;

// https://firebase.google.com/docs/reference/android/com/google/firebase/iid/FirebaseInstanceId
public class FireBaseIdUtils {

    /**
     * return Id và auto generate token cho project
     *
     * @return
     */
    public static Task<InstanceIdResult> getInstanceId() {
        return FirebaseInstanceId.getInstance().getInstanceId();
    }

    /**
     * unique identifier của instance app
     *
     * @return
     */
    public static String getIdAppInstance() {
        return FirebaseInstanceId.getInstance().getId();
    }

    /**
     * return milliseconds since Epoch
     *
     * @return
     */
    public static long getCreationTime() {
        return FirebaseInstanceId.getInstance().getCreationTime();
    }

    /**
     * Delete Instance ID và các data liên quan tới nó
     *
     * @throws IOException - if request fails
     */
    public static void deleteInstanceId() throws IOException {
        FirebaseInstanceId.getInstance().deleteInstanceId();
    }

}
