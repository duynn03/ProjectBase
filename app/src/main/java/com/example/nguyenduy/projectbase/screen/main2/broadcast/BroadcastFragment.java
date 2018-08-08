package com.example.nguyenduy.projectbase.screen.main2.broadcast;

import com.example.nguyenduy.projectbase.R;
import com.example.nguyenduy.projectbase.base.BaseFragment;
import com.example.nguyenduy.projectbase.base.IBasePresenter;
import com.example.nguyenduy.projectbase.base.broadcast.custom.global.GlobalBroadcastUtils;
import com.example.nguyenduy.projectbase.base.broadcast.custom.global.GlobalReceiver;
import com.example.nguyenduy.projectbase.base.broadcast.custom.local.LocalBroadcastUtils;
import com.example.nguyenduy.projectbase.base.broadcast.custom.local.LocalReceiver;
import com.example.nguyenduy.projectbase.base.broadcast.custom.order.callback.FinalOrderReceiver;
import com.example.nguyenduy.projectbase.base.broadcast.custom.order.callback.Order3Receiver;
import com.example.nguyenduy.projectbase.base.broadcast.custom.order.callback.Order4Receiver;
import com.example.nguyenduy.projectbase.base.broadcast.custom.order.callback.OrderCallbackBroadcastUtils;
import com.example.nguyenduy.projectbase.base.broadcast.custom.order.normal.Order1Receiver;
import com.example.nguyenduy.projectbase.base.broadcast.custom.order.normal.Order2Receiver;
import com.example.nguyenduy.projectbase.base.broadcast.custom.order.normal.OrderNormalBroadcastUtils;
import com.example.nguyenduy.projectbase.base.broadcast.custom.permission.PermissionBroadcastUtils;
import com.example.nguyenduy.projectbase.base.broadcast.custom.permission.PermissionReceiver;
import com.example.nguyenduy.projectbase.base.broadcast.system.airplane.AirplaneReceiver;
import com.example.nguyenduy.projectbase.base.broadcast.system.airplane.AirplaneUtils;
import com.example.nguyenduy.projectbase.base.broadcast.system.screen.ScreenReceiver;
import com.example.nguyenduy.projectbase.base.broadcast.system.screen.ScreenUtils;
import com.example.nguyenduy.projectbase.utils.LogUtils;
import com.example.nguyenduy.projectbase.utils.method.MethodUtils;
import com.example.nguyenduy.projectbase.utils.method.SystemUtils;

import butterknife.OnClick;

public class BroadcastFragment extends BaseFragment<IBroadcastPresenter> implements IBroadcastView, AirplaneReceiver.IChangeAirplaneListener, ScreenReceiver.IChangeScreenListener, LocalReceiver.IChangeLocalListener, GlobalReceiver.IChangeGlobalListener, Order2Receiver.IChangeOrder2Listener, Order1Receiver.IChangeOrder1Listener, Order3Receiver.IChangeOrder3Listener, Order4Receiver.IChangeOrder4Listener, FinalOrderReceiver.IChangeFinalOrderListener, PermissionReceiver.IChangePermissionListener {

    private static final String TAG = MethodUtils.getTagClass(BroadcastFragment.class);

    // system
    private AirplaneUtils airplaneUtils;
    private ScreenUtils screenUtils;

    // custom
    private LocalBroadcastUtils localBroadcastUtils;
    private GlobalBroadcastUtils globalBroadcastUtils;
    private OrderNormalBroadcastUtils orderNormalBroadcastUtils;
    private OrderCallbackBroadcastUtils orderCallbackBroadcastUtils;
    private PermissionBroadcastUtils permissionBroadcastUtils;

    @Override
    public int getIdLayout() {
        return R.layout.fragment_broadcast;
    }

    @Override
    public IBasePresenter initPresenter() {
        return new BroadcastPresenterImp(this);
    }

    @Override
    public void initViews() {

    }

