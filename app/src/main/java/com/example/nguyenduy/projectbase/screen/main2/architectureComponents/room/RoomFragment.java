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
        AppDatabase.getInstance().userDao().insert(new User("Nguyen", "Duy Insert", new Address("Tố Hữu", "Hà đông", "Hà Nội", 20)));
    }

    @OnClick(R.id.btn_insert_list_data)
    public void onClickButtonInsertListData() {
        List<User> users = new ArrayList<>();
        users.add(new User("Nguyen", "Duy Insert List 1", new Address("Tố Hữu", "Hà đông", "Hà Nội", 20)));
        users.add(new User("Nguyen", "Duy Insert List 2", new Address("Tố Hữu", "Hà đông", "Hà Nội", 20)));
        users.add(new User("Nguyen", "Duy Insert List 3", new Address("Tố Hữu", "Hà đông", "Hà Nội", 20)));
        AppDatabase.getInstance().userDao().insert(users.toArray(new User[users.size()]));
    }

    @OnClick(R.id.btn_update_data)
    public void onClickButtonUpdateData() {
        User user = AppDatabase.getInstance().userDao().getByID(2);
        user.setLastName("Duy Update Data");
        AppDatabase.getInstance().userDao().update(user);
    }

    @OnClick(R.id.btn_update_list_data)
    public void onClickButtonUpdateListData() {
        User user1 = AppDatabase.getInstance().userDao().getByID(3);
        user1.setLastName("Duy Update Data 1");
        User user2 = AppDatabase.getInstance().userDao().getByID(4);
        user2.setLastName("Duy Update Data 2");
        AppDatabase.getInstance().userDao().update(user1, user2);
    }

    @OnClick(R.id.btn_delete_data)
    public void onClickButtonDeleteData() {
        User user = AppDatabase.getInstance().userDao().getByID(5);
        AppDatabase.getInstance().userDao().delete(user);
    }

    @OnClick(R.id.btn_delete_list_data)
    public void onClickButtonDeleteListData() {
        User user1 = AppDatabase.getInstance().userDao().getByID(6);
        User user2 = AppDatabase.getInstance().userDao().getByID(7);
        AppDatabase.getInstance().userDao().delete(user1, user2);
    }
}
