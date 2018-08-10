package com.example.nguyenduy.projectbase.screen.main2.structure;

import com.example.nguyenduy.projectbase.R;
import com.example.nguyenduy.projectbase.base.BaseFragment;
import com.example.nguyenduy.projectbase.base.IBasePresenter;

public class ViewModelFragment extends BaseFragment<IViewModelPresenter> implements IViewModelView {

    @Override
    public int getIdLayout() {
        return R.layout.fragment_view_model;
    }

    @Override
    public IBasePresenter initPresenter() {
        return new ViewModelPresenterImp(this);
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
