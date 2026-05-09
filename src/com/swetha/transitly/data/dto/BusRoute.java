package com.swetha.transitly.data.dto;

public class BusRoute {
    private final int busId;
    private final int routeId;
    private final int busRouteId;
    private final String shift;

    public BusRoute( int busRouteId, int busId, int routeId, String shift) {
        this.busRouteId = busRouteId;
        this.busId = busId;
        this.routeId = routeId;
        this.shift = shift;
    }

    public String getShift() {
        return shift;
    }

    public int getBusId() {
        return busId;
    }

    public int getRouteId() {
        return routeId;
    }

    public int getBusRouteId() {
        return busRouteId;
    }
}
