package com.example.nguyenduy.projectbase.base.architectureComponents.database.dto;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Relation;

import com.example.nguyenduy.projectbase.base.architectureComponents.database.entity.Account;
import com.example.nguyenduy.projectbase.base.architectureComponents.database.entity.User;
import com.example.nguyenduy.projectbase.utils.method.MethodUtils;

import java.util.List;

/* 1 User có thể có nhiều Account*/
public class UserAndAccountDto {

    public UserAndAccountDto() {

    }

    @Embedded
    private User user;

    @Relation(parentColumn = "id", entityColumn = "user_id")
    private List<Account> accounts;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        if (null != user) {
            builder.append("user: " + user.toString());
        }
        if (!MethodUtils.isEmpty(accounts)) {
            for (Account account : accounts) {
                builder.append(", account: " + account.toString());
            }
        }
        return builder.toString();
    }


}
