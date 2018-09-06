package com.example.nguyenduy.projectbase.base.firebase;

import android.app.Activity;
import android.support.annotation.NonNull;

import com.example.nguyenduy.projectbase.base.BaseActivity;
import com.example.nguyenduy.projectbase.base.sharedPreference.UserInformation;
import com.example.nguyenduy.projectbase.utils.LogUtils;
import com.example.nguyenduy.projectbase.utils.method.MethodUtils;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;

public class FirebaseAuth {

    public static final String TAG = MethodUtils.getTagClass(FirebaseAuth.class);

    private com.google.firebase.auth.FirebaseAuth mAuth;

    public FirebaseAuth() {
        mAuth = com.google.firebase.auth.FirebaseAuth.getInstance();
    }

    /*nếu đã signIn thì có thể lấy tài khoản bất cứ lúc nào*/
    // nếu chưa signIn thì sẽ return về null
    public FirebaseUser getUser() {
        /*mAuth.getCurrentUser().getDisplayName();
        mAuth.getCurrentUser().getEmail();
        mAuth.getCurrentUser().isEmailVerified();
        mAuth.getCurrentUser().getPhoneNumber();
        mAuth.getCurrentUser().getPhotoUrl();
        // The user's ID, unique to the Firebase project. Do NOT use this value to
        // authenticate with your backend server, if you have one. Use
        // FirebaseUser.getIdToken() instead.
        mAuth.getCurrentUser().getUid();*/
        return mAuth.getCurrentUser();
    }

    public Task<AuthResult> signUpEmailAndPassword(BaseActivity activity, UserInformation user) {
        // check setting internet

        activity.showDialogLoading();
        return mAuth.createUserWithEmailAndPassword(user.getEmail(), user.getPassword())
                .addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        activity.hideDialogLoading();
                    }
                });
    }

    public void signInEmailAndPassword(Activity activity, String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            LogUtils.d(TAG + "signInEmailAndPassword() :success");
                        } else {
                            LogUtils.d(TAG + "signInEmailAndPassword() :failure, Exception: " + task.getException());
                        }
                    }
                });
    }

}
