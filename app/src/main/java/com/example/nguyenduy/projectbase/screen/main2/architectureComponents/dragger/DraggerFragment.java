package com.example.nguyenduy.projectbase.screen.main2.architectureComponents.dragger;

import com.example.nguyenduy.projectbase.R;
import com.example.nguyenduy.projectbase.base.BaseFragment;
import com.example.nguyenduy.projectbase.base.IBasePresenter;
import com.example.nguyenduy.projectbase.screen.main2.architectureComponents.dragger.component.VehicleComponent;
import com.example.nguyenduy.projectbase.screen.main2.architectureComponents.dragger.component.vehicleModule.VehicleModule;
import com.example.nguyenduy.projectbase.screen.main2.architectureComponents.dragger.component.vehicleModule.provide.Vehicle;

public class DraggerFragment extends BaseFragment<IDraggerPresenter> implements IDraggerView {

    Vehicle vehicle;

    @Override
    public int getIdLayout() {
        return R.layout.fragment_dragger;
    }

    @Override
    public IBasePresenter initPresenter() {
        return new DraggerPresenterImp(this);
    }

    @Override
    public void initViews() {

    }

    @Override
    public void initComponents() {
       // VehicleComponent component = Dagger_VehicleComponent.builder().vehicleModule(new VehicleModule()).build();

       // vehicle = component.getProvideVehicle();
    }

    @Override
    public void setEvents() {

    }

    @Override
    public void prepareComplete() {

    }
}
