package com.example.nguyenduy.projectbase.screen.main2.architectureComponents.viewModel.activity;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.nguyenduy.projectbase.R;
import com.example.nguyenduy.projectbase.base.architectureComponents.database.entity.Address;
import com.example.nguyenduy.projectbase.base.architectureComponents.database.entity.User;
import com.example.nguyenduy.projectbase.screen.main2.architectureComponents.viewModel.activity.shareDataMultiFragment.DetailFragment;
import com.example.nguyenduy.projectbase.screen.main2.architectureComponents.viewModel.activity.shareDataMultiFragment.MasterFragment;
import com.example.nguyenduy.projectbase.utils.method.MethodUtils;

public class ViewModelActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tvUserName;
    private Button btnAddUser;
    private MyViewModel model;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_model);

        tvUserName = findViewById(R.id.tv_view_model_user_name);
        btnAddUser = findViewById(R.id.btn_view_model_add_user);
        btnAddUser.setOnClickListener(this);

        // Create a ViewModel the first time the system calls an activity's onCreate() method.
        // Re-created activities receive the same MyViewModel instance created by the first activity.
        model = ViewModelProviders.of(this).get(MyViewModel.class);
        model.getUsers().observe(this, users -> {
            // update UI
            if (MethodUtils.isEmpty(users)) return;
            String value = "";
            for (User user : users) {
                value += "\n" + user.getFullName();
            }
            tvUserName.setText(value);
        });

        // shared data with multi fragment
        replaceFragment(R.id.fl_master, new MasterFragment());
        replaceFragment(R.id.fl_detail, new DetailFragment());
    }

    private void replaceFragment(int frameLayoutId, Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction()
                .replace(frameLayoutId, fragment, fragment.getClass().getName());
        transaction.commit();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_view_model_add_user:
                model.addUser(new User("Nguyen", "Duy Insert View Model", new Address("Tố Hữu", "Hà đông", "Hà Nội", 20)));
                break;
        }
    }
}
