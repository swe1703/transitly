package com.swetha.transitly.data.dto;

import java.util.LinkedHashMap;

public class StopTiming {
    private int busRouteId;
    private LinkedHashMap<String, String> stopTimings;

    public StopTiming(int busRouteId, LinkedHashMap<String, String> stopTimings) {
        this.busRouteId = busRouteId;
        this.stopTimings = stopTimings;
    }

    public int getBusRouteId() {
        return busRouteId;
    }

    public void setBusRouteId(int busRouteId) {
        this.busRouteId = busRouteId;
    }

    public LinkedHashMap<String, String> getStopTimings() {
        return stopTimings;
    }

    public void setStopTimings(LinkedHashMap<String, String> stopTimings) {
        this.stopTimings = stopTimings;
    }
}
