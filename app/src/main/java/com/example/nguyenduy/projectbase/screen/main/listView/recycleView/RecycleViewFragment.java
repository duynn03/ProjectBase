package com.example.nguyenduy.projectbase.screen.main.listView.recycleView;

import android.databinding.DataBindingUtil;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nguyenduy.projectbase.R;
import com.example.nguyenduy.projectbase.base.BaseFragment;
import com.example.nguyenduy.projectbase.base.IBasePresenter;
import com.example.nguyenduy.projectbase.base.architectureComponents.database.entity.Address;
import com.example.nguyenduy.projectbase.base.architectureComponents.database.entity.User;
import com.example.nguyenduy.projectbase.base.listView.recycleView.adapter.BaseRecycleViewAdapter;
import com.example.nguyenduy.projectbase.base.listView.recycleView.adapter.SingleTypeRecycleViewAdapter;
import com.example.nguyenduy.projectbase.databinding.FragmentRecycleViewBinding;

public class RecycleViewFragment extends BaseFragment<IRecycleViewPresenter> implements IRecycleViewView {

    private FragmentRecycleViewBinding binding;

    private ObservableList<User> users;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // binding fragment, recyclerView, ...
        binding = DataBindingUtil.inflate(inflater, getIdLayout(), container, false);
        return binding.getRoot();
    }

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
        initUser();
        initSingleTypeRecycleView();
    }

    private void initUser() {
        users = new ObservableArrayList<>();
        for (int i = 0; i < 10; i++) {
            users.add(new User("firstName " + (i + 1), "lastName " + (i + 1), new Address()));
        }
    }

    private void initSingleTypeRecycleView() {
        SingleTypeRecycleViewAdapter<User> adapterSingleType = new SingleTypeRecycleViewAdapter<>(
                getContext(),
                R.layout.fragment_recycle_view_single_type_item,
                new ItemUserAdapterListener());
        adapterSingleType.setItems(users);
        binding.setAdapterSingleType(adapterSingleType);
    }

    @Override
    public void setEvents() {

    }

    @Override
    public void prepareComplete() {

    }

    public class ItemUserAdapterListener implements BaseRecycleViewAdapter.BaseListener {
        public void onItemClick(String value) {
            showToast("onItemClick: " + value);
        }
    }

}
