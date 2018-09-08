package com.example.nguyenduy.projectbase.screen.main.architectureComponents.room;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.widget.TextView;

import com.example.nguyenduy.projectbase.R;
import com.example.nguyenduy.projectbase.base.BaseFragment;
import com.example.nguyenduy.projectbase.base.IBasePresenter;
import com.example.nguyenduy.projectbase.base.architectureComponents.database.AppDatabase;
import com.example.nguyenduy.projectbase.base.architectureComponents.database.dto.UserAndAccountDto;
import com.example.nguyenduy.projectbase.base.architectureComponents.database.entity.Address;
import com.example.nguyenduy.projectbase.base.architectureComponents.database.entity.Repo;
import com.example.nguyenduy.projectbase.base.architectureComponents.database.entity.User;
import com.example.nguyenduy.projectbase.base.architectureComponents.database.entity.UserAndRepo;
import com.example.nguyenduy.projectbase.base.architectureComponents.database.repository.dao.UserAndAccountDao;
import com.example.nguyenduy.projectbase.base.architectureComponents.database.repository.dao.UserAndRepoDao;
import com.example.nguyenduy.projectbase.base.architectureComponents.database.repository.dao.UserDao;
import com.example.nguyenduy.projectbase.screen.main.architectureComponents.room.livedata.UserViewModel;
import com.example.nguyenduy.projectbase.utils.LogUtils;
import com.example.nguyenduy.projectbase.utils.method.MethodUtils;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

import butterknife.BindView;
import butterknife.OnClick;

public class RoomFragment extends BaseFragment<IRoomPresenter> implements IRoomView {

    private static final String TAG = MethodUtils.getTagClass(RoomFragment.class);

    private UserDao userDao;
    private UserAndRepoDao userAndRepoDao;
    private UserAndAccountDao userAndAccountDao;

    @BindView(R.id.tv_live_data)
    TextView tvLiveData;
    UserViewModel userViewModel;

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
        userDao = AppDatabase.getInstance().userDao();
        userAndRepoDao = AppDatabase.getInstance().userRepoDao();
        userAndAccountDao = AppDatabase.getInstance().userAndAccountDao();

