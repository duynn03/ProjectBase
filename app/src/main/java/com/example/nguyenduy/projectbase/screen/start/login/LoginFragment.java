package com.example.nguyenduy.projectbase.screen.start.login;

import android.app.Activity;
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

import com.example.nguyenduy.projectbase.R;
import com.example.nguyenduy.projectbase.base.BaseFragment;
import com.example.nguyenduy.projectbase.base.IBasePresenter;
import com.example.nguyenduy.projectbase.base.firebase.FireBaseUtils;
import com.example.nguyenduy.projectbase.base.listener.HandShakeListener;
import com.example.nguyenduy.projectbase.base.listener.HandShakeListenerUtils;
import com.example.nguyenduy.projectbase.utils.data.SharedPreferenceUtils;
import com.example.nguyenduy.projectbase.utils.permission.BasePermission;
import com.example.nguyenduy.projectbase.utils.permission.PermissionUtils;

import butterknife.BindView;
import butterknife.OnClick;

public class LoginFragment extends BaseFragment<ILoginPresenter> implements ILoginView, SharedPreferences.OnSharedPreferenceChangeListener, HandShakeListener.OnHandShakeListener {

    private HandShakeListener mHandShakeListener;

    @Override
    public int getIdLayout() {
        return R.layout.fragment_login;
    }

    @Override
    public IBasePresenter initPresenter() {
        return new LoginPresenterImp(this);
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
//        AppCenterUtils.generateCrash();
        FireBaseUtils.generateCrash();
    }

    @OnClick(R.id.btn_login)
    public void onClickLogin() {
        getPresenter().login();
    }

    @OnClick(R.id.btn_forgot_password)
    public void onClickForgotPassword() {

    }

    @OnClick(R.id.btn_sign_up)
    public void onClickSignUp() {

    }

    @OnClick(R.id.btn_Permission_gallery)
    public void getGallery() {
        PermissionUtils.checkPermissionReadExternalStorage(getActivity(), new BasePermission.CallbackPermissionListener() {
            @Override
            public void onResult(boolean success) {
                if (success) {
                    showToast("get Permission read gallery Success fragment!");
                    getImageFromGallery();
                } else {
                    showToast("Not get Permission read gallery file fragment...");
                }
            }
        });
    }

    private static final int PICK_PHOTO_FORM_GALLERY_FRAGMENT = 11;

    private void getImageFromGallery() {
        Intent intent;
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
            intent = new Intent(Intent.ACTION_GET_CONTENT);
            intent.setType("image/*");
            startActivityForResult(intent, PICK_PHOTO_FORM_GALLERY_FRAGMENT);
        } else {
            intent = new Intent(Intent.ACTION_PICK,
                    android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(intent, PICK_PHOTO_FORM_GALLERY_FRAGMENT);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intentData) {
        switch (requestCode) {
            case PICK_PHOTO_FORM_GALLERY_FRAGMENT:
                if (resultCode == Activity.RESULT_OK) {
                    String linkImage = getPathLinkImage(intentData);
                    showToast(linkImage);
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
        Cursor cursor = getRootActivity().getContentResolver().query(selectedImage, filePathColumn, null, null, null);
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
        showToast("SharedPreference of LoginFragment: key: " + key + ", value: " + SharedPreferenceUtils.getInstance().getValue(key, -1));
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mHandShakeListener = new HandShakeListener(this);
        HandShakeListenerUtils.getInstance().registerListener(mHandShakeListener);
    }

    @Override
    public void onDestroy() {
        HandShakeListenerUtils.getInstance().unregisterListener(mHandShakeListener);
        super.onDestroy();
    }

    @Override
    public void onShake(int count) {
        Log.e("LoginFragment", count + "");
    }

}
