package com.example.nguyenduy.projectbase.screen.main2.architectureComponents.viewModel.activity.shareDataMultiFragment;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.nguyenduy.projectbase.R;

public class DetailFragment extends Fragment implements View.OnClickListener {

    private Button btnIncreaseNumber;
    private TextView tvNumber;

    private SharedViewModel model;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        model = ViewModelProviders.of(getActivity()).get(SharedViewModel.class);
        model.getNumber().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable Integer number) {
                tvNumber.setText(number + "");
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_view_model_fragment_detail, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btnIncreaseNumber = view.findViewById(R.id.btn_increment_number);
        btnIncreaseNumber.setOnClickListener(this);
        tvNumber = view.findViewById(R.id.tv_number);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_increment_number:
                try {
                    model.setNumber(Integer.parseInt(tvNumber.getText().toString()) + 1);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
        }
    }
}
