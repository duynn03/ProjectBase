package com.example.nguyenduy.projectbase.base.architectureComponents.database.dto;

import android.arch.persistence.room.Relation;

import com.example.nguyenduy.projectbase.base.architectureComponents.database.entity.Book;
import com.example.nguyenduy.projectbase.base.architectureComponents.database.entity.User;

import java.util.List;

public class UserAndBookDto {

    public UserAndBookDto() {
    }

    public User user;

    @Relation(parentColumn = "id", entityColumn = "userId")
    private List<Book> books;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
