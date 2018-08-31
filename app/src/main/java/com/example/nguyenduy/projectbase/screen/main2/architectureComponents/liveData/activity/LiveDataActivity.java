package com.example.nguyenduy.projectbase.screen.main2.architectureComponents.liveData.activity;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nguyenduy.projectbase.R;
import com.example.nguyenduy.projectbase.application.MyApplication;
import com.example.nguyenduy.projectbase.utils.LogUtils;
import com.example.nguyenduy.projectbase.utils.method.MethodUtils;

import java.util.List;

public class LiveDataActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String TAG = MethodUtils.getTagClass(LiveDataActivity.class);

    private NameViewModel mModel;
    private TextView mNameTextView;
    private Button btnUpdateData;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_livedata);

        mNameTextView = findViewById(R.id.tv_live_data_name);
        btnUpdateData = findViewById(R.id.btn_live_data_update_data);
        btnUpdateData.setOnClickListener(this);
        // Get the ViewModel.
        mModel = ViewModelProviders.of(this).get(NameViewModel.class);


        // Create the observer which updates the UI.
        final Observer<String> nameObserver = new Observer<String>() {
            @Override
            public void onChanged(@Nullable final String newName) {
                // Update the UI, in this case, a TextView.
                mNameTextView.setText(newName);
            }
        };

        // Observe the LiveData, passing in this activity as the LifecycleOwner and the observer.
        mModel.getCurrentName().observe(this, nameObserver);

        // extend livedata
        LiveData<List<Location>> locationListener = new ExtendLiveData(this);
        locationListener.observe(this, locations -> {
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
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_live_data_update_data:
                // để update data thì có thể sử dụng setValue() hoặc postValue trong MutableLiveData
                mModel.getCurrentName().setValue(mNameTextView.getText().toString() + " changed");
                break;
        }
    }
}