    @Override
    public void initComponents() {
        airplaneUtils = new AirplaneUtils(getRootActivity());
        screenUtils = new ScreenUtils(getRootActivity());

        localBroadcastUtils = new LocalBroadcastUtils(getRootActivity());
        globalBroadcastUtils = new GlobalBroadcastUtils(getRootActivity());
        orderNormalBroadcastUtils = new OrderNormalBroadcastUtils(getRootActivity());
        orderCallbackBroadcastUtils = new OrderCallbackBroadcastUtils(getRootActivity());
        permissionBroadcastUtils = new PermissionBroadcastUtils(getRootActivity());
    }

    @Override
    public void setEvents() {

    }

    @Override
    public void prepareComplete() {

    }

    @OnClick(R.id.btn_get_status_airplane)
    public void onClickButtonGetStatusAirplane() {
        LogUtils.i(TAG + "onClickButtonGetStatusAirplane(): " + SystemUtils.isAirplaneModeOn());
        showToast("onClickButtonGetStatusAirplane(): " + SystemUtils.isAirplaneModeOn());
    }

    @OnClick(R.id.btn_register_airplane)
    public void onClickButtonRegisterAirplane() {
        airplaneUtils.register(this);
    }

    @Override
    public void changeStatusAirplane(boolean isOnAirplane) {
        LogUtils.i(TAG + "changeStatusScreen(): " + isOnAirplane);
        showToast("changeStatusScreen(): " + isOnAirplane);
    }

    @OnClick(R.id.btn_unregister_airplane)
    public void onClickButtonUnregisterAirplane() {
        airplaneUtils.unregister();
    }

    @OnClick(R.id.btn_register_screen)
    public void onClickButtonRegisterScreen() {
        screenUtils.register(this);
    }

    @Override
    public void changeStatusScreen(boolean isOnScreen) {
        LogUtils.i(TAG + "changeStatusScreen: " + isOnScreen);
        showToast("changeStatusScreen: " + isOnScreen);
    }

    @OnClick(R.id.btn_unregister_screen)
    public void onClickButtonUnregisterScreen() {
        screenUtils.unregister();
    }

    private final String nameBroadcastLocal = "BROADCAST LOCAL";

    @OnClick(R.id.btn_send_broadcast_local)
    public void onClickButtonSendBroadcastLocal() {
        localBroadcastUtils.sendBroadcast(nameBroadcastLocal, "Data of Local Broadcast Custom");
    }

    @Override
    public void changeStatusLocal(String nameBroadcast, String data) {
        LogUtils.i(TAG + "changeStatusLocal(): nameBroadcast: " + nameBroadcast + ", data: " + data);
        showToast("changeStatusLocal(): nameBroadcast: " + nameBroadcast + "\ndata: " + data);
    }

    @OnClick(R.id.btn_register_broadcast_local)
    public void onClickButtonRegisterBroadcastLocal() {
        localBroadcastUtils.register(nameBroadcastLocal, this);
    }

    @OnClick(R.id.btn_unregister_broadcast_local)
    public void onClickButtonUnregisterBroadcastLocal() {
        localBroadcastUtils.unregister();
    }

    private final String nameBroadcastGlobal = "BROADCAST GLOBAL";

    @OnClick(R.id.btn_send_broadcast_global)
    public void onClickButtonSendBroadcastGlobal() {
        globalBroadcastUtils.sendBroadcast(nameBroadcastGlobal, "Data of Global Broadcast Custom");
    }

    @OnClick(R.id.btn_register_broadcast_global)
    public void onClickButtonRegisterBroadcastGlobal() {
        globalBroadcastUtils.register(nameBroadcastGlobal, this);
    }

    @Override
    public void changeStatusGlobal(String nameBroadcast, String data) {
        LogUtils.i(TAG + "changeStatusPermission(): nameBroadcast: " + nameBroadcast + ", data: " + data);
        showToast("changeStatusPermission(): nameBroadcast: " + nameBroadcast + "\ndata: " + data);
    }

    @OnClick(R.id.btn_unregister_broadcast_global)
    public void onClickButtonUnregisterBroadcastGlobal() {
        globalBroadcastUtils.unregister();
    }

    private final String nameBroadcastOrderNormal = "BROADCAST ORDER NORMAL";

