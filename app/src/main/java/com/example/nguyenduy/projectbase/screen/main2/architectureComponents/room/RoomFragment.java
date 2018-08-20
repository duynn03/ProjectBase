package com.example.nguyenduy.projectbase.screen.main2.architectureComponents.room;

import com.example.nguyenduy.projectbase.R;
import com.example.nguyenduy.projectbase.base.BaseFragment;
import com.example.nguyenduy.projectbase.base.IBasePresenter;
import com.example.nguyenduy.projectbase.base.architectureComponents.database.AppDatabase;
import com.example.nguyenduy.projectbase.base.architectureComponents.database.entity.Address;
import com.example.nguyenduy.projectbase.base.architectureComponents.database.entity.User;
import com.example.nguyenduy.projectbase.utils.LogUtils;
import com.example.nguyenduy.projectbase.utils.method.MethodUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.OnClick;

public class RoomFragment extends BaseFragment<IRoomPresenter> implements IRoomView {

    private static final String TAG = MethodUtils.getTagClass(RoomFragment.class);

    @Override
    public int getIdLayout() {
        return R.layout.fragment_room;
    }

    @Override
    public IBasePresenter initPresenter() {
        return new RoomPresenterImp(this);
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

    @OnClick(R.id.btn_get_list_data)
    public void onClickButtonGetListData() {
        List<User> users = AppDatabase.getInstance().userDao().getAll();
        for (User user : users) {
            LogUtils.i(TAG + "onClickButtonGetListData(): " + user.toString());
        }
    }

    @OnClick(R.id.btn_get_by_id_data)
    public void onClickButtonGetByIdData() {
        User user = AppDatabase.getInstance().userDao().getByID(1);
        LogUtils.i(TAG + "onClickButtonGetByIdData(): " + user.toString());
    }

    @OnClick(R.id.btn_get_by_name_data)
    public void onClickButtonGetByNameData() {
        User user = AppDatabase.getInstance().userDao().getByName("Duy1");
        if (null != user)
            LogUtils.i(TAG + "onClickButtonGetByNameData(): " + user.toString());
        else
            LogUtils.i(TAG + "onClickButtonGetByNameData(): null");
    }

    @OnClick(R.id.btn_insert_data)
    public void onClickButtonInsertData() {
        long id = AppDatabase.getInstance().userDao().insert(new User("Nguyen", "Duy Insert", new Address("Tố Hữu", "Hà đông", "Hà Nội", 20)));
        showToast("onClickButtonInsertData: id: " + (id >= 1 ? id : "null"));
        LogUtils.i(TAG + "onClickButtonInsertData: id: " + (id >= 1 ? id : "null"));
    }

    @OnClick(R.id.btn_insert_list_data)
    public void onClickButtonInsertListData() {
        List<User> users = new ArrayList<>();
        users.add(new User("Nguyen", "Duy Insert List 1", new Address("Tố Hữu", "Hà đông", "Hà Nội", 20)));
        users.add(new User("Nguyen", "Duy Insert List 2", new Address("Tố Hữu", "Hà đông", "Hà Nội", 20)));
        users.add(new User("Nguyen", "Duy Insert List 3", new Address("Tố Hữu", "Hà đông", "Hà Nội", 20)));
        List<Long> results = AppDatabase.getInstance().userDao().insert(users);
        showToast("onClickButtonInsertListData: ids: " + results.toString());
        LogUtils.i(TAG + "onClickButtonInsertListData: ids: " + results.toString());
    }

    @OnClick(R.id.btn_update_data)
    public void onClickButtonUpdateData() {
        User user = AppDatabase.getInstance().userDao().getByID(2);
        user.setLastName("Duy Update Data");
        int numberRecordUpdated = AppDatabase.getInstance().userDao().update(user);
        showToast("onClickButtonUpdateData: number record Updated: " + numberRecordUpdated);
        LogUtils.i(TAG + "onClickButtonUpdateData: number record Updated: " + numberRecordUpdated);
    }

    @OnClick(R.id.btn_update_list_data)
    public void onClickButtonUpdateListData() {
        User user1 = AppDatabase.getInstance().userDao().getByID(3);
        user1.setLastName("Duy Update List Data 1");
        User user2 = AppDatabase.getInstance().userDao().getByID(4);
        user2.setLastName("Duy Update List Data 2");
        int numberRecordUpdated = AppDatabase.getInstance().userDao().update(user1, user2);
        showToast("onClickButtonUpdateListData: number record Updated: " + numberRecordUpdated);
        LogUtils.i(TAG + "onClickButtonUpdateListData: number record Updated: " + numberRecordUpdated);
    }

    @OnClick(R.id.btn_delete_data)
    public void onClickButtonDeleteData() {
        User user = AppDatabase.getInstance().userDao().getByID(5);
        int numberRecordDeleted = AppDatabase.getInstance().userDao().delete(user);
        showToast("onClickButtonDeleteData: number record Updated: " + numberRecordDeleted);
        LogUtils.i(TAG + "onClickButtonDeleteData: number record Updated: " + numberRecordDeleted);
    }

    @OnClick(R.id.btn_delete_list_data)
    public void onClickButtonDeleteListData() {
        User user1 = AppDatabase.getInstance().userDao().getByID(6);
        User user2 = AppDatabase.getInstance().userDao().getByID(7);
        int numberRecordDeleted = AppDatabase.getInstance().userDao().delete(user1, user2);
        showToast("onClickButtonDeleteListData: number record Updated: " + numberRecordDeleted);
        LogUtils.i(TAG + "onClickButtonDeleteListData: number record Updated: " + numberRecordDeleted);
    }
}
