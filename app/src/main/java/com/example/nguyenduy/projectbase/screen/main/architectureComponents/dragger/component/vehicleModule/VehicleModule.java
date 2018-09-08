package com.example.nguyenduy.projectbase.screen.main.architectureComponents.dragger.component.vehicleModule;

import com.example.nguyenduy.projectbase.screen.main.architectureComponents.dragger.component.vehicleModule.provide.Vehicle;
import com.example.nguyenduy.projectbase.screen.main.architectureComponents.dragger.component.vehicleModule.provide.Motor;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class VehicleModule {

    @Provides
    @Singleton
    Motor provideMotor() {
        return new Motor();
    }

    @Provides
    @Singleton
    Vehicle provideVehicle() {
        return new Vehicle(new Motor());
    }

}
