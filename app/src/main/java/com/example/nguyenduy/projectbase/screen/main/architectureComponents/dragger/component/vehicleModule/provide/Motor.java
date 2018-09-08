package com.example.nguyenduy.projectbase.screen.main.architectureComponents.dragger.component.vehicleModule.provide;

public class Motor {

    private int rpm;

    public Motor() {
        this.rpm = 0;
    }

    public void accelerate(int value) {
        rpm = rpm + value;
    }

    public void brake() {
        rpm = 0;
    }

    public int getRpm() {
        return rpm;
    }
}
