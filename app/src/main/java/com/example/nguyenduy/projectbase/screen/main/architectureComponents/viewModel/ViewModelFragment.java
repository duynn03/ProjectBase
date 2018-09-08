package com.example.nguyenduy.projectbase.screen.main.architectureComponents.viewModel;

import com.example.nguyenduy.projectbase.R;
import com.example.nguyenduy.projectbase.base.BaseFragment;
import com.example.nguyenduy.projectbase.base.IBasePresenter;
import com.example.nguyenduy.projectbase.screen.main.architectureComponents.viewModel.activity.ViewModelActivity;

import butterknife.OnClick;

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

    @OnClick(R.id.btn_open_activity_view_model)
    public void onClickButtonOpenSettingViewModel() {
        (getRootActivity()).startActivity(ViewModelActivity.class, null, null);
    }

}
