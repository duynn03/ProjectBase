package com.example.nguyenduy.projectbase.screen.main.listView.recycleView;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.util.ArrayMap;
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
import com.example.nguyenduy.projectbase.base.retrofit.BaseRetrofit;
import com.example.nguyenduy.projectbase.base.retrofit.api.AccountApi;
import com.example.nguyenduy.projectbase.base.retrofit.model.Account;
import com.example.nguyenduy.projectbase.base.retrofit.model.BaseResponseGet;
import com.example.nguyenduy.projectbase.base.sharedPreference.SharedPreferenceUtils;
import com.example.nguyenduy.projectbase.databinding.FragmentRecycleViewBinding;
import com.example.nguyenduy.projectbase.screen.main.listView.recycleView.adapter.AccountAdapter;
import com.example.nguyenduy.projectbase.utils.LogUtils;
import com.example.nguyenduy.projectbase.utils.method.MethodUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.OnClick;

public class RecycleViewFragment extends BaseFragment<IRecycleViewPresenter> implements IRecycleViewView {

    private static final String TAG = MethodUtils.getTagClass(RecycleViewFragment.class);

    private FragmentRecycleViewBinding binding;

    private AccountAdapter adapterAccount;

    private List<User> users;
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

        initRecycleView();

        initAccountRecycleView();
        loadAccount();

        initUser();
        initMultiTypeRecycleView();
    }

    private void initRecycleView() {
        /*RecyclerView rvSingleType = (RecyclerView) findViewById(R.id.rv_single_type);*/
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

    private void loadAccount() {
        new AccountApi().getAccounts(adapterAccount, getRootActivity(), new BaseRetrofit.IGetListener<Account>() {
            @Override
            public void onResponse(BaseResponseGet<Account> response) {
                adapterAccount.setItems(response.getData());
            }

            @Override
            public void onFailure(Throwable throwable) {
                showToast("Lỗi call API");
                LogUtils.e(TAG + "Exception: " + throwable.getMessage());
            }
        });
    }

    private void initAccountRecycleView() {
        adapterAccount = new AccountAdapter(getRootActivity());
        binding.setAdapterAccount(adapterAccount);
    }

    @OnClick(R.id.fab_add_item_account)
    public void onClickButtonAddAccount() {
        int position = adapterAccount.addItem(
                new Account()
                        .setFullName("Insert: " + SharedPreferenceUtils.getInstance().getNumberIncrease())
                        .setUserName("Insert: " + SharedPreferenceUtils.getInstance().getNumberIncrease()));

        showSnackbar("Add success Account With Position: " + position, "UNDO", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapterAccount.removeItem(position);
            }
        });
    }

    private void initUser() {
        users = new ArrayList<>();
        users.add(new User("firstName 1", "lastName 1", new Address()).setAvatarUrl("https://images.pexels.com/photos/457702/pexels-photo-457702.jpeg?auto=compress&cs=tinysrgb&h=750&w=1260"));
        users.add(new User("firstName 2", "lastName 2", new Address()).setAvatarUrl("https://www.w3schools.com/w3css/img_lights.jpg"));
        users.add(new User("firstName 3", "lastName 3", new Address()).setAvatarUrl("https://www.w3schools.com/w3css/img_snowtops.jpg"));
        users.add(new User("firstName 4", "lastName 4", new Address()).setAvatarUrl("https://www.w3schools.com/w3css/img_nature.jpg"));
        users.add(new User("firstName 5", "lastName 5", new Address()).setAvatarUrl("https://www.w3schools.com/w3css/img_forest.jpg"));
    }

    private void initMultiTypeRecycleView() {
        Map<Integer, Integer> viewTypeAndLayout = new ArrayMap<>();
        viewTypeAndLayout.put(RecycleViewConstants.Type.ITEM, R.layout.fragment_recycle_view_multi_type_item_item);
        viewTypeAndLayout.put(RecycleViewConstants.Type.USER, R.layout.fragment_recycle_view_multi_type_item_user);

        adapterMultiType = new MultiTypeRecycleViewAdapter(
                getContext(),
                viewTypeAndLayout,
                new MultiTypeAdapterListener());
        adapterMultiType.setItems(null, RecycleViewConstants.Type.ITEM);
        adapterMultiType.setItems(users, RecycleViewConstants.Type.USER);
        binding.setAdapterMultiType(adapterMultiType);
    }

    public class MultiTypeAdapterListener implements BaseRecycleViewAdapter.BaseListener {

        public void onItemClickUser(String value) {
            showToast("onItemClickUser: " + value);
        }
    }

    @OnClick(R.id.fab_add_item_multi_type)
    public void onClickButtonAddItemMultiType() {

    }

    @Override
    public void setEvents() {

    }

    @Override
    public void prepareComplete() {

    }

}
