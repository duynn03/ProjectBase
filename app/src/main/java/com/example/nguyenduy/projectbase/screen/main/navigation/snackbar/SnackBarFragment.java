package com.example.nguyenduy.projectbase.screen.main.navigation.snackbar;

import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.nguyenduy.projectbase.R;
import com.example.nguyenduy.projectbase.base.BaseFragment;
import com.example.nguyenduy.projectbase.base.IBasePresenter;
import com.example.nguyenduy.projectbase.base.navigation.snackbar.SnackBarBuilder;
import com.example.nguyenduy.projectbase.base.navigation.snackbar.SnackBarUtils;

import butterknife.BindView;
import butterknife.OnClick;

public class SnackBarFragment extends BaseFragment<ISnackBarPresenter> implements ISnackBarView, View.OnClickListener {

    @Override
    public int getIdLayout() {
        return R.layout.fragment_snack_bar;
    }

    @Override
    public IBasePresenter initPresenter() {
        return new SnackBarPresenterImp(this);
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

    @OnClick(R.id.btn_show_snack_bar_in_top_activity)
    public void onClickButtonShowSnackBarInTopActivity() {
        SnackBarUtils.showSnackBarInTopActivity(getRootActivity());
    }

    @OnClick(R.id.btn_show_snack_bar_in_fragment)
    public void onClickButtonShowSnackBarInFragment() {
        SnackBarUtils.showSnackBarInViewGroup(rootView);
    }

    @BindView(R.id.rl_viewgroup)
    RelativeLayout viewGroup;

    @OnClick(R.id.btn_show_snack_bar_in_view_group)
    public void onClickButtonShowSnackBarInViewGroup() {
        SnackBarUtils.showSnackBarInViewGroup(viewGroup);
    }

    @OnClick(R.id.btn_show_snack_bar_no_auto_dismiss)
    public void onClickButtonShowSnackBarNoAutoDismiss() {
        SnackBarUtils.showSnackBarNoAutoDismiss(getRootActivity());
    }

    @OnClick(R.id.btn_show_snack_bar_padding)
    public void onClickButtonShowSnackBarPadding() {
        SnackBarUtils.showSnackBarPadding(getRootActivity());
    }

    @OnClick(R.id.btn_show_snack_bar_margin)
    public void onClickButtonShowSnackBarMargin() {
        SnackBarUtils.showSnackBarMargin(getRootActivity());
    }

    @OnClick(R.id.btn_show_snack_bar_with_action)
    public void onClickButtonShowSnackBarWithButtonAction() {
        SnackBarUtils.showSnackBarWithButtonAction(getRootActivity(), this);
    }

    @OnClick(R.id.btn_show_snack_bar_with_icon)
    public void onClickButtonShowSnackBarIcon() {
        SnackBarUtils.showSnackBarWithIcon(getRootActivity(), this);
    }

    @OnClick(R.id.btn_show_snack_bar_with_floating_action_button_in_activity)
    public void onClickButtonShowSnackBarWithFloatingActionButtonInActivity() {
        SnackBarUtils.showSnackBarWithFloatingActionButtonInActivity(getRootActivity().findViewById(R.id.fab_create));
    }

    @BindView(R.id.fab_in_fragment)
    FloatingActionButton fabInFragment;

    // TODO
    @OnClick(R.id.btn_show_snack_bar_with_floating_action_button_in_fragment)
    public void onClickButtonShowSnackBarWithFloatingActionButtonInFragment() {
        SnackBarUtils.showSnackBarWithFloatingActionButtonInFragment(fabInFragment);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case SnackBarBuilder.BUTTON_ACTION_ID:
                showToast("Clicked Button Undo");
                break;
        }
    }
}
