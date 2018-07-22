package com.example.nguyenduy.projectbase.screen.start;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.nguyenduy.projectbase.R;
import com.example.nguyenduy.projectbase.base.BaseActivity;
import com.example.nguyenduy.projectbase.base.firebase.PerformanceUtils;
import com.example.nguyenduy.projectbase.base.listener.HandShakeListener;
import com.example.nguyenduy.projectbase.base.listener.HandShakeListenerUtils;
import com.example.nguyenduy.projectbase.screen.main.MainActivity;
import com.example.nguyenduy.projectbase.screen.start.login.LoginFragment;
import com.example.nguyenduy.projectbase.utils.data.SharedPreference.SharedPreferenceUtils;
import com.example.nguyenduy.projectbase.utils.permission.BasePermission;
import com.example.nguyenduy.projectbase.utils.permission.PermissionUtils;
import com.google.firebase.perf.metrics.AddTrace;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class StartActivity extends BaseActivity<IStartActivityPresenter> implements IStartActivityView, SharedPreferences.OnSharedPreferenceChangeListener, HandShakeListener.OnHandShakeListener {

    @BindView(R.id.im_gallery)
    ImageView imGallery;

    private HandShakeListener mHandShakeListener;

    @AddTrace(name = "onCreateTrace", enabled = true)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mHandShakeListener = new HandShakeListener(this);
        HandShakeListenerUtils.getInstance().registerListener(mHandShakeListener);
    }

    @Override
    public int getIdLayout() {
        return R.layout.activity_start;
    }

    @Override
    public IStartActivityPresenter initPresenter() {
        return new StartActivityPresenterImp(this);
    }

    @Override
    public void initViews() {

    }

    @Override
    public void initComponents() {

    }

    @Override
    public void setEvents() {

    }

    @Override
    public void prepareComplete() {
        PermissionUtils.checkPermissionInternet(this, new BasePermission.CallbackPermissionListener() {
            @Override
            public void onResult(boolean success, List<String> permissionDenieds) {
                if (!success) {
                    showToast("get Permission Internet Fail");
                }
                if (SharedPreferenceUtils.getInstance().getUserInformation() == null) {
                    addFragmentLogin();
                } else {
                    startRootActivity(MainActivity.class);
                }
            }
        });
    }

    private void addFragmentLogin() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fl_content, new LoginFragment(), LoginFragment.class.getName())
                .addToBackStack(LoginFragment.class.getName())
                .commit();
    }

    @OnClick(R.id.btn_go_to_login_fragment)
    public void goToLoginFragment() {
        addFragmentLogin();
    }

    @BindView(R.id.btn_number)
    Button btnNumber;

    private static final String NUMBER = "NUMBER";

    @OnClick(R.id.btn_number)
    public void onClickButtonNumber() {
        int number = Integer.parseInt(btnNumber.getText().toString());
        SharedPreferenceUtils.getInstance().setValue(NUMBER, number + 1);
        btnNumber.setText(SharedPreferenceUtils.getInstance().getValue(NUMBER, -1) + "");
    }

    @OnClick(R.id.btn_register_change_data_share_prefrence)
    public void registerOnSharedPreferenceChangeListener() {
        SharedPreferenceUtils.getInstance().registerOnSharedPreferenceChangeListener(this);
    }

    @OnClick(R.id.btn_unregister_change_data_share_prefrence)
    public void unRegisterOnSharedPreferenceChangeListener() {
        SharedPreferenceUtils.getInstance().unregisterOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        if (!NUMBER.equals(key)) {
            return;
        }
        showToast("SharedPreference of StartActivity: key: " + key + ", value: " + SharedPreferenceUtils.getInstance().getValue(key, -1));
    }

    @OnClick(R.id.btn_Permission_location)
    public void getPermissionLocation() {
        PermissionUtils.checkPermissionLocation(this, new BasePermission.CallbackPermissionListener() {
            @Override
            public void onResult(boolean success, List<String> permissionDenieds) {
                if (success) {
                    showToast("get Permission Location Success!");
                } else {
                    showToast("Not get Permission Location...");
                }
            }
        });
    }

    @OnClick(R.id.btn_Permission_write_file)
    public void getPermissionWriteFile() {
        PermissionUtils.checkPermissionWriteExternalStorage(this, new BasePermission.CallbackPermissionListener() {
            @Override
            public void onResult(boolean success, List<String> permissionDenieds) {
                if (success) {
                    showToast("get Permission write file Success!");
                } else {
                    showToast("Not get Permission write file...");
                }
            }
        });
    }

    @OnClick(R.id.btn_Permission_write_file_and_location)
    public void getPermissionWriteFileAndLocation() {
        PermissionUtils.checkPermissionWriteExternalStorageAndLocation(this, new BasePermission.CallbackPermissionListener() {
            @Override
            public void onResult(boolean success, List<String> permissionDenieds) {
                if (success) {
                    showToast("get Permission write file and location Success!");
                } else {
                    String permission = permissionDenieds.get(0);
                    for (int i = 1; i < permissionDenieds.size(); i++) {
                        permission += ", " + permissionDenieds.get(i);
                    }
                    showToast("Not get Permission: " + permission);
                }
            }
        });
    }

    @OnClick(R.id.btn_trace)
    public void testTrace() {
        PerformanceUtils.test(imGallery);
    }

    @OnClick(R.id.btn_Permission_gallery)
    public void getGallery() {
        PermissionUtils.checkPermissionReadExternalStorage(this, new BasePermission.CallbackPermissionListener() {
            @Override
            public void onResult(boolean success, List<String> permissionDenieds) {
                if (success) {
                    showToast("get Permission read gallery Success!");
                    getImageFromGallery();
                } else {
                    showToast("Not get Permission read gallery file...");
                }
            }
        });
    }

    private static final int PICK_PHOTO_FORM_GALLERY = 10;

    private void getImageFromGallery() {
        Intent intent;
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
            intent = new Intent(Intent.ACTION_GET_CONTENT);
            intent.setType("image/*");
            startActivityForResult(intent, PICK_PHOTO_FORM_GALLERY);
        } else {
            intent = new Intent(Intent.ACTION_PICK,
                    android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(intent, PICK_PHOTO_FORM_GALLERY);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intentData) {
        switch (requestCode) {
            case PICK_PHOTO_FORM_GALLERY:
                if (resultCode == RESULT_OK) {
                    String linkImage = getPathLinkImage(intentData);
                    Glide.with(this)
                            .load(linkImage)
                            .apply(new RequestOptions().placeholder(R.drawable.ic_launcher_background))
                            .into(imGallery);
                }
                break;
            default:
                super.onActivityResult(requestCode, resultCode, intentData);
                break;
        }
    }

    private String getPathLinkImage(Intent data) {
        // lấy ảnh chụp
        Uri selectedImage = data.getData();
        String[] filePathColumn = {MediaStore.Images.Media.DATA};
        Cursor cursor = this.getContentResolver().query(selectedImage, filePathColumn, null, null, null);
        String picturePath = "";
        if (cursor != null) {
            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            picturePath = cursor.getString(columnIndex);
            cursor.close();
        }
        // picturePath là link ảnh
        return picturePath;
    }

    @Override
    protected void onDestroy() {
        HandShakeListenerUtils.getInstance().unregisterListener(mHandShakeListener);
        super.onDestroy();
    }

    @Override
    public void onShake(int count) {
        Log.e("StartActivity", count + "");
    }

}

