package com.example.nguyenduy.projectbase.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.example.nguyenduy.projectbase.R;
import com.example.nguyenduy.projectbase.utils.method.MethodContextUtils;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseFragment<P extends IBasePresenter> extends Fragment implements IBaseView {

    private P mPresenter;
    private Unbinder bindView;
    private ViewGroup mRootView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(getIdLayout(), container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initRootView(view);
        hiddenContentLayout();
        initViews();
        initBaseComponents(view);
        initComponents();
        setEvents();
        showContentLayout();
        prepareComplete();
    }

    private void initRootView(View view) {
        mRootView = view.findViewById(getIdRootView());
    }

    private int getIdRootView() {
        return R.id.root_fragment;
    }

    private void initBaseComponents(View view) {
        //Inject View
        bindView = ButterKnife.bind(this, view);

        //Create presenter for this view
        if (null == mPresenter) {
            mPresenter = initPresenter();
        }
        initEditText();
    }

    private void initEditText() {
        hideSoftKeyboard();
    }

    public void hideSoftKeyboard() {
        final InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getView().getWindowToken(), 0);
    }

    public void showToast(String msg) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
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

    public <A extends BaseActivity> A getRootActivity() {
        FragmentActivity activity = getActivity();
        return activity == null ? null : (A) activity;
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
