package com.example.nguyenduy.projectbase.screen.main2.navigation.snackbar;

import android.support.design.widget.Snackbar;
import android.widget.RelativeLayout;

import com.example.nguyenduy.projectbase.R;
import com.example.nguyenduy.projectbase.base.BaseFragment;
import com.example.nguyenduy.projectbase.base.IBasePresenter;

import butterknife.BindView;
import butterknife.OnClick;

public class SnackbarFragment extends BaseFragment<ISnackbarPresenter> implements ISnackbarView {

    @Override
    public int getIdLayout() {
        return R.layout.fragment_snack_bar;
    }

    @Override
    public IBasePresenter initPresenter() {
        return new SnackbarPresenterImp(this);
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

    @BindView(R.id.root_fragment)
    RelativeLayout rootView;

    @OnClick(R.id.btn_show_snack_bar)
    public void onClickButtonShowSnackbar() {
        Snackbar.make(rootView, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }

}
