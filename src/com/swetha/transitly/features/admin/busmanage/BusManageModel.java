package com.swetha.transitly.features.admin.busmanage;

import com.swetha.transitly.data.dto.Bus;
import com.swetha.transitly.data.repository.TransitlyDB;

import java.util.ArrayList;

class BusManageModel {
    private final BusManageView busManageView;

    BusManageModel(BusManageView busManageView) {
        this.busManageView = busManageView;
    }

    void addBus(int busId, String busNumber, String busName) {
        Bus bus = new Bus(busId, busNumber, busName);
        TransitlyDB.getInstance().addBus(bus);

        busManageView.onBusAdded("Bus has been added.");
    }

    void getAllBuses() {
        ArrayList<Bus> buses = TransitlyDB.getInstance().getAllBuses();

        if(buses.isEmpty()) {
            busManageView.showMessage("No buses available.");
            return;
        }

        busManageView.showBusTable(buses);
    }

    boolean doesBusIdExist(int busId) {
        ArrayList<Bus> buses = TransitlyDB.getInstance().getAllBuses();

        for(Bus bus : buses) {
            if(bus.getBusId() == busId) return true;
        }

        return false;
    }

    void removeBus(int busId) {
        boolean isDeleted = TransitlyDB.getInstance().removeBus(busId);

        if(isDeleted) busManageView.showMessage("Bus has been removed.");
        else busManageView.showMessage("Unable to remove bus.");
    }
}
