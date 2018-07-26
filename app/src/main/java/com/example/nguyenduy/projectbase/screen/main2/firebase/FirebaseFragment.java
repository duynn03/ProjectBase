package com.example.nguyenduy.projectbase.screen.main2.firebase;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.example.nguyenduy.projectbase.R;
import com.example.nguyenduy.projectbase.base.BaseFragment;
import com.example.nguyenduy.projectbase.base.IBasePresenter;
import com.example.nguyenduy.projectbase.base.firebase.FCM.TopicUtils;
import com.example.nguyenduy.projectbase.base.firebase.FireBaseConstants;
import com.example.nguyenduy.projectbase.base.firebase.FireBaseUtils;
import com.example.nguyenduy.projectbase.base.firebase.PerformanceUtils;
import com.google.firebase.perf.metrics.AddTrace;

import butterknife.BindView;
import butterknife.OnClick;

public class FirebaseFragment extends BaseFragment<IFirebasePresenter> implements IFirebaseView {

    @BindView(R.id.im_gallery)
    ImageView imGallery;

    @AddTrace(name = "onCreateFirebaseFragment", enabled = true)
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public int getIdLayout() {
        return R.layout.fragment_firebase;
    }

    @Override
    public IBasePresenter initPresenter() {
        return new FirebasePresenterImp(this);
    }

    @Override
    public void initViews() {

    }

    @Override
    public void initComponents() {

    }

    @Override
    public void setEvents() {

    }

    @Override
    public void prepareComplete() {

    }

    @OnClick(R.id.btn_subscribe)
    public void onClickButtonSubscribe() {
        TopicUtils.subscribe(FireBaseConstants.Topic.NEWS);
    }

    @OnClick(R.id.btn_unsubscribe)
    public void onClickButtonUnsubscribe() {
        TopicUtils.unsubscribe(FireBaseConstants.Topic.NEWS);
    }

    @OnClick(R.id.btn_generate_crash)
    public void onClickCrash() {
        // AppCenterUtils.generateCrash();
        FireBaseUtils.testCrash();
    }

    @OnClick(R.id.btn_trace)
    public void testTrace() {
        PerformanceUtils.test(imGallery);
    }


}
