package com.swetha.transitly.data.dto;

import java.util.LinkedHashMap;

public class StopTiming {
    private final int busRouteId;
    private final LinkedHashMap<String, String> stopTimings;

    public StopTiming(int busRouteId, LinkedHashMap<String, String> stopTimings) {
        this.busRouteId = busRouteId;
        this.stopTimings = stopTimings;
    }

    public int getBusRouteId() {
        return busRouteId;
    }

    public LinkedHashMap<String, String> getStopTimings() {
        return stopTimings;
    }
}
