package com.example.nguyenduy.projectbase.screen.main.architectureComponents.liveData.activity;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModelProviders;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nguyenduy.projectbase.R;
import com.example.nguyenduy.projectbase.application.MyApplication;
import com.example.nguyenduy.projectbase.base.architectureComponents.database.AppDatabase;
import com.example.nguyenduy.projectbase.base.architectureComponents.database.entity.Address;
import com.example.nguyenduy.projectbase.base.architectureComponents.database.entity.User;
import com.example.nguyenduy.projectbase.base.sharedPreference.SharedPreferenceUtils;
import com.example.nguyenduy.projectbase.utils.LogUtils;
import com.example.nguyenduy.projectbase.utils.method.MethodUtils;

import java.util.Arrays;
import java.util.List;

public class LiveDataActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String TAG = MethodUtils.getTagClass(LiveDataActivity.class);

    private NameViewModel nameViewModel;
    private TextView tvName;
    private Button btnUpdateData;

    private TextView tvTranformDataMap;
    private TextView tvTranformDataSwitchMapUserId;
    private TextView tvTranformDataSwitchMapUserName;
    private Button btnTranformDataSwitchMapIncreaseUserId;
    private MutableLiveData<Integer> userIdLiveData;

    private Button btnAddUser;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_livedata);

        tvName = findViewById(R.id.tv_live_data_name);
        btnUpdateData = findViewById(R.id.btn_live_data_update_data);
        btnUpdateData.setOnClickListener(this);
        // Get the ViewModel.
        nameViewModel = ViewModelProviders.of(this).get(NameViewModel.class);

        // Create the observer which updates the UI.
        final Observer<String> nameObserver = new Observer<String>() {
            @Override
            public void onChanged(@Nullable final String newName) {
                // Update the UI, in this case, a TextView.
                tvName.setText(newName);
            }
        };

        // Observe the LiveData, passing in this activity as the LifecycleOwner and the observer.
        nameViewModel.getCurrentName().observe(this, nameObserver);

        // extend livedata
        LiveData<List<Location>> locationLiveData = new ExtendLiveData(this);
        locationLiveData.observe(this, locations -> {
            // Update the UI.
            if (!MethodUtils.isEmpty(locations))
                for (Location location : locations) {
                    // Update UI with location data
                    String value = "Latitude: " + location.getLatitude()
                            + "\nLongitude: " + location.getLongitude()
                            + "\nTime: " + location.getTime();
                    Toast.makeText(MyApplication.getAppContext(), value, Toast.LENGTH_SHORT).show();
                    LogUtils.i(TAG + value);
                }
        });

        // tranform data map()
        // bất kì khi nào locationLiveData2 thay đổi thì đều update latitudeLiveData
        tvTranformDataMap = findViewById(R.id.tv_live_data_tranformdata_map);
        LiveData<List<Location>> locationLiveData2 = new ExtendLiveData(this);
        LiveData<String> latitudeLiveData = Transformations.map(locationLiveData2, locations -> {
            if (MethodUtils.isEmpty(locations)) return null;
            Location location = locations.get(0);
            return location.getLatitude() + "";
        });
        latitudeLiveData.observe(this, latitude -> {
            // Update the UI.
            if (!TextUtils.isEmpty(latitude)) {
                tvTranformDataMap.setText(latitude);
                LogUtils.i(TAG + "latitude: " + latitude);
            }
        });

        // tranform data switchMap()
        tvTranformDataSwitchMapUserId = findViewById(R.id.tv_live_data_tranformdata_switchmap_user_id);
        tvTranformDataSwitchMapUserName = findViewById(R.id.tv_live_data_tranformdata_switchmap_user_name);
        // bất kì khi nào userId thay đổi thì đều call method getUser() --> repository.getUserById(id) ==> user sẽ bị tay đổi
        userIdLiveData = new MutableLiveData<>();
        LiveData<User> userLiveData = Transformations.switchMap(userIdLiveData, id -> getUser(id));
        userIdLiveData.observe(this, userId -> {
            tvTranformDataSwitchMapUserId.setText(userId + "");
        });
        userLiveData.observe(this, user -> {
            if (null != user)
                tvTranformDataSwitchMapUserName.setText(user.getFullName());
            else
                tvTranformDataSwitchMapUserName.setText("User is null");
        });
        btnTranformDataSwitchMapIncreaseUserId = findViewById(R.id.btn_live_data_tranformdata_switchmap_increase_user_id);
        btnTranformDataSwitchMapIncreaseUserId.setOnClickListener(this);

        // merge multiple liveData
        btnAddUser = findViewById(R.id.btn_live_data_merge_multiple_live_data);
        btnAddUser.setOnClickListener(this);
        LiveData<List<User>> liveData1 = AppDatabase.getInstance().userDao().getAllUserLiveData();
        LiveData<User> liveData2 = AppDatabase.getInstance().userDao().getByIDLiveData(1);
        MediatorLiveData<List<User>> liveDataMerger = new MediatorLiveData<>();
        liveDataMerger.addSource(liveData1, new Observer<List<User>>() {
            @Override
            public void onChanged(@Nullable List<User> users) {
                if (MethodUtils.isEmpty(users)) return;
                if (users.size() >= 7)
                    liveDataMerger.removeSource(liveData1);
                else
                    liveDataMerger.setValue(users);
            }
        });
        liveDataMerger.addSource(liveData2, new Observer<User>() {
            @Override
            public void onChanged(@Nullable User user) {
                if (null == user) return;
                liveDataMerger.setValue(Arrays.asList(user));
            }
        });

        liveDataMerger.observe(this, users -> {
            if (!MethodUtils.isEmpty(users))
                for (User user : users) {
                    LogUtils.i(TAG + "id: " + user.getId() + " UserName: " + user.getFullName());
                }
        });
    }

    private LiveData<User> getUser(int id) {
        return AppDatabase.getInstance().userDao().getByIDLiveData(id);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_live_data_update_data:
                // để update data thì có thể sử dụng setValue() hoặc postValue trong MutableLiveData
                nameViewModel.getCurrentName().setValue(tvName.getText().toString() + " changed");
                break;
            case R.id.btn_live_data_tranformdata_switchmap_increase_user_id:
                userIdLiveData.setValue(Integer.parseInt(tvTranformDataSwitchMapUserId.getText().toString()) + 1);
                break;
            case R.id.btn_live_data_merge_multiple_live_data:
                long id = AppDatabase.getInstance().userDao().insert(new User("Nguyen", "Duy Insert Merge Multiple Live Data " + SharedPreferenceUtils.getInstance().getNumberIncrease(), new Address("Tố Hữu", "Hà đông", "Hà Nội", 20)));
                Toast.makeText(this, "Live Data - addItem user: id: " + (id >= 1 ? id : "null"), Toast.LENGTH_SHORT).show();
                LogUtils.i(TAG + "Live Data - addItem user: id: " + (id >= 1 ? id : "null"));
                break;
        }
    }
}
