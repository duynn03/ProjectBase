package com.example.nguyenduy.projectbase.screen.main.architectureComponents.paging;

import com.example.nguyenduy.projectbase.R;
import com.example.nguyenduy.projectbase.base.BaseFragment;
import com.example.nguyenduy.projectbase.base.IBasePresenter;

public class PagingFragment extends BaseFragment<IPagingPresenter> implements IPagingView {

    @Override
    public int getIdLayout() {
        return R.layout.fragment_paging;
    }

    @Override
    public IBasePresenter initPresenter() {
        return new PagingPresenterImp(this);
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
