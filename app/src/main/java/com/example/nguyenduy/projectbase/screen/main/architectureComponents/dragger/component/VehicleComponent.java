package com.example.nguyenduy.projectbase.screen.main.architectureComponents.dragger.component;

import com.example.nguyenduy.projectbase.screen.main.architectureComponents.dragger.component.vehicleModule.VehicleModule;
import com.example.nguyenduy.projectbase.screen.main.architectureComponents.dragger.component.vehicleModule.provide.Vehicle;

import javax.inject.Singleton;

import dagger.Component;

// chỉ rõ module nào được sử dụng
@Singleton
@Component(modules = {VehicleModule.class})
public interface VehicleComponent {
    Vehicle getProvideVehicle();
}
