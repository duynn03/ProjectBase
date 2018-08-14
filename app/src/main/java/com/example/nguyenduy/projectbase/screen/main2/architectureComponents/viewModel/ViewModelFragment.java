package com.example.nguyenduy.projectbase.screen.main2.architectureComponents.viewModel;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.nguyenduy.projectbase.R;
import com.example.nguyenduy.projectbase.base.BaseFragment;
import com.example.nguyenduy.projectbase.base.IBasePresenter;

public class ViewModelFragment extends BaseFragment<IViewModelPresenter> implements IViewModelView {

    private ViewModelViewModel viewModel;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        /*viewModel = ViewModelProviders.of(this).get(ViewModelViewModel.class);

        viewModel.getUser().observe(this, user -> {
            // update UI
        });*/
    }

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
