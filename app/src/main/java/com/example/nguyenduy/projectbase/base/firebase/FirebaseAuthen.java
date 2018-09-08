package com.example.nguyenduy.projectbase.base.firebase;

import android.support.annotation.NonNull;

import com.example.nguyenduy.projectbase.base.BaseActivity;
import com.example.nguyenduy.projectbase.base.broadcast.system.network.ConnectionInternetUtils;
import com.example.nguyenduy.projectbase.base.sharedPreference.UserInformation;
import com.example.nguyenduy.projectbase.utils.method.MethodUtils;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class FirebaseAuthen {

    public static final String TAG = MethodUtils.getTagClass(FirebaseAuthen.class);

    private FirebaseAuth mAuth;

    public FirebaseAuthen() {
        mAuth = FirebaseAuth.getInstance();
        mAuth.setLanguageCode("vn");
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

    public UserInformation getUserProfile() {
        FirebaseUser firebaseUser = getUser();
        return new UserInformation()
                .setId(firebaseUser.getUid())
                .setEmail(firebaseUser.getEmail())
                .setName(firebaseUser.getDisplayName())
                .setPhoneNumber(firebaseUser.getPhoneNumber());
    }

    public void signUpEmailAndPassword(BaseActivity activity,
                                       UserInformation user,
                                       OnFailureListener onFailureListenerSignUp,
                                       OnSuccessListener<Void> onSuccessListenerUpdateUserProfile,
                                       OnFailureListener onFailureListenerUpdateUserProfile) {
        ConnectionInternetUtils.isConnectInternet(activity, new ConnectionInternetUtils.CallbackConnectionInternetListener() {
            @Override
            public void onResult(boolean isConnectInternet) {
                if (!isConnectInternet) return;
                activity.showDialogLoading();
                mAuth.createUserWithEmailAndPassword(user.getEmail(), user.getPassword())
                        .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                            @Override
                            public void onSuccess(AuthResult authResult) {
                                updateUserProfile(activity, user, onSuccessListenerUpdateUserProfile, onFailureListenerUpdateUserProfile);
                            }
                        })
                        .addOnFailureListener(onFailureListenerSignUp);
            }
        });
    }

    private void updateUserProfile(BaseActivity activity,
                                   UserInformation user,
                                   OnSuccessListener<Void> onSuccessListener,
                                   OnFailureListener onFailureListener) {
        ConnectionInternetUtils.isConnectInternet(activity, new ConnectionInternetUtils.CallbackConnectionInternetListener() {
            @Override
            public void onResult(boolean isConnectInternet) {
                if (!isConnectInternet) return;
                activity.showDialogLoading();

                UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                        .setDisplayName(user.getName())
                        .build();

                getUser().updateProfile(profileUpdates)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                activity.hideDialogLoading();
                            }
                        })
                        .addOnSuccessListener(onSuccessListener)
                        .addOnFailureListener(onFailureListener);
            }
        });
    }

    /*https://firebase.google.com/docs/auth/android/manage-users#send_a_password_reset_email*/
    public void forgotPassword(BaseActivity activity,
                               String email,
                               OnSuccessListener<Void> onSuccessListener,
                               OnFailureListener onFailureListener) {
        ConnectionInternetUtils.isConnectInternet(activity, new ConnectionInternetUtils.CallbackConnectionInternetListener() {
            @Override
            public void onResult(boolean isConnectInternet) {
                if (!isConnectInternet) return;
                activity.showDialogLoading();
                mAuth.sendPasswordResetEmail(email)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                activity.hideDialogLoading();
                            }
                        })
                        .addOnSuccessListener(onSuccessListener)
                        .addOnFailureListener(onFailureListener);
            }
        });
    }

    public void signInEmailAndPassword(BaseActivity activity,
                                       String email, String password,
                                       OnSuccessListener<AuthResult> onSuccessListener,
                                       OnFailureListener onFailureListener) {
        ConnectionInternetUtils.isConnectInternet(activity, new ConnectionInternetUtils.CallbackConnectionInternetListener() {
            @Override
            public void onResult(boolean isConnectInternet) {
                if (!isConnectInternet) return;
                activity.showDialogLoading();
                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                activity.hideDialogLoading();
                            }
                        })
                        .addOnSuccessListener(onSuccessListener)
                        .addOnFailureListener(onFailureListener);
            }
        });
    }

    public void signOut() {
        mAuth.signOut();
    }

    public void updateUserPassword(BaseActivity activity,
                                   String newPassword,
                                   OnSuccessListener<Void> onSuccessListener,
                                   OnFailureListener onFailureListener) {
        ConnectionInternetUtils.isConnectInternet(activity, new ConnectionInternetUtils.CallbackConnectionInternetListener() {
            @Override
            public void onResult(boolean isConnectInternet) {
                if (!isConnectInternet) return;
                activity.showDialogLoading();
                getUser().updatePassword(newPassword)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                activity.hideDialogLoading();
                            }
                        })
                        .addOnSuccessListener(onSuccessListener)
                        .addOnFailureListener(onFailureListener);
            }
        });
    }

}
