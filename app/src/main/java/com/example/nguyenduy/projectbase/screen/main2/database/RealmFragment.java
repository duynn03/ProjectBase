package com.example.nguyenduy.projectbase.screen.main2.database;

import com.example.nguyenduy.projectbase.R;
import com.example.nguyenduy.projectbase.base.BaseFragment;
import com.example.nguyenduy.projectbase.base.IBasePresenter;

public class RealmFragment extends BaseFragment<IRealmPresenter> implements IRealmView {

    @Override
    public int getIdLayout() {
        return R.layout.fragment_realm;
    }

    @Override
    public IBasePresenter initPresenter() {
        return new RealmPresenterImp(this);
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
