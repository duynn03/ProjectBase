package com.example.nguyenduy.projectbase.base;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import com.example.nguyenduy.projectbase.R;
import com.example.nguyenduy.projectbase.utils.LogUtils;
import com.example.nguyenduy.projectbase.utils.method.MethodContextUtils;
import com.example.nguyenduy.projectbase.utils.method.ResourceUtils;
import com.example.nguyenduy.projectbase.utils.permission.BasePermission;

import java.io.Serializable;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseActivity<P extends IBasePresenter> extends AppCompatActivity implements IBaseView, IBaseIntent {

    private P mPresenter;
    private Unbinder bindView;
    private Intent mIntent;
    private ViewGroup mRootView;
    private ProgressDialog loadingDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mIntent = getIntent();
        setContentView(getIdLayout());
        initRootView();
        hiddenContentLayout();
        initViews();
        initBaseComponents();
        initComponents();
        setEvents();
        showContentLayout();
        prepareComplete();
    }

    private void initRootView() {
        mRootView = findViewById(getIdRootView());
    }

    private int getIdRootView() {
        return R.id.root_activity;
    }

    private void initBaseComponents() {
        //Inject View
        bindView = ButterKnife.bind(this);

        //Create presenter for this view
        if (null == mPresenter) {
            mPresenter = initPresenter();
        }
        initEditText();
        initLoadingDialog();

    }

    private void initLoadingDialog() {
        loadingDialog = new ProgressDialog(this, R.style.ProgressBarDialog);
        loadingDialog.setMessage(ResourceUtils.getString(R.string.base_activity_dialog_loading_message_loading));
        loadingDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
    }

    private void initEditText() {
        setNotFocusWhenClickOutSideEditText(getIdRootView());
        // hidden keyboard of edit text when activity start
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }

    private void setNotFocusWhenClickOutSideEditText(int viewId) {
        if (viewId <= 0) return;
        View view = findViewById(viewId);
        // Set up touch listener for non-text box views to hide keyboard.
        if (!(view instanceof EditText)) {
            view.setOnTouchListener(new View.OnTouchListener() {
                public boolean onTouch(View v, MotionEvent event) {
                    hideSoftKeyboard();
                    return false;
                }
            });
        }

        //If a layout container, iterate over children and seed recursion.
        if (view instanceof ViewGroup) {
            for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
                View innerView = ((ViewGroup) view).getChildAt(i);
                setNotFocusWhenClickOutSideEditText(innerView.getId());
            }
        }
    }

    public void hideSoftKeyboard() {
        InputMethodManager inputManager = (InputMethodManager) this.getSystemService(Activity.INPUT_METHOD_SERVICE);
        if (this.getCurrentFocus() != null) {
            inputManager.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(), 0);
        }
    }

    public void showToast(String msg) {
        Toast.makeText(BaseActivity.this, msg, Toast.LENGTH_SHORT).show();
    }

    public P getPresenter() {
        return mPresenter;
    }

    public void hiddenContentLayout() {
        MethodContextUtils.setVisibility(mRootView, View.INVISIBLE);
    }

    public void showContentLayout() {
        MethodContextUtils.setVisibility(mRootView, View.VISIBLE);
    }

    public void showDialogLoading() {
        if (!isShowDialogLoading()) {
            loadingDialog.show();
        }
    }

    public void hideDialogLoading() {
        if (isShowDialogLoading()) {
            loadingDialog.hide();
        }
    }

    public boolean isShowDialogLoading() {
        return loadingDialog.isShowing();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        BasePermission.onRequestPermissionResult(this, requestCode, permissions, grantResults);
    }

    /**
     * Intent.FLAG_ACTIVITY_CLEAR_TASK
     * Intent.FLAG_ACTIVITY_CLEAR_TOP
     * Intent.FLAG_ACTIVITY_NEW_TASK
     * Intent.FLAG_ACTIVITY_SINGLE_TOP
     */
    public void startActivity(Class<?> clazz, Bundle data, int... intentIds) {
        Intent intent = buildIntent(clazz, data, intentIds);
        startActivity(intent);
    }

    private Intent buildIntent(Class<?> clazz, Bundle data, int... intentIds) {
        Intent intent = new Intent(this, clazz);
        if (null != data) {
            intent.putExtras(data);
        }

        if (intentIds != null && intentIds.length > 0) {
            for (int intentId : intentIds) {
                intent.addFlags(intentId);
            }
        }
        return intent;
    }

    /**
     * note: bundle nullable
     *
     * @return
     */
    private Bundle getIntentBundle() {
        return mIntent.getExtras();
    }

    @Override
    public boolean isIntentBundle() {
        return null != getIntentBundle() ? true : false;
    }

    @Override
    public <O extends Serializable> O getIntentObject(String key, O valueDefault) {
        if (!isIntentBundle()) {
            return null;
        }
        O value = (O) getIntentBundle().getSerializable(key);
        return null != value ? value : valueDefault;
    }

    @Override
    public String getIntentString(String key, String valueDefault) {
        if (!isIntentBundle()) {
            return null;
        }
        String value = getIntentBundle().getString(key);
        return !TextUtils.isEmpty(value) ? value : valueDefault;
    }

    @Override
    public Long getIntentLong(String key, long valueDefault) {
        if (!isIntentBundle()) {
            return null;
        }
        return getIntentBundle().getLong(key, valueDefault);
    }

    @Override
    public Integer getIntentInt(String key, int valueDefault) {
        if (!isIntentBundle()) {
            return null;
        }
        return getIntentBundle().getInt(key, valueDefault);
    }

    @Override
    protected void onDestroy() {
        if (bindView != null) {
            bindView.unbind();
        }
        mPresenter.onDestroy();
        super.onDestroy();
    }

    public int getCountBackStack() {
        return getSupportFragmentManager().getBackStackEntryCount();
    }

    public android.support.v4.app.FragmentManager.BackStackEntry getFragmentInBackStack(int index) {
        return index < getCountBackStack() ? null : getSupportFragmentManager().getBackStackEntryAt(index);
    }

    public Fragment getFragmentAt(int index) {
        return getCountBackStack() > 0 ? getSupportFragmentManager().findFragmentByTag(Integer.toString(index)) : null;
    }

    private void printfListBackStack() {
        List<Fragment> fragments = getSupportFragmentManager().getFragments();
        for (Fragment item : fragments) {
            LogUtils.e("Tag: " + item.getTag());
        }
    }

    public void popBackStack() {
        getSupportFragmentManager().popBackStack();
    }

    public void popBackStack(String tag) {
        getSupportFragmentManager().popBackStack();
    }

    @Override
    public void onBackPressed() {
        if (getCountBackStack() > 1) {
            popBackStack();
        } else {
            super.onBackPressed();
        }
    }

    public class BundleBuilder {
        private Bundle bundle;

        public BundleBuilder() {
            bundle = new Bundle();
        }

        public <O extends Serializable> BundleBuilder putObject(String key, O value) {
            bundle.putSerializable(key, value);
            return this;
        }

        public BundleBuilder putString(String key, String value) {
            bundle.putString(key, value);
            return this;
        }

        public BundleBuilder putInt(String key, int value) {
            bundle.putInt(key, value);
            return this;
        }

        public BundleBuilder putLong(String key, long value) {
            bundle.putLong(key, value);
            return this;
        }

        public Bundle build() {
            return bundle;
        }

    }
}
