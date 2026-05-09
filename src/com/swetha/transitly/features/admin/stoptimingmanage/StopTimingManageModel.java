package com.swetha.transitly.features.admin.stoptimingmanage;

import com.swetha.transitly.data.dto.BusRoute;
import com.swetha.transitly.data.dto.Route;
import com.swetha.transitly.data.dto.StopTiming;
import com.swetha.transitly.data.repository.TransitlyDB;

import java.util.ArrayList;
import java.util.LinkedHashMap;

class StopTimingManageModel {
    private final StopTimingManageView scheduleManageView;

    StopTimingManageModel(StopTimingManageView scheduleManageView) {
        this.scheduleManageView = scheduleManageView;
    }

    Route getRouteByBusRouteId(int busRouteId) {
        BusRoute busRouteAvailability = TransitlyDB.getInstance().getBusRouteById(busRouteId);

        if(busRouteAvailability == null) return null;
        return TransitlyDB.getInstance().getRouteById(busRouteAvailability.getRouteId());
    }

    void addStopTiming(int busRouteId, LinkedHashMap<String, String> stopTimings) {
        StopTiming stopTiming = new StopTiming(busRouteId, stopTimings);
        TransitlyDB.getInstance().setStopTiming(stopTiming);
    }

    ArrayList<StopTiming> getStopTimingsByBusRouteId(int busRouteId) {
        return TransitlyDB.getInstance().getTimingsByBusRouteId(busRouteId);
    }

    boolean updateStopTiming(int busRouteId, String stopName, String newTime) {
        return TransitlyDB.getInstance().updateStopTiming(busRouteId, stopName, newTime);
    }
}
