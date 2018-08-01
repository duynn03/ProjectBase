package com.example.nguyenduy.projectbase.screen.main2.permission;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.widget.ImageView;

import com.example.nguyenduy.projectbase.R;
import com.example.nguyenduy.projectbase.base.BaseFragment;
import com.example.nguyenduy.projectbase.base.IBasePresenter;
import com.example.nguyenduy.projectbase.utils.method.ViewUtils;
import com.example.nguyenduy.projectbase.utils.permission.BasePermission;
import com.example.nguyenduy.projectbase.utils.permission.PermissionUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class PermissionFragment extends BaseFragment<IPermissionPresenter> implements IPermissionView {

    private static final int PICK_PHOTO_FORM_GALLERY = 10;

    @BindView(R.id.im_gallery)
    ImageView imGallery;

    @Override
    public int getIdLayout() {
        return R.layout.fragment_permission;
    }

    @Override
    public IBasePresenter initPresenter() {
        return new PermissionPresenterImp(this);
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

    }

    @OnClick(R.id.btn_get_permission_location)
    public void getPermissionLocation() {
        PermissionUtils.checkPermissionLocation(getActivity(), new BasePermission.CallbackPermissionListener() {
            @Override
            public void onResult(boolean isSuccess, List<String> permissionDenieds) {
                if (isSuccess) {
                    showToast("get Permission Location Success!");
                } else {
                    showToast("Not get Permission Location...");
                }
            }
        });
    }

    @OnClick(R.id.btn_get_permission_write_file)
    public void getPermissionWriteFile() {
        PermissionUtils.checkPermissionWriteExternalStorage(getActivity(), new BasePermission.CallbackPermissionListener() {
            @Override
            public void onResult(boolean isSuccess, List<String> permissionDenieds) {
                if (isSuccess) {
                    showToast("get Permission write file Success!");
                } else {
                    showToast("Not get Permission write file...");
                }
            }
        });
    }

    @OnClick(R.id.btn_get_permission_write_file_and_location)
    public void getPermissionWriteFileAndLocation() {
        PermissionUtils.checkPermissionWriteExternalStorageAndLocation(getActivity(), new BasePermission.CallbackPermissionListener() {
            @Override
            public void onResult(boolean isSuccess, List<String> permissionDenieds) {
                if (isSuccess) {
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

    @OnClick(R.id.btn_get_permission_gallery)
    public void getGallery() {
        PermissionUtils.checkPermissionReadExternalStorage(getActivity(), new BasePermission.CallbackPermissionListener() {
            @Override
            public void onResult(boolean isSuccess, List<String> permissionDenieds) {
                if (isSuccess) {
                    showToast("get Permission read gallery Success!");
                    getImageFromGallery();
                } else {
                    showToast("Not get Permission read gallery file...");
                }
            }
        });
    }

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
    public void onActivityResult(int requestCode, int resultCode, Intent intentData) {
        switch (requestCode) {
            case PICK_PHOTO_FORM_GALLERY:
                if (resultCode == Activity.RESULT_OK) {
                    String linkImage = getPathLinkImage(intentData);
                    ViewUtils.setImage(imGallery, linkImage, R.drawable.ic_launcher_background);
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

}
