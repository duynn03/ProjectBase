package com.example.nguyenduy.projectbase.screen.main2.architectureComponents.liveData.activity;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.nguyenduy.projectbase.R;
import com.example.nguyenduy.projectbase.screen.main2.architectureComponents.liveData.NameViewModel;

public class LiveDataActivity extends AppCompatActivity implements View.OnClickListener {

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
