package com.example.nguyenduy.projectbase.base;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.transition.Explode;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import com.example.nguyenduy.projectbase.R;
import com.example.nguyenduy.projectbase.base.broadcast.system.network.ConnectionInternetUtils;
import com.example.nguyenduy.projectbase.base.firebase.FireBaseUtils;
import com.example.nguyenduy.projectbase.base.location.LocationSetting;
import com.example.nguyenduy.projectbase.base.navigation.snackbar.SnackBarBuilder;
import com.example.nguyenduy.projectbase.utils.LogUtils;
import com.example.nguyenduy.projectbase.utils.method.ResourceUtils;
import com.example.nguyenduy.projectbase.utils.method.SDKUtils;
import com.example.nguyenduy.projectbase.utils.method.ViewUtils;
import com.example.nguyenduy.projectbase.utils.permission.BasePermission;

import java.io.Serializable;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseActivity<P extends IBasePresenter> extends AppCompatActivity implements IBaseView, IBaseIntent {

    /*firebase*/
    private FireBaseUtils mFireBase;

    private P mPresenter;
    private Unbinder bindView;
    private Intent mIntent;
    private ViewGroup mRootViewLayout;
    private ProgressDialog loadingDialog;

    @SuppressLint("NewApi")
    private void initAnimation() {
        if (SDKUtils.isVersionSdkCurrentGreaterOrEqualVersionLollipop()) {
            // inside your activity (if you did not enable transitions in your theme)
            getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
            // set an exit transition
            getWindow().setExitTransition(new Explode());
            getWindow().setEnterTransition(new Explode());
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        //initAnimation();
        super.onCreate(savedInstanceState);
        mFireBase = new FireBaseUtils(this);
        mIntent = getIntent();
        setContentView(getIdLayout());
        initRootView();
        hiddenContentLayout();
        initBaseView();
        initViews();
        initBaseComponents();
        initComponents();
        setEvents();
        showContentLayout();
        prepareComplete();
    }

    private void initRootView() {
        mRootViewLayout = findViewById(getIdRootView());
    }

    private int getIdRootView() {
        return R.id.root_activity;
    }

    protected void initBaseView() {
        //Inject View
        bindView = ButterKnife.bind(this);
    }

    protected void initBaseComponents() {
        //Create presenter for this view
        if (null == mPresenter) {
            mPresenter = initPresenter();
        }
        initLoadingDialog();
        initKeyboard();
    }

    private void initLoadingDialog() {
        loadingDialog = new ProgressDialog(this, R.style.ProgressBarDialog);
        loadingDialog.setMessage(ResourceUtils.getString(R.string.base_activity_dialog_loading_message_loading));
        loadingDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
    }

    private void initKeyboard() {
        hideSoftKeyboard();
    }

    /*https://stackoverflow.com/questions/1109022/close-hide-the-android-soft-keyboard*/
    private void hideSoftKeyboard() {
        //Find the currently focused view, so we can grab the correct window token from it.
        View view = this.getCurrentFocus();
        if (null == view) {
            view = new View(this);
        }
        hideSoftKeyboard(view);
    }

    public void hideSoftKeyboard(View view) {
        InputMethodManager inputManager = (InputMethodManager) this.getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public void showSoftKeyboard(View view) {
        if (null != view && view.requestFocus()) {
            InputMethodManager inputManager = (InputMethodManager) this.getSystemService(Activity.INPUT_METHOD_SERVICE);
            inputManager.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT);
        }
    }

    /* ẩn keyboard when click outside EditText https://stackoverflow.com/questions/6677969/tap-outside-edittext-to-lose-focus*/
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View view = getCurrentFocus();
            if (view != null && view instanceof EditText) {
                Rect outRect = new Rect();
                view.getGlobalVisibleRect(outRect);
                int rawX = (int) ev.getRawX();
                int rawY = (int) ev.getRawY();
                if (!outRect.contains(rawX, rawY)) {
                    view.clearFocus();
                    hideSoftKeyboard();
                }
            }
        }
        return super.dispatchTouchEvent(ev);
    }

    public void showToast(String msg) {
        Toast.makeText(BaseActivity.this, msg, Toast.LENGTH_SHORT).show();
    }

    public void showSnackbar(String message, String action, View.OnClickListener listener) {
        new SnackBarBuilder(mRootViewLayout).setText(message).setAction(action, listener).setDuration(Snackbar.LENGTH_SHORT).build().show();
    }

    public P getPresenter() {
        return mPresenter;
    }

    public void hiddenContentLayout() {
        ViewUtils.setVisibility(mRootViewLayout, View.INVISIBLE);
    }

    public void showContentLayout() {
        ViewUtils.setVisibility(mRootViewLayout, View.VISIBLE);
    }

    public void showDialogLoading() {
        if (!isShowDialogLoading()) {
            loadingDialog.show();
        }
    }

    public void hideDialogLoading() {
        if (isShowDialogLoading()) {
            loadingDialog.dismiss();
        }
    }

    public boolean isShowDialogLoading() {
        return loadingDialog.isShowing();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        BasePermission.onRequestPermissionResult(requestCode, permissions, grantResults);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        LocationSetting.onActivityResult(requestCode, resultCode, data);
        ConnectionInternetUtils.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void startRootActivity(Class<?> clazz) {
        startActivity(clazz, null, Intent.FLAG_ACTIVITY_CLEAR_TASK, Intent.FLAG_ACTIVITY_NEW_TASK);
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
     */
    private Bundle getIntentBundle() {
        return mIntent.getExtras();
    }

    @Override
    public boolean isIntentBundle() {
        return null != getIntentBundle();
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
        bindView.unbind();
        mPresenter.onDestroy();
        mFireBase.onDestroy();
        super.onDestroy();
    }

    public void addFragment(Fragment fragment, boolean isAddToBackStack) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction()
                .add(R.id.fl_content, fragment, fragment.getClass().getName());
        if (isAddToBackStack) {
            transaction.addToBackStack(fragment.getClass().getSimpleName());
        }
        transaction.commit();
        initKeyboard();
    }

    public void replaceFragment(Fragment fragment, boolean isAddToBackStack) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction()
                .replace(R.id.fl_content, fragment, fragment.getClass().getName());
        if (isAddToBackStack) {
            transaction.addToBackStack(fragment.getClass().getSimpleName());
        }
        transaction.commit();
        initKeyboard();
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
