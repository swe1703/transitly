package com.swetha.transitly.features.admin.busroutemanage;

import com.swetha.transitly.data.dto.Bus;
import com.swetha.transitly.data.dto.BusRoute;
import com.swetha.transitly.data.dto.Route;
import com.swetha.transitly.data.repository.TransitlyDB;

import java.util.ArrayList;

class BusRouteManageModel {
    private BusRouteManageView busRouteManageView;

    public BusRouteManageModel(BusRouteManageView busRouteManageView) {
        this.busRouteManageView = busRouteManageView;
    }


    boolean doesBusRouteIdExist(int busRouteId) {
        for(BusRoute map : TransitlyDB.getInstance().getAllBusRoutes()) {
            if(map.getBusRouteId() == busRouteId) return true;
        }

        return false;
    }

    Bus getBusById(int busId) {
        return TransitlyDB.getInstance().getBusById(busId);
    }

    Route getRouteById(int routeId) {
        return TransitlyDB.getInstance().getRouteById(routeId);
    }

    void addBusRoute(int busRouteId, int busId, int routeId, String shift) {
        BusRoute busRoute = new BusRoute(busRouteId, busId, routeId, shift);
        TransitlyDB.getInstance().addBusRoute(busRoute);
        busRouteManageView.showMessage("Bus mapped to route successfully.");
    }

    ArrayList<BusRoute> getAllBusRoutes() {
        return TransitlyDB.getInstance().getAllBusRoutes();
    }

    void removeBusRoute(int busRouteId) {
        boolean isRemoved = TransitlyDB.getInstance().removeBusRoute(busRouteId);
        if(isRemoved) busRouteManageView.showMessage("Bus-route has been removed.");
    }
}
