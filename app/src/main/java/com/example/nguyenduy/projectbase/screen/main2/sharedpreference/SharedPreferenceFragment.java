package com.example.nguyenduy.projectbase.screen.main2.sharedpreference;

import com.example.nguyenduy.projectbase.R;
import com.example.nguyenduy.projectbase.base.BaseFragment;
import com.example.nguyenduy.projectbase.base.IBasePresenter;

public class SharedPreferenceFragment extends BaseFragment<ISharedPreferencePresenter> implements ISharedPreferenceView {

    @Override
    public int getIdLayout() {
        return R.layout.fragment_sharedpreference;
    }

    @Override
    public IBasePresenter initPresenter() {
        return new SharedPreferencePresenterImp(this);
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
}
