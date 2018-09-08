package com.example.nguyenduy.projectbase.screen.main.architectureComponents.dataBinding;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nguyenduy.projectbase.R;
import com.example.nguyenduy.projectbase.base.BaseActivity;
import com.example.nguyenduy.projectbase.base.BaseFragment;
import com.example.nguyenduy.projectbase.base.IBasePresenter;
import com.example.nguyenduy.projectbase.base.architectureComponents.database.entity.Address;
import com.example.nguyenduy.projectbase.base.architectureComponents.database.entity.User;
import com.example.nguyenduy.projectbase.databinding.FragmentDataBindingBinding;
import com.example.nguyenduy.projectbase.screen.main.architectureComponents.dataBinding.activity.DataBindingActivity;

import butterknife.OnClick;

public class DataBindingFragment extends BaseFragment<IDataBindingPresenter> implements IDataBindingView {

    private FragmentDataBindingBinding binding;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // binding activity
        // binding = DataBindingUtil.setContentView(getRootActivity(), R.layout.fragment_data_binding);

        // get view
        // ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // binding fragment, recyclerView, ...
        binding = DataBindingUtil.inflate(inflater, getIdLayout(), container, false);
        return binding.getRoot();
    }

    @Override
    public int getIdLayout() {
        return R.layout.fragment_data_binding;
    }

    @Override
    public IBasePresenter initPresenter() {
        return new DataBindingPresenterImp(this);
    }

    @Override
    public void initViews() {

    }

    @Override
    public void initComponents() {
        User user = new User("Duy", "Nguyen", new Address());
        binding.setUser(user);
    }

    @Override
    public void setEvents() {

    }

    @Override
    public void prepareComplete() {

    }

    @OnClick(R.id.btn_open_activity_data_binding)
    public void onClickButtonOpenActivityDataBinding() {
        ((BaseActivity) getRootActivity()).startActivity(DataBindingActivity.class, null, null);
    }

}
