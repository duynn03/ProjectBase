package com.example.nguyenduy.projectbase.database;

import io.realm.RealmObject;

public class Dog extends RealmObject {
    public Dog() {
    }

    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
