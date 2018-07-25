package com.example.nguyenduy.projectbase.screen.main2.firebase;

import com.example.nguyenduy.projectbase.R;
import com.example.nguyenduy.projectbase.base.BaseFragment;
import com.example.nguyenduy.projectbase.base.IBasePresenter;

public class FirebaseFragment extends BaseFragment<IFirebasePresenter> implements IFirebaseView {

    @Override
    public int getIdLayout() {
        return R.layout.fragment_firebase;
    }

    @Override
    public IBasePresenter initPresenter() {
        return new FirebasePresenterImp(this);
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
