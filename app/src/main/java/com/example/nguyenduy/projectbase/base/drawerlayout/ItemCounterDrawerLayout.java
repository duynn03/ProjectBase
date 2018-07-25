package com.example.nguyenduy.projectbase.base.drawerlayout;

public class ItemCounterDrawerLayout {
    private int idMenu;
    private int counter;

    public ItemCounterDrawerLayout() {
    }
    
    public int getIdMenu() {
        return idMenu;
    }

    public ItemCounterDrawerLayout setIdMenu(int idMenu) {
        this.idMenu = idMenu;
        return this;
    }

    public int getCounter() {
        return counter;
    }

    public ItemCounterDrawerLayout setCounter(int counter) {
        this.counter = counter;
        return this;
    }
}
