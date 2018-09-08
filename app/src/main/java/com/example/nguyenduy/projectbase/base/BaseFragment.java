package com.example.nguyenduy.projectbase.base;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.example.nguyenduy.projectbase.R;
import com.example.nguyenduy.projectbase.base.navigation.snackbar.SnackBarBuilder;
import com.example.nguyenduy.projectbase.utils.method.ViewUtils;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseFragment<P extends IBasePresenter> extends Fragment implements IBaseView {

    private P mPresenter;
    private Unbinder bindView;
    private ViewGroup mRootViewLayout;
    private View mRootViewFragmentCreated;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(getIdLayout(), container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRootViewFragmentCreated = view;
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
        mRootViewLayout = (ViewGroup) findViewById(getIdRootView());
    }

    public View findViewById(@IdRes int id) {
        return mRootViewFragmentCreated.findViewById(id);
    }

    private int getIdRootView() {
        return R.id.root_fragment;
    }

    protected void initBaseView() {
        //Inject View
        bindView = ButterKnife.bind(this, mRootViewFragmentCreated);
    }

    private void initBaseComponents() {
        //Create presenter for this view
        if (null == mPresenter) {
            mPresenter = initPresenter();
        }
        initKeyboard();
    }

    private void initKeyboard() {
        hideSoftKeyboard();
    }

    private void hideSoftKeyboard() {
        getRootActivity().hideSoftKeyboard(getView().getRootView());
    }

    public void showToast(String msg) {
        getRootActivity().showToast(msg);
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

    public interface CallbackAddActionDoneListener {
        void callback();
    }

    public void addActionDoneEditText(EditText view, CallbackAddActionDoneListener listener) {
        view.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    listener.callback();
                }
                return false;
            }
        });
    }

    public <A extends BaseActivity> A getRootActivity() {
        FragmentActivity activity = getActivity();
        return activity == null ? null : (A) activity;
    }

    public void addFragment(Fragment fragment, boolean isAddToBackStack) {
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction()
                .add(R.id.fl_content_fragment, fragment, fragment.getClass().getName());
        if (isAddToBackStack) {
            transaction.addToBackStack(fragment.getClass().getSimpleName());
        }
        transaction.commit();
        initKeyboard();
    }

    public void replaceFragment(Fragment fragment, boolean isAddToBackStack) {
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction()
                .replace(R.id.fl_content_fragment, fragment, fragment.getClass().getName());
        if (isAddToBackStack) {
            transaction.addToBackStack(fragment.getClass().getSimpleName());
        }
        transaction.commit();
        initKeyboard();
    }

    public void showProgress() {
        // TODO
    }

    public void hideProgress() {
        // TODO
    }

    @Override
    public void onDestroy() {
        if (bindView != null) {
            bindView.unbind();
        }
        mPresenter.onDestroy();
        super.onDestroy();
    }
}