    @OnClick(R.id.btn_send_broadcast_order)
    public void onClickButtonSendBroadcastOrder() {
        orderNormalBroadcastUtils.sendBroadcast(nameBroadcastOrderNormal, "Data of Order Normal Broadcast Custom");
    }

    @OnClick(R.id.btn_register_broadcast_order)
    public void onClickButtonRegisterBroadcastOrder() {
        orderNormalBroadcastUtils.register(nameBroadcastOrderNormal, this, this);
    }

    @Override
    public void changeStatusOrder1(String nameBroadcast, String data) {
        LogUtils.i(TAG + "changeStatusOrder1(): nameBroadcast: " + nameBroadcast + ", data: " + data);
        showToast("changeStatusOrder1(): nameBroadcast: " + nameBroadcast + "\ndata: " + data);
    }

    @Override
    public void changeStatusOrder2(String nameBroadcast, String data) {
        LogUtils.i(TAG + "changeStatusOrder2(): nameBroadcast: " + nameBroadcast + ", data: " + data);
        showToast("changeStatusOrder2(): nameBroadcast: " + nameBroadcast + "\ndata: " + data);
    }

    @OnClick(R.id.btn_unregister_broadcast_order)
    public void onClickButtonUnregisterBroadcastOrder() {
        orderNormalBroadcastUtils.unregister();
    }

    private final String nameBroadcastOrderCallback = "BROADCAST ORDER CALLBACK";

    @OnClick(R.id.btn_send_broadcast_order_callback)
    public void onClickButtonSendBroadcastOrderCallback() {
        orderCallbackBroadcastUtils.sendBroadcast(nameBroadcastOrderCallback, "Data of Order Callback Broadcast Custom");
    }

    @OnClick(R.id.btn_register_broadcast_order_callback)
    public void onClickButtonRegisterBroadcastOrderCallback() {
        orderCallbackBroadcastUtils.register(nameBroadcastOrderCallback, this, this, this);
    }

    @Override
    public void changeStatusOrder3(String nameBroadcast, String data) {
        LogUtils.i(TAG + "changeStatusOrder3(): nameBroadcast: " + nameBroadcast + ", data: " + data);
        showToast("changeStatusOrder3(): nameBroadcast: " + nameBroadcast + "\ndata: " + data);
    }

    @Override
    public void changeStatusOrder4(String nameBroadcast, String data) {
        LogUtils.i(TAG + "changeStatusOrder4(): nameBroadcast: " + nameBroadcast + ", data: " + data);
        showToast("changeStatusOrder4(): nameBroadcast: " + nameBroadcast + "\ndata: " + data);
    }

    @Override
    public void changeStatusFinalOrder(String nameBroadcast, String data) {
        LogUtils.i(TAG + "changeStatusFinalOrder(): nameBroadcast: " + nameBroadcast + ", data: " + data);
        showToast("changeStatusFinalOrder(): nameBroadcast: " + nameBroadcast + "\ndata: " + data);
    }

    @OnClick(R.id.btn_unregister_broadcast_order_callback)
    public void onClickButtonUnregisterBroadcastOrderCallback() {
        orderCallbackBroadcastUtils.unregister();
    }

    private final String nameBroadcastPermission = "BROADCAST PERMISSION";

    @OnClick(R.id.btn_send_broadcast_with_permission)
    public void onClickButtonSendBroadcastPermission() {
        permissionBroadcastUtils.sendBroadcast(nameBroadcastPermission, "Data of Order Callback Broadcast Custom");
    }

    @OnClick(R.id.btn_register_broadcast_with_permission)
    public void onClickButtonRegisterBroadcastPermission() {
        permissionBroadcastUtils.register(nameBroadcastPermission, this);
    }

    @Override
    public void changeStatusPermission(String nameBroadcast, String data) {
        LogUtils.i(TAG + "changeStatusPermission(): nameBroadcast: " + nameBroadcast + ", data: " + data);
        showToast("changeStatusPermission(): nameBroadcast: " + nameBroadcast + "\ndata: " + data);
    }

    @OnClick(R.id.btn_unregister_broadcast_with_permission)
    public void onClickButtonUnregisterBroadcastPermission() {
        permissionBroadcastUtils.unregister();
    }
}
