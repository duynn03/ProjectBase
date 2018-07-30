package com.example.nguyenduy.projectbase.screen;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.nguyenduy.projectbase.R;
import com.example.nguyenduy.projectbase.screen.main2.MainActivity;

public class CollapsingToolbarActivity extends MainActivity {

    @Override
    public int getIdLayout() {
        return R.layout.activity_drawer_layout_collapsing_toolbar;
    }
}
