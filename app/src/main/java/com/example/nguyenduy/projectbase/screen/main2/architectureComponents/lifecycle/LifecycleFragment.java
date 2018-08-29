package com.example.nguyenduy.projectbase.screen.main2.architectureComponents.lifecycle;

import com.example.nguyenduy.projectbase.R;
import com.example.nguyenduy.projectbase.base.BaseFragment;
import com.example.nguyenduy.projectbase.base.IBasePresenter;

import butterknife.OnClick;

public class LifecycleFragment extends BaseFragment<ILifecyclePresenter> implements ILifecycleView {

    @Override
    public int getIdLayout() {
        return R.layout.fragment_lifecycle;
    }

    @Override
    public IBasePresenter initPresenter() {
        return new LifecyclePresenterImp(this);
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


    @OnClick(R.id.btn_open_activity_lifecycle_default)
    public void onClickButtonOpenActivityLifecycleDefault() {
        (getRootActivity()).startActivity(LifecycleDefaultActivity.class, null, null);
    }

    @OnClick(R.id.btn_open_activity_lifecycle_custom)
    public void onClickButtonOpenActivityLifecycleCustom() {
        (getRootActivity()).startActivity(CustomLifecycleActivity.class, null, null);
    }
}
