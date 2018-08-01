package com.example.nguyenduy.projectbase.screen;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.nguyenduy.projectbase.R;
import com.example.nguyenduy.projectbase.base.appCenter.AppCenterUtils;
import com.example.nguyenduy.projectbase.base.appCenter.CrashUtils;
import com.example.nguyenduy.projectbase.screen.main.MainActivity;
import com.example.nguyenduy.projectbase.screen.start.StartActivity;
import com.example.nguyenduy.projectbase.utils.data.SharedPreference.SharedPreferenceUtils;
import com.example.nguyenduy.projectbase.utils.permission.BasePermission;
import com.example.nguyenduy.projectbase.utils.permission.PermissionUtils;

import java.util.List;

public class SplashActivity extends AppCompatActivity {

    private AppCenterUtils mAppCenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        PermissionUtils.checkPermissionInternet(this, new BasePermission.CallbackPermissionListener() {
            @Override
            public void onResult(boolean isSuccess, List<String> permissionDenieds) {
                if (!isSuccess) {
                    finish();
                    return;
                }
                initComponents();
            }
        });
    }

    private void initComponents() {
        // app center
        mAppCenter = new AppCenterUtils(
                this,
                new CrashUtils.ICrashListener() {
                    @Override
                    public void onComplete() {
                        prepareComplete();
                    }
                });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        mAppCenter.onActivityResult(requestCode);
    }

    private void prepareComplete() {
        startActivity(!isUserLogin() ? StartActivity.class : MainActivity.class);
        finish();
    }

    // TODO
    private boolean isUserLogin() {
        return SharedPreferenceUtils.getInstance().getUserInformation() != null;
    }

    private void startActivity(Class<?> clazz) {
        Intent intent = new Intent(this, clazz);
        // root task
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

}