        userViewModel = ViewModelProviders.of(this).get(UserViewModel.class);
    }

    @Override
    public void setEvents() {

    }

    @Override
    public void prepareComplete() {
        // live data
        userViewModel.getUsers().observe(this, new Observer<List<User>>() {
            @Override
            public void onChanged(@Nullable List<User> users) {
                String value = "";
                for (User user : users) {
                    value += ", " + user.getFullName();
                }
                tvLiveData.setText(value);
                LogUtils.i(TAG + "onChanged(): " + value);
            }
        });
    }

    @OnClick(R.id.btn_get_list_data)
    public void onClickButtonGetListData() {
        List<User> users = userDao.getAll();
        for (User user : users) {
            LogUtils.i(TAG + "onClickButtonGetListData(): " + user.toString());
        }
    }

    @OnClick(R.id.btn_get_by_id_data)
    public void onClickButtonGetByIdData() {
        User user = userDao.getByID(1);
        LogUtils.i(TAG + "onClickButtonGetByIdData(): " + user.toString());
    }

    @OnClick(R.id.btn_get_by_name_data)
    public void onClickButtonGetByNameData() {
        User user = userDao.getByName("Duy1");
        if (null != user)
            LogUtils.i(TAG + "onClickButtonGetByNameData(): " + user.toString());
        else
            LogUtils.i(TAG + "onClickButtonGetByNameData(): null");
    }

    @OnClick(R.id.btn_insert_data)
    public void onClickButtonInsertData() {
        long id = userDao.insert(new User("Nguyen", "Duy Insert", new Address("Tố Hữu", "Hà đông", "Hà Nội", 20)));
        showToast("onClickButtonInsertData: id: " + (id >= 1 ? id : "null"));
        LogUtils.i(TAG + "onClickButtonInsertData: id: " + (id >= 1 ? id : "null"));
    }

    @OnClick(R.id.btn_insert_list_data)
    public void onClickButtonInsertListData() {
        List<User> users = new ArrayList<>();
        users.add(new User("Nguyen", "Duy Insert List 1", new Address("Tố Hữu", "Hà đông", "Hà Nội", 20)));
        users.add(new User("Nguyen", "Duy Insert List 2", new Address("Tố Hữu", "Hà đông", "Hà Nội", 20)));
        users.add(new User("Nguyen", "Duy Insert List 3", new Address("Tố Hữu", "Hà đông", "Hà Nội", 20)));
        List<Long> results = userDao.insert(users);
        showToast("onClickButtonInsertListData: ids: " + results.toString());
        LogUtils.i(TAG + "onClickButtonInsertListData: ids: " + results.toString());
    }

    @OnClick(R.id.btn_update_data)
    public void onClickButtonUpdateData() {
        User user = AppDatabase.getInstance().userDao().getByID(2);
        user.setLastName("Duy Update Data");
        int numberRecordUpdated = userDao.update(user);
        showToast("onClickButtonUpdateData: number record Updated: " + numberRecordUpdated);
        LogUtils.i(TAG + "onClickButtonUpdateData: number record Updated: " + numberRecordUpdated);
    }

    @OnClick(R.id.btn_update_list_data)
    public void onClickButtonUpdateListData() {
        User user1 = userDao.getByID(3);
        user1.setLastName("Duy Update List Data 1");
        User user2 = userDao.getByID(4);
        user2.setLastName("Duy Update List Data 2");
        int numberRecordUpdated = userDao.update(user1, user2);
        showToast("onClickButtonUpdateListData: number record Updated: " + numberRecordUpdated);
        LogUtils.i(TAG + "onClickButtonUpdateListData: number record Updated: " + numberRecordUpdated);
    }

    @OnClick(R.id.btn_delete_data)
    public void onClickButtonDeleteData() {
        User user = userDao.getByID(5);
        int numberRecordDeleted = userDao.delete(user);
        showToast("onClickButtonDeleteData: number record Updated: " + numberRecordDeleted);
        LogUtils.i(TAG + "onClickButtonDeleteData: number record Updated: " + numberRecordDeleted);
    }

    @OnClick(R.id.btn_delete_list_data)
    public void onClickButtonDeleteListData() {
        User user1 = userDao.getByID(6);
        User user2 = userDao.getByID(7);
        int numberRecordDeleted = userDao.delete(user1, user2);
        showToast("onClickButtonDeleteListData: number record Updated: " + numberRecordDeleted);
        LogUtils.i(TAG + "onClickButtonDeleteListData: number record Updated: " + numberRecordDeleted);
    }

    @OnClick(R.id.btn_close_database)
    public void onClickButtonCloseDatabase() {
        AppDatabase.getInstance().closeDatabase();
    }

    @OnClick(R.id.btn_relation_ship_many_to_many_insert)
    public void onClickButtonManyToManyInsert() {
        long id = userAndRepoDao.insert(new UserAndRepo(3, 1));
        showToast("onClickButtonManyToManyInsert: id: " + (id >= 1 ? id : "null"));
        LogUtils.i(TAG + "onClickButtonManyToManyInsert: id: " + (id >= 1 ? id : "null"));
    }

    @OnClick(R.id.btn_relation_ship_many_to_many_get_list_user)
    public void onClickButtonManyToManyGetListUsers() {
        List<User> users = userAndRepoDao.getAllUsers(2);
        for (User user : users) {
            LogUtils.i(TAG + "onClickButtonManyToManyGetListUsers(): " + user.toString());
        }
    }

    @OnClick(R.id.btn_relation_ship_many_to_many_get_list_repo)
    public void onClickButtonManyToManyGetListRepos() {
        List<Repo> repos = userAndRepoDao.getAllRepositories(1);
        for (Repo repo : repos) {
            LogUtils.i(TAG + "onClickButtonManyToManyGetListRepos(): " + repo.toString());
        }
    }

    /*chỉ get được, không insert update được*/
    // nếu muốn update có thể dùng giống many to many
    @OnClick(R.id.btn_relation_ship_one_to_many_get_list_user_and_account)
    public void onClickButtonOneToManyGetListUserAndAccount() {
        List<UserAndAccountDto> userAndAccountDtos = userAndAccountDao.getAllUsersAndAccounts();
        for (UserAndAccountDto userAndAccount : userAndAccountDtos) {
            LogUtils.i(TAG + "onClickButtonOneToManyGetListUserAndAccount(): " + userAndAccount.toString());
        }
    }
}
