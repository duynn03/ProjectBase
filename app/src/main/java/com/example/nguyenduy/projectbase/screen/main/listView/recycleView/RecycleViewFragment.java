package com.example.nguyenduy.projectbase.screen.main.listView.recycleView;

import android.databinding.DataBindingUtil;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.util.ArrayMap;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nguyenduy.projectbase.R;
import com.example.nguyenduy.projectbase.base.BaseFragment;
import com.example.nguyenduy.projectbase.base.IBasePresenter;
import com.example.nguyenduy.projectbase.base.architectureComponents.database.entity.Address;
import com.example.nguyenduy.projectbase.base.architectureComponents.database.entity.User;
import com.example.nguyenduy.projectbase.base.listView.recycleView.RecycleViewConstants;
import com.example.nguyenduy.projectbase.base.listView.recycleView.adapter.BaseRecycleViewAdapter;
import com.example.nguyenduy.projectbase.base.listView.recycleView.adapter.MultiTypeRecycleViewAdapter;
import com.example.nguyenduy.projectbase.base.listView.recycleView.adapter.SingleTypeRecycleViewAdapter;
import com.example.nguyenduy.projectbase.base.sharedPreference.SharedPreferenceUtils;
import com.example.nguyenduy.projectbase.databinding.FragmentRecycleViewBinding;

import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

public class RecycleViewFragment extends BaseFragment<IRecycleViewPresenter> implements IRecycleViewView {

    private FragmentRecycleViewBinding binding;

    private ObservableList<User> users;

    private SingleTypeRecycleViewAdapter<User> adapterSingleType;

    private MultiTypeRecycleViewAdapter adapterMultiType;

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
        initRecycleView();
        initSingleTypeRecycleView();
        initMultiTypeRecycleView();
    }

    private void initUser() {
        users = new ObservableArrayList<>();
        users.add(new User("firstName 1", "lastName 1", new Address()).setAvatarUrl("https://images.pexels.com/photos/457702/pexels-photo-457702.jpeg?auto=compress&cs=tinysrgb&h=750&w=1260"));
        users.add(new User("firstName 2", "lastName 2", new Address()).setAvatarUrl("https://www.w3schools.com/w3css/img_lights.jpg"));
        users.add(new User("firstName 3", "lastName 3", new Address()).setAvatarUrl("https://www.w3schools.com/w3css/img_snowtops.jpg"));
        users.add(new User("firstName 4", "lastName 4", new Address()).setAvatarUrl("https://www.w3schools.com/w3css/img_nature.jpg"));
        users.add(new User("firstName 5", "lastName 5", new Address()).setAvatarUrl("https://www.w3schools.com/w3css/img_forest.jpg"));
    }

    @BindView(R.id.srl_single_type)
    SwipeRefreshLayout srlSingleType;

    private void initRecycleView() {
        RecyclerView rvSingleType = (RecyclerView) findViewById(R.id.rv_single_type);
        // use this setting to improve performance if you know that changes
        // set khi các item không thay đổi về size và count
        //rvSingleType.setHasFixedSize(true);
/*        srlSingleType.setColorSchemeResources(R.color.colorAccent);
        srlSingleType.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // TODO
                srlSingleType.setRefreshing(false);
            }
        });*/
    }

    private void initSingleTypeRecycleView() {
        adapterSingleType = new SingleTypeRecycleViewAdapter<>(
                getRootActivity(),
                R.layout.fragment_recycle_view_single_type_item,
                new SingleTypeAdapterListener());
        adapterSingleType.setItems(users);
        binding.setAdapterSingleType(adapterSingleType);
    }

    private void initMultiTypeRecycleView() {
        Map<Integer, Integer> viewTypeAndLayout = new ArrayMap<>();
        viewTypeAndLayout.put(RecycleViewConstants.Type.HEADER, R.layout.fragment_recycle_view_multi_type_item_header);
        viewTypeAndLayout.put(RecycleViewConstants.Type.ITEM, R.layout.fragment_recycle_view_multi_type_item_item);
        viewTypeAndLayout.put(RecycleViewConstants.Type.USER, R.layout.fragment_recycle_view_multi_type_item_user);

        adapterMultiType = new MultiTypeRecycleViewAdapter(
                getContext(),
                viewTypeAndLayout,
                new MultiTypeAdapterListener());
        adapterMultiType.setItems(null, RecycleViewConstants.Type.HEADER);
        adapterMultiType.setItems(null, RecycleViewConstants.Type.ITEM);
        adapterMultiType.setItems(users, RecycleViewConstants.Type.USER);
        binding.setAdapterMultiType(adapterMultiType);
    }

    public class SingleTypeAdapterListener implements BaseRecycleViewAdapter.BaseListener {

        public void onItemClick(String value) {
            showToast("onItemClick: " + value);
        }
    }

    public class MultiTypeAdapterListener implements BaseRecycleViewAdapter.BaseListener {

        public void onItemClickUser(String value) {
            showToast("onItemClickUser: " + value);
        }
    }

    @Override
    public void setEvents() {

    }

    @Override
    public void prepareComplete() {

    }

    @OnClick(R.id.fab_add_item_single_type)
    public void onClickButtonAddItemSingleType() {
        int position = adapterSingleType.addItem(
                new User("Insert: " + SharedPreferenceUtils.getInstance().getNumberIncrease(),
                        "Insert: " + SharedPreferenceUtils.getInstance().getNumberIncrease(),
                        new Address()).setAvatarUrl("https://images.pexels.com/photos/457702/pexels-photo-457702.jpeg?auto=compress&cs=tinysrgb&h=750&w=1260"));

        showSnackbar("Add success Item Single Type With Position: " + position, "UNDO", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapterSingleType.removeItem(position);
            }
        });
    }

    @OnClick(R.id.fab_add_item_multi_type)
    public void onClickButtonAddItemMultiType() {

    }

}
