package com.example.nguyenduy.projectbase.screen.main.listView.recycleView;

import com.example.nguyenduy.projectbase.R;
import com.example.nguyenduy.projectbase.base.BaseFragment;
import com.example.nguyenduy.projectbase.base.IBasePresenter;

public class RecycleViewFragment extends BaseFragment<IRecycleViewPresenter> implements IRecycleViewView {

    @Override
    public int getIdLayout() {
        return R.layout.fragment_recycle_view;
    }

    @Override
    public IBasePresenter initPresenter() {
        return new RecycleViewPresenterImp(this);
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
