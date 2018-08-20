package com.example.nguyenduy.projectbase.screen.main2.backgroundTask.thread;

import com.example.nguyenduy.projectbase.R;
import com.example.nguyenduy.projectbase.base.BaseFragment;
import com.example.nguyenduy.projectbase.base.IBasePresenter;

public class ThreadFragment extends BaseFragment<IThreadPresenter> implements IThreadView {

    @Override
    public int getIdLayout() {
        return R.layout.fragment_thread;
    }

    @Override
    public IBasePresenter initPresenter() {
        return new ThreadPresenterImp(this);
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
