package com.swetha.transitly.features.user.bussearch;

import com.swetha.transitly.data.dto.Bus;
import com.swetha.transitly.data.dto.BusRoute;
import com.swetha.transitly.data.dto.StopTiming;
import com.swetha.transitly.data.repository.TransitlyDB;

import java.util.ArrayList;
import java.util.Map;

public class BusSearchModel {
    private final BusSearchView busSearchView;

    public BusSearchModel(BusSearchView busSearchView) {
        this.busSearchView = busSearchView;
    }

    public ArrayList<String[]> getBusesByStopName(String stopName, String shift) {
        ArrayList<String[]> availableBuses = new ArrayList<>();

        ArrayList<BusRoute> busRoutes = TransitlyDB.getInstance().getAllBusRoutes();
        for(BusRoute busRoute : busRoutes) {
            if(!busRoute.getShift().equalsIgnoreCase(shift)) continue;

            ArrayList<StopTiming> stopTimings = TransitlyDB.getInstance().getTimingsByBusRouteId(busRoute.getBusRouteId());
            for(StopTiming stopTiming : stopTimings) {
                for(Map.Entry<String, String> stopNameWithTiming : stopTiming.getStopTimings().entrySet()) {

                    String existingStopName = stopNameWithTiming.getKey();

                    if(existingStopName.equalsIgnoreCase(stopName)) {
                        Bus bus = TransitlyDB.getInstance().getBusById(busRoute.getBusId());
                        if(bus != null) {
                            String arrivalTime = stopNameWithTiming.getValue();
                            availableBuses.add(new String[] {bus.getBusNumber(), bus.getBusName(), arrivalTime});
                        }

                        break;
                    }
                }
            }
        }

        return availableBuses;
    }
}
