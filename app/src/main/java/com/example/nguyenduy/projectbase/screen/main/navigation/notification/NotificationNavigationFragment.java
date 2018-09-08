package com.example.nguyenduy.projectbase.screen.main.navigation.notification;

import android.widget.Button;

import com.example.nguyenduy.projectbase.R;
import com.example.nguyenduy.projectbase.base.BaseFragment;
import com.example.nguyenduy.projectbase.base.IBasePresenter;
import com.example.nguyenduy.projectbase.base.drawerlayout.BaseActivityWithHeaderUserDrawerLayout;
import com.example.nguyenduy.projectbase.utils.method.ViewUtils;

import butterknife.BindView;
import butterknife.OnClick;

public class NotificationNavigationFragment extends BaseFragment<INotificationPresenter> implements INotificationView {

    @Override
    public int getIdLayout() {
        return R.layout.fragment_notification_navigation;
    }

    @Override
    public IBasePresenter initPresenter() {
        return new NotificationPresenterImp(this);
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

    @BindView(R.id.btn_number)
    Button btnNumber;

    @OnClick(R.id.btn_increment_number)
    public void onClickButtonIncrementNumber() {
        int number = Integer.parseInt(btnNumber.getText().toString());
        number++;
        ViewUtils.setText(btnNumber, number + "");
        ((BaseActivityWithHeaderUserDrawerLayout) getActivity()).setCountMenuDrawerLayout(R.id.menu_navigation_notification, number);
    }

    @OnClick(R.id.btn_decrement_number)
    public void onClickButtonDecrementNumber() {
        int number = Integer.parseInt(btnNumber.getText().toString());
        number--;
        ViewUtils.setText(btnNumber, number + "");
        ((BaseActivityWithHeaderUserDrawerLayout) getActivity()).setCountMenuDrawerLayout(R.id.menu_navigation_notification, number);
    }
}
